package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.controller.BaseController;
import cn.ekgc.itrip.base.controller.Enum.OrderStatusEnum;
import cn.ekgc.itrip.base.controller.pojo.vo.ResponseDto;
import cn.ekgc.itrip.base.pojo.entity.Hotel;
import cn.ekgc.itrip.base.pojo.entity.HotelOrder;
import cn.ekgc.itrip.base.pojo.entity.User;
import cn.ekgc.itrip.base.pojo.entity.UserLinkUser;
import cn.ekgc.itrip.base.pojo.vo.AddHotelOrderVO;
import cn.ekgc.itrip.base.pojo.vo.HotelRoom;
import cn.ekgc.itrip.base.pojo.vo.RoomStoreVO;
import cn.ekgc.itrip.base.pojo.vo.validateRoomStoreVO;
import cn.ekgc.itrip.base.transport.HotelOrderTransport;
import cn.ekgc.itrip.base.transport.HotelRoomTransport;
import cn.ekgc.itrip.base.transport.HotelTransport;
import cn.ekgc.itrip.base.transport.UserTransport;
import cn.ekgc.itrip.base.util.HotelOrderNoCreator;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.http.HttpRequest;
import org.bouncycastle.asn1.ocsp.Request;
import org.bouncycastle.asn1.ocsp.ResponderID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelOrderController extends BaseController {
	@Autowired
	private HotelTransport hotelTransport;
	@Autowired
	private HotelRoomTransport hotelRoomTransport;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private UserTransport userTransport;
	@Autowired
	private HotelOrderTransport hotelOrderTransport;
	/**
	 * <b>生成订单前，获得预定信息</b>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getpreorderinfo")
	public ResponseDto<Object> getPreOrderInfo(@RequestBody validateRoomStoreVO validateRoomStoreVO)throws Exception{
		RoomStoreVO roomStoreVO = new RoomStoreVO();

		Hotel hotel = hotelTransport.getListByQuery(validateRoomStoreVO.getHotelId());
		//根据hotelid查询对应的hotel对象
		roomStoreVO.setHotelId(hotel.getId());
		roomStoreVO.setHotelName(hotel.getHotelName());

		//根据roomid查询对应hotelroom对象
		HotelRoom hotelRoom = hotelRoomTransport.queryHotelRoomByhotel(validateRoomStoreVO.getRoomId());
		roomStoreVO.setRoomId(hotelRoom.getId());
		roomStoreVO.setPrice(hotelRoom.getRoomPrice());

		//根据入住时间和退房时间，查询该房间所剩数量
		int store = hotelRoomTransport.queryHotelRoomStoreByDate(validateRoomStoreVO);
		roomStoreVO.setStore(store);

		roomStoreVO.setCheckInDate(validateRoomStoreVO.getCheckInDate());
		roomStoreVO.setCheckOutDate(validateRoomStoreVO.getCheckOutDate());
		roomStoreVO.setCount(validateRoomStoreVO.getCount());

		return ResponseDto.success(roomStoreVO);
	}

	/**
	 * <b>生成订单</b>
	 * @param addHotelOrderVO
	 * @return
	 * @throws Exception
	 */
    @PostMapping(value = "/addhotelorder")
	public ResponseDto<Object> addHotelOrder(@RequestBody AddHotelOrderVO addHotelOrderVO)throws Exception{
		//查询是否有房
	    validateRoomStoreVO validateRoomStoreVO = new validateRoomStoreVO();
	    BeanUtils.copyProperties(addHotelOrderVO,validateRoomStoreVO);
	    int store = hotelRoomTransport.queryHotelRoomStoreByDate(validateRoomStoreVO);
	    //如果此时的剩余房间量大于等于用户所需要的房间数量
	    if (store >= addHotelOrderVO.getCount()){
           //在有房的情况下，保存订单数据表
		   //创建HotelOrder对象
		   String userCode="";
		   Cookie[] cookies = request.getCookies();
		   for (Cookie cookie : cookies){
		   	if ("user".equals(cookie.getName())){
		   		userCode = cookie.getValue();
		    }
		   }
		   User userQuery = new User();
		   userQuery.setUserCode(userCode);

		   User user = userTransport.EmailListByQuery(userQuery).get(0);

		    HotelOrder hotelOrder = new HotelOrder();
		    hotelOrder.setUserId(user.getId());
		    BeanUtils.copyProperties(addHotelOrderVO,hotelOrder);
		    String orderNo = HotelOrderNoCreator.createHotelOrderNo(addHotelOrderVO.getHotelId(),addHotelOrderVO.getRoomId());
		    hotelOrder.setOrderNo(orderNo);
		    //交易编号
		    hotelOrder.setTradeNo(orderNo);
		    //订单状态,未支付
		    hotelOrder.setOrderStatus(OrderStatusEnum.ORDER_STATUS_PREPAY.getCode());
		    //订单价格
		    HotelRoom hotelRoom = hotelRoomTransport.queryHotelRoomByhotel(addHotelOrderVO.getRoomId());
		    //订的房间数量 * 每间房的价格
		    hotelOrder.setPayAmount(hotelRoom.getRoomPrice() * addHotelOrderVO.getCount());
            //添加联系人信息
		    List<UserLinkUser> userLinkUserList = addHotelOrderVO.getLinkUser();
		    StringBuffer sb = new StringBuffer();
		    for (UserLinkUser userLinkUser: userLinkUserList) {
			    sb.append(userLinkUser.getLinkUserName() + ",");

		    }
		    //将用户姓名截取之后传入hotelOrder
		    hotelOrder.setLinkUserName(sb.toString().substring(0,toString().length() - 1 ));
		    //保存订单
		    hotelOrderTransport.save(hotelOrder);

		    //获得 Hotelorder 对象的 Id 和orderId 添加为Map集合
		    //查询对象
		    HotelOrder query = new HotelOrder();
		    query.setOrderNo(orderNo);
		    HotelOrder order = hotelOrderTransport.getHotelOrderNo(orderNo);

		    Map<String,Object> resultMap = new HashMap<String,Object>();
		    resultMap.put("id",order.getId());
		    resultMap.put("orderNo",order.getOrderNo());
		    //返回该Map集合
		    return ResponseDto.success(resultMap);
	    }
	    return null;
	}

}

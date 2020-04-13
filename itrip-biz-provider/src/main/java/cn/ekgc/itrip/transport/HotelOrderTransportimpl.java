package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.base.pojo.entity.HotelOrder;
import cn.ekgc.itrip.base.transport.HotelOrderTransport;
import cn.ekgc.itrip.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("hotelOrderTransport")
@RequestMapping("/hotelorder/trans")
public class HotelOrderTransportimpl implements HotelOrderTransport {
   @Autowired
    private HotelOrderService hotelOrderService;
	/**
	 * <b>查询用户信息</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/list")
	public List<HotelOrder> getHotelListByQuery(HotelOrder query) throws Exception {
		return hotelOrderService.getListByQuery(query);
	}

	/**
	 * <b>保存用户信息</b>
	 * @param hotelOrder
	 * @throws Exception
	 */
	@PostMapping(value = "/save")
	public boolean save(HotelOrder hotelOrder) throws Exception {
		return hotelOrderService.savehotelorder(hotelOrder);
	}

	/**
	 * <b>更改用户信息</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/update")
	public boolean update(HotelOrder hotelOrder) throws Exception {
		return hotelOrderService.updatehotel(hotelOrder);
	}

	/**
	 * <b>使用订单Id查询用户信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/id")
	public HotelOrder getHotelById(Long orderId) throws Exception {
		return hotelOrderService.getHotelById(orderId);
	}

	/**
	 * <b>使用订单号查询用户信息</b>
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/no")
	public HotelOrder getHotelOrderNo(String orderNo) throws Exception {
		return hotelOrderService.getNo(orderNo);
	}
}

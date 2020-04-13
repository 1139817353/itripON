package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.base.pojo.entity.HotelOrder;
import cn.ekgc.itrip.dao.HotelOrderDao;
import cn.ekgc.itrip.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("hotelOrderService")
@Transactional
public class HotelOrderServiceimpl implements HotelOrderService {
	@Autowired
	private HotelOrderDao hotelOrderDao;
	/**
	 * <b>查询用户列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<HotelOrder> getListByQuery(HotelOrder query) throws Exception {
		List<HotelOrder> hotelOrderList = hotelOrderDao.findListByQuery(query);
		if (hotelOrderList != null){
			return hotelOrderList;
		}
		return new ArrayList<HotelOrder>();
	}

	/**
	 * <b>保存用户信息</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	public boolean savehotelorder(HotelOrder hotelOrder) throws Exception {
		int count = hotelOrderDao.saveHotelQuery(hotelOrder);
		if (count > 0 ){
			return true;
		}
		return false;
	}

	/**
	 * <b>更改用户信息</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	public boolean updatehotel(HotelOrder hotelOrder) throws Exception {
		int count = hotelOrderDao.updateHotel(hotelOrder);
		if (count > 0){
			return true;
		}
		return false;
	}

	/**
	 * <b>按照订单Id查询用户信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public HotelOrder getHotelById(Long orderId) throws Exception {
		HotelOrder query = new HotelOrder();
		query.setId(orderId);
        List<HotelOrder> orderList = hotelOrderDao.findListByQuery(query);
		if (orderList != null && orderList.size() > 0 ){
			return orderList.get(0);
		}
		return null;
	}

	/**
	 * <b>根据用户订单号查询用户信息</b>
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	public HotelOrder getNo(String orderNo) throws Exception {
		HotelOrder query = new HotelOrder();
		query.setOrderNo(orderNo);
		List<HotelOrder> hotelOrderList = hotelOrderDao.findListByQuery(query);
		if (hotelOrderList != null && hotelOrderList.size() > 0 ){
			return hotelOrderList.get(0);
		}
		return null;
	}
}

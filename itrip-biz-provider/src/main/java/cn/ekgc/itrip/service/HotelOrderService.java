package cn.ekgc.itrip.service;

import cn.ekgc.itrip.base.pojo.entity.HotelOrder;

import java.util.List;

public interface HotelOrderService {

	List<HotelOrder> getListByQuery(HotelOrder query)throws Exception;

	boolean savehotelorder(HotelOrder hotelOrder)throws Exception;

	boolean updatehotel(HotelOrder hotelOrder)throws Exception;

	HotelOrder getHotelById(Long orderId)throws Exception;

	HotelOrder getNo(String orderNo)throws Exception;
}

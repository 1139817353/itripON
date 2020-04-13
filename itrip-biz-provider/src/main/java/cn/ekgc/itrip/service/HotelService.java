package cn.ekgc.itrip.service;

import cn.ekgc.itrip.base.pojo.entity.Hotel;

public interface HotelService {
	/**
	 * <b>根据酒店id查询信息</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	Hotel getListByQuery(Long hotelId)throws Exception;
}

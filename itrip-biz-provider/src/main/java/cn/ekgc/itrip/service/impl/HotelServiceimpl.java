package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.base.controller.pojo.vo.ResponseDto;
import cn.ekgc.itrip.base.pojo.entity.Hotel;
import cn.ekgc.itrip.dao.HotelDao;
import cn.ekgc.itrip.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("hotelService")
@Transactional
public class HotelServiceimpl implements HotelService {
	@Autowired
	private HotelDao hotelDao;
	/**
	 * <b>根据酒店id查询信息</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	public Hotel getListByQuery(Long hotelId) throws Exception {
		Hotel query = new Hotel();
		query.setId(hotelId);
		List<Hotel> hotelList = hotelDao.findListByQuery(hotelId);
		if (hotelList != null && hotelList.size() > 0 ){
			return hotelList.get(0);
		}
		return new Hotel();
	}
}

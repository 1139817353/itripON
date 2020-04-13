package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.base.pojo.entity.HotelOrder;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Resource
public interface HotelOrderDao {
	/**
	 * <b>查询这个时间段租出多少间房</b>
	 * @param orderQueryMap
	 * @return
	 * @throws Exception
	 */
	Integer findOrderRoomCountByQuery(Map<String, Object> orderQueryMap)throws Exception;

	/**
	 * <b>查询用户信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<HotelOrder> findListByQuery(HotelOrder query) throws Exception;

	/**
	 * <b>保存用户信息</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	int saveHotelQuery(HotelOrder hotelOrder)throws Exception;

	/**
	 * <b>更改用户信息</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	int updateHotel(HotelOrder hotelOrder)throws Exception;

}

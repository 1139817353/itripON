package cn.ekgc.itrip.service;

import cn.ekgc.itrip.base.pojo.vo.HotelRoom;
import cn.ekgc.itrip.base.pojo.vo.SearchHotelRoomVO;
import cn.ekgc.itrip.base.pojo.vo.validateRoomStoreVO;

import java.util.List;

public interface HotelRoomService {
	/**
	 * <b>根据roomid查询hotelroom对象</b>
	 * @param searchHotelRoomVO
	 * @return
	 * @throws Exception
	 */
	List<HotelRoom> getHotelRoomByQuery(SearchHotelRoomVO searchHotelRoomVO)throws Exception;
	/**
	 * <b>根据房间Id的查询对象</b>
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	HotelRoom getHotelRoomById(Long roomId)throws Exception;
	/**
	 * <b>根据入住时间和退房时间，查询该房间所剩数量</b>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	int getHotelByDate(validateRoomStoreVO validateRoomStoreVO)throws Exception;
}

package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.base.pojo.vo.HotelRoom;
import cn.ekgc.itrip.base.pojo.vo.SearchHotelRoomVO;
import cn.ekgc.itrip.base.pojo.vo.validateRoomStoreVO;
import cn.ekgc.itrip.dao.HotelOrderDao;
import cn.ekgc.itrip.dao.HotelRoomDao;
import cn.ekgc.itrip.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("hotelRoomService")
@Transactional
public class HotelRoomServiceimpl implements HotelRoomService {
	@Autowired
	private HotelRoomDao hotelRoomDao;
	@Autowired
	private HotelOrderDao hotelOrderDao;

	/**
	 * <b>根据roomid查询hotelroom对象</b>
	 *
	 * @param searchHotelRoomVO
	 * @return
	 * @throws Exception
	 */
	public List<HotelRoom> getHotelRoomByQuery(SearchHotelRoomVO searchHotelRoomVO) throws Exception {
		List<HotelRoom> resultList = new ArrayList<HotelRoom>();
		//根据酒店id查询该酒店所有的房间列表
		HotelRoom query = new HotelRoom();
		query.setHotelId(searchHotelRoomVO.getHotelId());
		List<HotelRoom> roomList = hotelRoomDao.findListByQuery(query);
		if (roomList != null && roomList.size() > 0) {
			for (HotelRoom hotelRoom : roomList) {
				//遍历列表,根据房间Id和当前时间查询临时库存
				//封装查询列表
				Map<String, Object> queryMap = new HashMap<String, Object>();
				queryMap.put("roomId", hotelRoom.getId());
				//记录时间beginDate
				queryMap.put("beginDate", searchHotelRoomVO.getStartDate());
				Integer store = hotelRoomDao.queryTempStore(queryMap);
				if (store == null) {
					//如果临时库存不存在，查询总库存
					queryMap.put("productId", hotelRoom.getId());
					store = hotelRoomDao.queryTotalStore(queryMap);
				}
				//计算可用库存，如果库存大于0
				if (store != null && store > 0) {
					//查询此时该房间订单中处于支付成功和未支付的订单
					Map<String, Object> orderQueryMap = new HashMap<String, Object>();
					orderQueryMap.put("roomId", hotelRoom.getId());
					orderQueryMap.put("startDate", searchHotelRoomVO.getStartDate());
					orderQueryMap.put("endDate", searchHotelRoomVO.getEndDate());
					Integer orderRoomCount = hotelOrderDao.findOrderRoomCountByQuery(orderQueryMap);
					if (store - orderRoomCount > 0) {
						resultList.add(hotelRoom);
					}
				}
			}

		}
		return resultList;
	}

	/**
	 * <b>根据房间Id的查询对象</b>
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	public HotelRoom getHotelRoomById(Long roomId) throws Exception {
		HotelRoom query = new HotelRoom();
		query.setId(roomId);
		List<HotelRoom> roomList = hotelRoomDao.findListByQuery(query);
		if (roomList != null && roomList.size() > 0 ){
			return roomList.get(0);
		}
		return new HotelRoom();
	}

	/**
	 * <b>根据入住时间和退房时间，查询该房间所剩数量</b>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	public int getHotelByDate(validateRoomStoreVO validateRoomStoreVO) throws Exception {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("roomId",validateRoomStoreVO.getRoomId());
		resultMap.put("createDate",validateRoomStoreVO.getCheckInDate());
		resultMap.put("endDate",validateRoomStoreVO.getCheckOutDate());
		Integer store = hotelRoomDao.queryTempStore(resultMap);
        if (store == null){
        	//临时库存查询不到，就查询总库存
	        Map<String,Object> queryMap = new HashMap<String,Object>();
	        queryMap.put("productId",validateRoomStoreVO.getRoomId());
	        store = hotelRoomDao.queryTotalStore(queryMap);
        }
        //计算可用库存,如果库存大于0
		Map<String,Object> queryMap = new HashMap<String,Object>();
        queryMap.put("roomId",validateRoomStoreVO.getRoomId());
        queryMap.put("createDate",validateRoomStoreVO.getCheckInDate());
        queryMap.put("endDate",validateRoomStoreVO.getCheckOutDate());
        Integer count = hotelOrderDao.findOrderRoomCountByQuery(queryMap);
        if (store - count > 0){
        	//返回房间数量
        	return store - count;
        }

		return 0;
	}
}
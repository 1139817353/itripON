package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.base.pojo.vo.HotelRoom;
import cn.ekgc.itrip.base.pojo.vo.SearchHotelRoomVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Resource
public interface HotelRoomDao {
	/**
	 * <b>查询临时库存</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	Integer queryTempStore(Map<String, Object> queryMap)throws Exception;

	/**
	 * <b>查询总库存</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	Integer queryTotalStore(Map<String, Object> queryMap)throws Exception;

	/**
	 * <b>查询所有的房间</b>
	 * @param room
	 * @return
	 */
	List<HotelRoom> findListByQuery(HotelRoom room);
}

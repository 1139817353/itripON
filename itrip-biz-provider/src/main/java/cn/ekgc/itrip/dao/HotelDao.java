package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.base.pojo.entity.Hotel;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface HotelDao {
	/**
	 * <b>根据酒店id查询信息</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	List<Hotel> findListByQuery(Long hotelId)throws Exception;
}

package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.base.pojo.entity.ItripImage;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface ItripImageDao {
	/**
	 * <b>根据targetId查询酒店图片（type=0）</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<ItripImage> findListByQuery(ItripImage query)throws Exception;
}

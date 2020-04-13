package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.base.pojo.entity.LableDic;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface LableDao {
	/**
	 * <b>查询列表信息</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<LableDic> findListByQuery(LableDic query)throws Exception;
}

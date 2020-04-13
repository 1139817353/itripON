package cn.ekgc.itrip.service;

import cn.ekgc.itrip.base.pojo.entity.LableDic;

import java.util.List;

public interface LableService {
	/**
	 * <b>根据查询获得信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<LableDic> getListByQuery(LableDic query)throws Exception;
}

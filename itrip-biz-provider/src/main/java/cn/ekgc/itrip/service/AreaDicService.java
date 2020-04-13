package cn.ekgc.itrip.service;

import cn.ekgc.itrip.base.pojo.entity.AreaDic;

import java.util.List;

public interface AreaDicService {
	/**
	 * <b>查询热门城市</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<AreaDic> getHotCityList(AreaDic query) throws Exception;
}

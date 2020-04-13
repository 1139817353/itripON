package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.base.pojo.entity.AreaDic;
import cn.ekgc.itrip.dao.AreaDicDao;
import cn.ekgc.itrip.service.AreaDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("hotelService")
@Transactional
public class AreaDicServiceimpl implements AreaDicService {
	@Autowired
	private AreaDicDao areaDicDao;
	/**
	 * <b>查询热门城市</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<AreaDic> getHotCityList(AreaDic query) throws Exception {
		List<AreaDic> areaDicList = areaDicDao.findListByQuery(query);
		if (areaDicList != null){
			return areaDicList;
		}
		return new ArrayList<AreaDic>();
	}
}

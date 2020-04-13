package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.base.pojo.entity.LableDic;
import cn.ekgc.itrip.dao.LableDao;
import cn.ekgc.itrip.service.LableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("lableService")
@Transactional
public class LableServiceimpl implements LableService {
	@Autowired
	private LableDao lableDao;
	/**
	 * <b>根据查询获得信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<LableDic> getListByQuery(LableDic query) throws Exception {
		List<LableDic> lableDicList =lableDao.findListByQuery(query);
		if (lableDicList != null){
			return lableDicList;
		}

		return new ArrayList<LableDic>();
	}
}

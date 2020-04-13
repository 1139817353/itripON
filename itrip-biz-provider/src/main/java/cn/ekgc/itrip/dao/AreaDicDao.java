package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.base.pojo.entity.AreaDic;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface AreaDicDao {
	/**
	 * <b>查询热门城市</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<AreaDic> findListByQuery(AreaDic query)throws Exception;
}

package cn.ekgc.itrip.dao;


import cn.ekgc.itrip.base.pojo.entity.User;

import javax.annotation.Resource;
import java.util.List;
@Resource
public interface UserDao {
	/**
	 * <b>查询用户信息</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<User> ListByQuery(User query)throws Exception;

	/**
	 * <b>保存用户后返回</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int saveList(User user) throws Exception;

	/**
	 * <b>更改用户信息</b>
	 * @param query
	 * @throws Exception
	 */
	int updateUser(User query)throws Exception;
}

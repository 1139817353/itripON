package cn.ekgc.itrip.service;

import cn.ekgc.itrip.base.pojo.entity.User;

import java.util.List;

public interface UserService {
	/**
	 * <b>查询用户信息</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<User> ListByQuery(User query)throws Exception;

	/**
	 * <b>保存用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean saveEmailByList(User user)throws Exception;
	/**
	 * <b>通过userCode在Redis中查询对应的激活码</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	String ListByCode(String user)throws Exception;
	/**
	 * <b>更改用户信息</b>
	 * @param query
	 * @throws Exception
	 */
	boolean updateUser(User query)throws Exception;
}

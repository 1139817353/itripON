package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.base.pojo.entity.User;
import cn.ekgc.itrip.base.transport.UserTransport;
import cn.ekgc.itrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userTransport")
@RequestMapping("/user/trans")
public class UserTransportimpl implements UserTransport {
    @Autowired
	private UserService userService;
	/**
	 * <b>查询用户信息</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/list")
	public List<User> EmailListByQuery(@RequestBody User query) throws Exception {
		return userService.ListByQuery(query);
	}

	/**
	 * <b>保存用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/save")
	public boolean EmailSaveListByQuery(@RequestBody User user) throws Exception {
		return userService.saveEmailByList(user);
	}

	/**
	 * <b>通过userCode在Redis中查询对应的激活码</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/activeCode")
	public String ListByCode(String user) throws Exception {
		return userService.ListByCode(user);
	}

	/**
	 * <b>更改用户信息</b>
	 * @param query
	 * @throws Exception
	 */
	@PostMapping(value = "/updateUser")
	public boolean update(User query) throws Exception {
		return userService.updateUser(query);
	}
}

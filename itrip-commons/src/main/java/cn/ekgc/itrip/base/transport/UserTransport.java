package cn.ekgc.itrip.base.transport;

import cn.ekgc.itrip.base.pojo.entity.User;
import io.swagger.annotations.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/user/trans")
public interface UserTransport {
	/**
	 * <b>查询用户信息</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查询用户列表",produces = "application/json",httpMethod = "post")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "User",value = "查询对象")
	})
	@ApiResponses({
			@ApiResponse(code = 200,message = "相应成功，返回列表")
	})
	@PostMapping(value = "list")
	List<User> EmailListByQuery(@RequestBody User query)throws Exception;

	/**
	 * <b>保存用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/save")
	boolean EmailSaveListByQuery(@RequestBody User user)throws Exception;

	/**
	 * <b>通过userCode在Redis中查询对应的激活码</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/activeCode")
	String ListByCode(@RequestParam String user)throws Exception;

	/**
	 * <b>更改用户信息</b>
	 * @param query
	 * @throws Exception
	 */
	@PostMapping(value = "/updateUser")
	boolean update(User query)throws Exception;
}

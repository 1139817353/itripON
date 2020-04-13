package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.base.pojo.entity.User;
import cn.ekgc.itrip.base.util.ActiveCodeUtil;
import cn.ekgc.itrip.base.util.MailSenderUtil;
import cn.ekgc.itrip.base.util.RegValidationUtil;
import cn.ekgc.itrip.base.util.SmsSenderUtil;
import cn.ekgc.itrip.dao.UserDao;
import cn.ekgc.itrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("userService")
@Transactional
public class UserServiceimpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private  MailSenderUtil mailSenderUtil;
	@Autowired
	private SmsSenderUtil smsSenderUtil;
	/**
	 * <b>查询用户信息</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<User> ListByQuery(User query) throws Exception {

		return userDao.ListByQuery(query);
	}

	/**
	 * <b>保存用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean saveEmailByList(User user) throws Exception {
		user.setCreationDate(new Date());
		int count = userDao.saveList(user);
		if (count > 0 ){
			//保存成功
            //产生激活码，将激活码保存
          String ActiveCode = ActiveCodeUtil.createActiveCode();
			//使用StringRedisTemlate将验证码进行保存，key是email地址，value则是激活码
			stringRedisTemplate.opsForValue().set(user.getUserCode(),ActiveCode);
            //设置存活时间
			//TimeUnit 时间单位.分钟
			stringRedisTemplate.expire(user.getUserCode(),30, TimeUnit.MINUTES);
		    //判断此时用户注册的是邮箱还是手机号码
			if(RegValidationUtil.validateEmail(user.getUserCode())){
				//通过发送邮件，将激活码发送给用户
				return mailSenderUtil.sendActiveCode(user.getUserCode(),ActiveCode);
				//如果是手机登录
			}else if (RegValidationUtil.vaildateCellphone(user.getUserCode())){
				//发送信息(容联云)
                return smsSenderUtil.sendSmsCellphone(user.getUserCode(),ActiveCode);
			}
		}
		return false;
	}

	/**
	 * <b>通过userCode在Redis中查询对应的激活码</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String ListByCode(String user) throws Exception {
		String code = stringRedisTemplate.opsForValue().get(user);
		return code;
	}

	/**
	 * <b>更改用户信息</b>
	 * @param query
	 * @throws Exception
	 */
	public boolean updateUser(User query) throws Exception {
		int count = userDao.updateUser(query);
		if (count > 0 ){
			return true;
		}
		return false;
	}


}

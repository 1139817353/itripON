package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.controller.BaseController;
import cn.ekgc.itrip.base.controller.Enum.UserActivatedEnum;
import cn.ekgc.itrip.base.controller.Enum.UserTypeEnum;
import cn.ekgc.itrip.base.controller.pojo.vo.ResponseDto;
import cn.ekgc.itrip.base.pojo.entity.User;
import cn.ekgc.itrip.base.pojo.vo.UserVo;
import cn.ekgc.itrip.base.transport.UserTransport;
import cn.ekgc.itrip.base.util.JWTUtil;
import cn.ekgc.itrip.base.util.MD5Util;
import cn.ekgc.itrip.base.util.RegValidationUtil;
import io.swagger.annotations.Api;
import org.apache.http.HttpResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>爱旅行-认证模块控制器</b>
 * @author Arthur
 * @version 1.0.0
 * @since 1.0.0
 */
@Api(value = "用户控制层",tags = "用户信息交互接口")
@RestController("autoController")
@RequestMapping("/auth/api")
public class AutoController extends BaseController {
      @Autowired
      private UserTransport userTransport;
      @Autowired
      private HttpResponse response;
	/**
	 * <b>用户名注册验证-电子邮件</b>
	 * @param name
	 * @return
	 * @throws Exception
	 */
   public ResponseDto<Object> checkUserEmailForRegistry(String name) throws Exception{
   	//校验用户所提交的电子邮件是否有效
	   if (RegValidationUtil.validateEmail(name)){
	   	  //封装查询对象
		   User query = new User();
		   query.setUserCode(name);
		  //进行查询邮箱地址在数据库中是否存在
		 List<User> userList = userTransport.EmailListByQuery(query);
         //如果不存在
		 if (userList == null || userList.size() == 0){
          	//此时用户注册时填写的邮箱地址可用，可注册
          	return ResponseDto.success();
          }
	   }
	   return ResponseDto.failure("该邮箱地址已被注册");
   }

	/**
	 * <b>使用电子邮件注册用户信息</b>
	 * @param userVo
	 * @return
	 * @throws Exception
	 */
   @PostMapping(value = "doregister")
   public ResponseDto<Object> registryUser(@RequestBody UserVo userVo)throws Exception{
   	    //判断电子邮件是否有效
	   if (RegValidationUtil.validateEmail(userVo.getUserCode()) && userVo.getUserPassword()
			   != null && !"".equals(userVo.getUserPassword())){
	   	   User query = new User();
	   	   query.setUserCode(userVo.getUserCode());
	   	   List<User> userList = userTransport.EmailListByQuery(query);
	   	   //查询集合邮箱若是未注册，那么就要给密码进行加密
	   	   if (userList == null && userList.size() <= 0 ){
	   	   	//将密码进行加密
	   	   	userVo.setUserPassword(MD5Util.encrypt(userVo.getUserPassword()));
             User user = new User();
		       BeanUtils.copyProperties(userVo,user);
		       //调用该方法用户属于自主注册
               user.setUserType(UserTypeEnum.USER_TYPE_REG.getCode());
	           //将激活码设置为未激活
		       user.setActivated(UserActivatedEnum.USER_ACTIVATED_NO.getCode());
		       //使用传输层，远程调用生产者进行注册
		       boolean flag = userTransport.EmailSaveListByQuery(user);
		       if (flag){
		       	//注册成功
		       	return ResponseDto.success();
		       }
	   	   }
	   }
	   return ResponseDto.failure("注册失败");

   }

	/**
	 * <b>使用手机号注册用户信息</b>
	 * @param userVo
	 * @return
	 * @throws Exception
	 */
   @PostMapping(value = "/registerbyphone")
   public ResponseDto<Object> Registerbyphone(@RequestBody UserVo userVo)throws Exception{
   	  if (RegValidationUtil.vaildateCellphone(userVo.getUserCode()) &&
		      userVo.getUserPassword() != null && !"".equals(userVo.getUserPassword().trim())){
   	  	   User query = new User();
   	  	   query.setUserCode(userVo.getUserCode());
           List<User> userList = userTransport.EmailListByQuery(query);
           if (userList == null || userList.size() == 0){
           	 userVo.setUserPassword(MD5Util.encrypt(userVo.getUserPassword()));
           	 User user = new User();
           	 BeanUtils.copyProperties(userVo,user);
             user.setUserType(UserTypeEnum.USER_TYPE_REG.getCode());
             user.setActivated(UserActivatedEnum.USER_ACTIVATED_NO.getCode());
           	 boolean flag = userTransport.EmailSaveListByQuery(user);
           	 if (flag){
           	 	return ResponseDto.success();
             }
           }
           return ResponseDto.failure("该号码已被注册");
      }
   	  return ResponseDto.failure("注册失败");
   }

	/**
	 * <b>激活注册用户-邮箱</b>
	 * @param user
	 * @param code
	 * @return
	 * @throws Exception
	 */
   @PostMapping(value = "activate")
   public ResponseDto<Object> activeUser(String user,String code)throws Exception{
      if (code != null && !"".equals(code.trim()) && user != null && !"".equals(user)){
          String codes = userTransport.ListByCode(user);
          if (code.equals(codes)){
             User query = new User();
             query.setUserCode(user);
             //改变状态
             query.setActivated(UserActivatedEnum.USER_ACTIVATED_YES.getCode());
             userTransport.update(query);
             return ResponseDto.success();
          }
          return ResponseDto.failure("激活码不正确!");
      }
      return ResponseDto.failure("激活失败");
   }

	/**
	 * <b>激活注册用户-手机号码</b>
	 * @param user
	 * @param code
	 * @return
	 * @throws Exception
	 */
   @PostMapping(value = "/validatephone")
   public ResponseDto<Object> activeUserByCellphone(String user,String code)throws Exception{
   	  if (user != null && !"".equals(user) && code != null && !"".equals(code)){
   	  	String codes = userTransport.ListByCode(user);
   	  	if (code.equals(codes)){
	        User query = new User();
	        query.setUserCode(user);
	        query.setActivated(UserActivatedEnum.USER_ACTIVATED_YES.getCode());
	        userTransport.update(query);
        }
   	  	return ResponseDto.failure("激活码不正确");
      }
   	  return ResponseDto.failure("激活失败");
   }

	/**
	 * <b>使用cellphone/email和password登录系统</b>
	 * @param name
	 * @param password
	 * @return
	 * @throws Exception
	 */
   @PostMapping(value = "/dologin")
   public ResponseDto<Object> loginUser(String name,String password)throws Exception{
   	 if (name != null && !"".equals(name.trim()) && password != null && !"".equals(password.trim())){
   	 	 User query = new User();
   	 	 query.setUserCode(name);
   	 	 List<User> userList = userTransport.EmailListByQuery(query);
   	 	 if (userList != null && userList.size() > 0){
   	 	 	//取出登录用户账号
            User user = userList.get(0);
            //登录密码与之比较
            if (user.getUserPassword().equals(MD5Util.encrypt(password))){
            	if (user.getActivated() == UserActivatedEnum.USER_ACTIVATED_YES.getCode()){
            		 String token = JWTUtil.createToken(user.getId());
		             response.setHeader("token", token);
		             return ResponseDto.success(token);

	            }else {
            		return ResponseDto.failure("该用户未激活!");
	            }
            }else {
	            return ResponseDto.failure("密码错误!");
            }
	     }else {
		     return ResponseDto.failure("用户不存在!");
	     }
     }else {
	     return ResponseDto.failure("登录错误!");

     }
   }


}

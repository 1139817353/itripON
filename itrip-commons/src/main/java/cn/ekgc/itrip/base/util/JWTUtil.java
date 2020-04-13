package cn.ekgc.itrip.base.util;

import cn.ekgc.itrip.base.util.content.SystemConstant;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
	private static Algorithm algorithm = Algorithm.HMAC256(SystemConstant.SECRCT_key);

	/**
	 * <b>生成令牌</b>
	 * @return id 当前登录用户主键
	 * @throws Exception
	 */
	public static String createToken(Long id) throws  Exception{
		//创建JWTCreator.Builder对象，用于创建Token
		JWTCreator.Builder builder = JWT.create();
		//创建一个map集合用于存储JWT的头部信息
		Map<String,Object> header = new HashMap<String,Object>();
		//第一个设定加密算法
		header.put("alg", "HS256");
		header.put("typ", "JWT");

		builder.withHeader(header);
		//设置有效载荷
		//key-value
		builder.withClaim("id", id);
		//给前端设置过期时间
		Long expTime = new Date().getTime() + (30 * 60 *1000);
		builder.withClaim("expTime",new Date(expTime));

		//使用算法进行签名，生成最终的Token字符串
		return builder.sign(algorithm);

	}

	/**
	 * <b>进行解密token，获得用户主键</b>
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Long validateToken(String token) throws  Exception{
		if (token != null && !"".equals(token.trim())){
			try {
				//通过加密算法进行解密
				JWTVerifier verifier = JWT.require(algorithm).build();
				//进行解密校验
				DecodedJWT decodedJWT = verifier.verify(token);
				//当没有发生异常信息时，说明token已经被成功识别，那么获得该信息
				return decodedJWT.getClaim("id").asLong();


			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return -1L;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(createToken(20L));

	}
}

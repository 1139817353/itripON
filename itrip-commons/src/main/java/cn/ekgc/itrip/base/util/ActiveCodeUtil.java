package cn.ekgc.itrip.base.util;

import java.util.Random;

/**
 * <b>激活码工具类</b>
 */
public class ActiveCodeUtil {
	public static String createActiveCode(){
		//拼接字符串
		StringBuffer stringBuffer = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 6 ; i++){
			stringBuffer.append(random);
		}
		return stringBuffer.toString();
	}
}

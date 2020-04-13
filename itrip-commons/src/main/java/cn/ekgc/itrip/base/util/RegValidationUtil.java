package cn.ekgc.itrip.base.util;

public class RegValidationUtil {
	//设置电子邮件格式
	private static final String emailRegEx =  "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

	//设置手机号码格式
	private static final String cellphoneRegEx = "1\\d{10}";

	/**
	 * <b>判断电子邮件信息</b>
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public static boolean validateEmail(String email){
		//判断此时的email地址不能为null，并且也不是空字符串
		if(email != null && !"".equals(email)){
			//如果格式一致返回
			return email.matches(emailRegEx);
		}
		return false;
	}
	public static boolean vaildateCellphone(String cellphone){
		if (cellphone != null && !"".equals(cellphone)){
			return cellphone.matches(cellphoneRegEx);
		}
		return false;
	}
}

package cn.ekgc.itrip.base.util.content;

import java.util.Properties;

public class SystemConstant {
	private static Properties props = new Properties();

	static{
		try {
			props.load(SystemConstant.class.getClassLoader().getResourceAsStream("prop/commons.properties"));

		}catch (Exception e){
			e.printStackTrace();
		}

	}
	public static final String SECRCT_key = props.getProperty("secret.key");
}

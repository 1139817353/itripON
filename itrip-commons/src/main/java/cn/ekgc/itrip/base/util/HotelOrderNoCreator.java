package cn.ekgc.itrip.base.util;

import java.text.SimpleDateFormat;
import java.util.Random;

public class HotelOrderNoCreator {
	public static String createHotelOrderNo(Long hotelId,Long roomId)throws Exception{
		Random random = new Random();
		StringBuffer stringBuffer = new StringBuffer();
		//增加HotelId
		stringBuffer.append(hotelId);
		//增加roomid
		stringBuffer.append(roomId);
		//获得当前时间，进行格式化
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmSSS");
		stringBuffer.append(random.nextInt(10));
		//对于该结果进行MD5加密
		String result = MD5Util.encrypt(stringBuffer.toString());
		return result;


	}
}

package cn.ekgc.itrip.base.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;


/**
 * <b>邮件发送工具类</b>
 */
@Component("mailSenderUtil")
public class MailSenderUtil {
	 @Autowired
	 private JavaMailSender mailSender;
	public boolean sendActiveCode(String email,String activeCode)throws Exception{
		try {
			//可以使用过带有html样式的邮件
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			// 设置utf-8或GBK编码，否则邮件会有乱码，true表示为multipart邮件
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
			//设置发件人邮箱地址
			messageHelper.setFrom("zmeye330@163.com");
			//设置抄送人地址
			messageHelper.setCc("zmeye330@163.com");
			//收件人地址
			messageHelper.setTo(email);
			//设置邮箱邮件主题
			messageHelper.setSubject("爱旅行-用户注册激活码");
			//设定发送字符串
			String content =  "<div>亲爱的&nbsp;" + email + "&nbsp;你好:<br>" +
					"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>" +
					"感谢您注册为爱旅行会员！</div><div><br>以下是您的会员激活码：" +
					"<span style='font-size: 24px;'><b>" + activeCode + "</b></span></div>" +
					"<div>请在<span style='font-size: 24px;'><b>30分钟</b></span>内登录网站进行激活！！！</div>" +
					"<div>&nbsp; &nbsp; &nbsp;&nbsp;</div>";;
			//设置发送的邮件内容
			messageHelper.setText(content,true);
			//发送邮件
			mailSender.send(mimeMessage);
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
}

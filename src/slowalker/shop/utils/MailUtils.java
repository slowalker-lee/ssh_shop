package slowalker.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	public static void sendMail(String to, String code) {
		//获得session对象
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@slowalker.com","service");
			}
		});
		
		
		//创建邮件对象
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("service@slowalker.com"));
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			//抄送 CC 密送BCC
			message.setSubject("来自购物天堂商城官方激活邮件");
			message.setContent("<h1>激活邮件<h1>"
					+ "<h3><a href='http://192.168.1.4:8080/ssh_shop/user_active?code="+ code +"'>激活</a>",
					"text/html;charset=UTF-8");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			System.out.println("1 error");
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			System.out.println("2 error");
			e.printStackTrace();
		}
		//发送邮件
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		sendMail("slowalker_lee@163.com","111111111");
		System.out.println("finish");
	}
}

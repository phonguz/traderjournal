
package com.trade.util;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailHelper {

	InputStream inputStream = null;
	
	private int port = 587;
	private Session session =null;

	private static String user = "";
	private static String password = "";

	private static String fromAddress = "";
	private static String fromName = "";
	
	private static String smtpServer = "";
	private static String mailContentType = "";
	private static String encoding = "";
	
	public static GmailHelper getPreConfigured(Properties properties){
		
		GmailHelper instance = new GmailHelper(); 
		
		encoding = properties.getProperty("encoding");
		mailContentType = properties.getProperty("mailContentType");
		smtpServer = properties.getProperty("smtpServer");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		fromAddress = properties.getProperty("fromAddress");
		fromName = properties.getProperty("fromName");
		
		try{
			instance.init();	
		}catch (Throwable e) {
			e.printStackTrace();
		}
		return instance;
	}

	public void sendEmail(String to, String Subject, String body) throws Exception {

		Message message = null;
		InternetAddress[] internetAddress = null;
		Transport transport = null;
		try {

			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromAddress,fromName,encoding));
			internetAddress = InternetAddress.parse(to);
			message.setRecipients(Message.RecipientType.TO, internetAddress);
			message.setSubject(Subject);
			message.setContent(body, mailContentType+"; " + "charset=\"" + encoding + "\"");
			transport = session.getTransport("smtp");
			transport.connect(smtpServer, port, user, password);
			Transport.send(message);

		} catch (Exception e) {
			throw e;	// return error message in case of error
		}
					// return null if email is sent successfully
	}


	public void init(){

		try {
			
			Properties props = new Properties();
			props.put("mail.smtp.host", smtpServer);
			props.put("mail.smtp.user", user);
			props.put("mail.smtp.password", password);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable","true");
			props.put("mail.smtp.port", (new Integer(port)).toString());
			props.put("mail.smtp.timeout", "60000");
			props.put("mail.smtp.connectiontimeout", "60000");
			props.put("mail.debug", "true");

			session = Session.getInstance(props,new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user,password);
				}
			});
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
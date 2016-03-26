package simulation;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dataObjects.Constants;

/**
 * Email class, to send email to user.
 * 
 * @author Rahmi
 *
 */
public class Email {

	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;

	/**
	 * Constructor.
	 * 
	 * @param str
	 * @param email
	 */
	public Email(String str, String email) {

		if (str.equals(Constants.fireString)) {

			try {
				generateAndSendEmail(" Your home is on fire!! Take necessary " + "actions immediately.", email);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}

		else {

			try {
				generateAndSendEmail(
						"Unusual activity detected in your home!!" + " If its not you call the police now!!", email);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Generates and send email from ssosafe4.system@gmail.com to the user.
	 * 
	 * @param body
	 * @param email
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public static void generateAndSendEmail(String body, String email) throws AddressException, MessagingException {

		// Step1
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");

		// Step2
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("mrahmi@scu.edu"));
		generateMailMessage.setSubject("Urgent Email from your security system");
		String emailBody = body + "<br><br> Regards, <br>Marufa Rahmi<br>Soumya Devate";
		generateMailMessage.setContent(emailBody, "text/html");

		// Step3
		Transport transport = getMailSession.getTransport("smtp");
		transport.connect("smtp.gmail.com", "ssosafe4.system@gmail.com", ")(*&^%$#@!");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
}
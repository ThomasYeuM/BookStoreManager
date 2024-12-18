package util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	private String otp;
	private final String EMAIL = "bookworldwithuser@gmail.com";
	private final String PASSWORD = "uukc ykae szco eccb";
	private final String HOST_NAME = "smtp.gmail.com";
	private final int SSL_PORT = 465;

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String generateOtp() {
		return String.valueOf(100000 + (int) (Math.random() * 900000));
	}

	public boolean sendMail(String email, String subject, String content) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", this.HOST_NAME);
		props.put("mail.smtp.socketFactory.port", this.SSL_PORT);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.port", this.SSL_PORT);

		Session session = Session.getDefaultInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SendMail.this.EMAIL, SendMail.this.PASSWORD);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject(subject);
			message.setContent(content, "text/html; charset=\"utf-8\"");

			Transport.send(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean sendOtp(String email) {
		String subject = "Mã dùng một lần của bạn";
		setOtp(generateOtp());
		String content = "<html lang=\"vi\"><head><meta charset=\"utf-8\"></head>" + "<body><p>Xin chào " + email
				+ ",<br>"
				+ "<p>Chúng tôi đã nhận yêu cầu mã dùng một lần để dùng cho tài khoản HaUI BookStore của bạn.</p><br>"
				+ "<p>Mã dùng một lần của bạn là: <b>" + otp + "</b></p><br>"
				+ "<p>Nếu không yêu cầu mã này thì bạn có thể bỏ qua email này một cách an toàn. Có thể ai đó khác đã nhập địa chỉ email của bạn do nhầm lẫn.</p><br>"
				+ "<p>Xin cảm ơn, <br>HaUI BookStore</p>" + "</body></html>";

		return sendMail(email, subject, content);
	}

	public String sendNewPassword(String email) {
        otp = generateOtp(); // Tạo mã OTP
        String subject = "Mã OTP để lấy lại mật khẩu cho tài khoản HaUI BookStore của bạn";
        String content = "<html lang=\"vi\"><head><meta charset=\"utf-8\"></head>" + 
                "<body><p>Xin chào " + email + ",<br>" +
                "<p>Chúng tôi đã nhận yêu cầu lấy lại mật khẩu cho tài khoản HaUI BookStore của bạn.</p><br>" +
                "<p>Mã OTP của bạn là: <b>" + otp + "</b></p><br>" +
                "<p>Vui lòng sử dụng mã OTP này để xác thực và thay đổi mật khẩu của bạn. Nếu bạn không yêu cầu thay đổi mật khẩu này, " +
                "vui lòng bỏ qua email này một cách an toàn.</p><br>" +
                "<p>Xin cảm ơn, <br>HaUI BookStore</p>" +
                "</body></html>";

        if (sendMail(email, subject, content)) {
            return otp;
        } else {
            return null;
        }
    }
	public boolean sendResetPasswordLink(String email) {
	    try {
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}

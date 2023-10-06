package EmailPakage;

// Index : 200524M
// Name  : Ransara H.P.C.

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;


public class SendEmailTLS implements Serializable{

    private String to;
    private String from;
    private String subject;
    private String body;
    private String date;
    private String time;
    public SendEmailTLS(String to, String subject, String body){
        this.to = to;
        this.subject = subject;
        this.body = body;
        from = "mail@gmail.com"; //email address of the sender
        LocalDate now = LocalDate.now();
        date = now.format(DateTimeFormatter.ofPattern("YYYY/MM/dd"));
        LocalTime now1 = LocalTime.now();
        time = now1.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public void send() {

        final String username = from;
        final String password = ""; // App password of the email

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

}

package pl.uz.domian.contact;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MessageInfoService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String owner;

    public MessageInfoService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void sendEmail(MessageInfoDto message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(owner);
        simpleMailMessage.setFrom(owner);
        simpleMailMessage.setText(message.getText());
        simpleMailMessage.setSubject("New Message");
        simpleMailMessage.setReplyTo(message.getEmail());
        this.mailSender.send(simpleMailMessage);
    }
}

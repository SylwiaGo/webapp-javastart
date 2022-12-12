package pl.uz.domian.contact;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    private final MailSender mailSender;
    @Value("${spring.mail.username}")
    private String owner;

    public ContactService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public MailSender getMailSender() {
        return mailSender;
    }

    public void sendEmail(ContactDto message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(owner);
        simpleMailMessage.setFrom(owner);
        simpleMailMessage.setText(message.getText());
        simpleMailMessage.setSubject("New Message");
        simpleMailMessage.setReplyTo(message.getEmail());
        this.mailSender.send(simpleMailMessage);
    }
}

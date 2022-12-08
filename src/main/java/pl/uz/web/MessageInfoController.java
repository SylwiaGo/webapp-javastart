package pl.uz.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.uz.domian.contact.MessageInfoDto;
import pl.uz.domian.contact.MessageInfoService;

@Controller
public class MessageInfoController {
    MessageInfoService messageInfoService;

    public static final String NOTIFICATION_ATTRIBUTE = "notification";

    public MessageInfoController(MessageInfoService messageInfoService) {
        this.messageInfoService = messageInfoService;
    }

    @GetMapping("/contact")
    public String emialForm(Model model) {
        model.addAttribute("message", new MessageInfoDto());
        return "contact";
    }

    @PostMapping("/send")
    public String sendMessage(MessageInfoDto message, RedirectAttributes redirectAttributes) {
        messageInfoService.sendEmail(message);
        redirectAttributes.addFlashAttribute(
                NOTIFICATION_ATTRIBUTE,
                "Your message has been sent");
        return "redirect:/contact";
    }
}

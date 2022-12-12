package pl.uz.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.uz.domian.contact.ContactDto;
import pl.uz.domian.contact.ContactService;

@Controller
public class ContactController {
    ContactService contactService;

    public static final String NOTIFICATION_ATTRIBUTE = "notification";

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact")
    public String emialForm(Model model) {
        model.addAttribute("message", new ContactDto());
        return "contact";
    }

    @PostMapping("/send")
    public String sendMessage(ContactDto message, RedirectAttributes redirectAttributes) {
        contactService.sendEmail(message);
        redirectAttributes.addFlashAttribute(
                NOTIFICATION_ATTRIBUTE,
                "Your message has been sent");
        return "redirect:/contact";
    }
}

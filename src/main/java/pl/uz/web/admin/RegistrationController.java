package pl.uz.web.admin;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.uz.domian.Captcha;
import pl.uz.domian.user.UserService;
import pl.uz.domian.user.dto.UserRegistrationDto;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        Captcha captcha = new Captcha(130, 50);
        model.addAttribute("user", userRegistrationDto);
        model.addAttribute("captcha", captcha);
        return "registration-form";
    }

    @PostMapping("/registration")
    public String rigister(UserRegistrationDto userRegistrationDto) {
        userService.registerUserWithDefaultRole(userRegistrationDto);
        return "redirect:/";
    }
}

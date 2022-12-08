package pl.uz.web;

import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Controller
public class CaptchaController {

    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置位数
//        CaptchaUtil.out(5, request, response);
        // 设置宽、高、位数
        CaptchaUtil.out(130, 48, 5, request, response);
//
        // 使用gif验证码
        GifCaptcha gifCaptcha = new GifCaptcha(130,48,4);
//        CaptchaUtil.out(gifCaptcha, request, response);

        SpecCaptcha specCaptcha = new SpecCaptcha(13, 48, 5);
        request.getSession().setAttribute("captcha", specCaptcha.text().toLowerCase());


        String verCode = specCaptcha.text().toLowerCase();

        CaptchaUtil.ver(verCode, request);
    }
}
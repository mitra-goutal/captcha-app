package com.example;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class CaptchaController {

    private final DefaultKaptcha captchaProducer;

    public CaptchaController(DefaultKaptcha captchaProducer) {
        this.captchaProducer = captchaProducer;
    }

    @GetMapping("/captcha")
    public String showForm() {
        return "captcha";
    }

    @GetMapping("/captcha-image")
    public void getCaptchaImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        String captchaText = captchaProducer.createText();
        request.getSession().setAttribute("captcha", captchaText);
        BufferedImage bi = captchaProducer.createImage(captchaText);

        ImageIO.write(bi, "jpg", response.getOutputStream());
        response.getOutputStream().flush();
    }

    @PostMapping("/verify")
    public String verifyCaptcha(HttpServletRequest request, String captchaInput, Model model) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (captcha != null && captcha.equalsIgnoreCase(captchaInput)) {
            model.addAttribute("message", "تم التحقق! إليك الصورة:");
            model.addAttribute("imageUrl", "/images/your-image.jpg"); // ضع الصورة هنا
        } else {
            model.addAttribute("message", "CAPTCHA غير صحيح. حاول مرة أخرى.");
        }
        return "captcha";
    }
}




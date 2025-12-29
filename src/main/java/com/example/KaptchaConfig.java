package com.example;



import com.google.code.kaptcha.impl.DefaultKaptcha;

import com.google.code.kaptcha.util.Config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {

@Bean
public DefaultKaptcha getDefaultKaptcha() {
DefaultKaptcha kaptcha = new DefaultKaptcha();
Properties props = new Properties();

// الحدود
props.setProperty("kaptcha.border", "yes");

props.setProperty("kaptcha.border.color", "105,179,90");

// لون
props.setProperty("kaptcha.textproducer.font.color", "blue");

props.setProperty("kaptcha.textproducer.font.size", "60");

props.setProperty("kaptcha.textproducer.char.length", "5");

props.setProperty("kaptcha.textproducer.font.names", "Arial,Courier");

// عرض الصورة
props.setProperty("kaptcha.image.width", "250");

props.setProperty("kaptcha.image.height", "90");

// مسح الخلفية
props.setProperty("kaptcha.background.clear.from", "lightGray");
props.setProperty("kaptcha.background.clear.to", "white");

// خلفية بيضاء
props.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.DefaultNoise");

props.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.WaterRipple");

// مفتاح التفعيل
props.setProperty("kaptcha.session.key", "captcha");

kaptcha.setConfig(new Config(props));

return kaptcha;

}
}
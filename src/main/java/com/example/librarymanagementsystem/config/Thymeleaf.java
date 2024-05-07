package com.example.librarymanagementsystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class Thymeleaf implements WebMvcConfigurer {
    @Value("${spring.mail.host}")
    private String host ;
    @Value("${spring.mail.port}")
    private Integer port;
    @Value("${spring.mail.username}")
    private String userName;
    @Value("${spring.mail.password}")
    private String password;

    @Bean
    private ClassLoaderTemplateResolver templateResolver(){
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();

        resolver.setPrefix("templates/"); //  thymeleaf template'in alanını veriyoruz
        resolver.setCacheable(false); // Şablon değişikliklerini kolaylaştırmak için önbelleğin çevrilmesi
        resolver.setSuffix(".html"); // Template dosya uzantısı
        resolver.setTemplateMode("HTML"); // Templ"ate Type
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public TemplateEngine templateEngine(){
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setPort(port);
        sender.setHost(host);
        sender.setUsername(userName);
        sender.setPassword(password);

        return sender;
    }
}

package com.example.librarymanagementsystem.services.impl;

import com.example.librarymanagementsystem.model.entities.Mail;
import com.example.librarymanagementsystem.services.abstracts.MailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
public class MailServiceImpl implements MailSenderService {
    @Value("${spring.mail.username}")
    private String mailFrom;
    private JavaMailSender sender;
    private TemplateEngine engine;

    public MailServiceImpl(JavaMailSender sender, TemplateEngine engine) {
        this.sender = sender;
        this.engine = engine;
    }

    @Async
    public void sendMail(Mail mail) throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setFrom(mailFrom);
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());

        Context context = getHtmlContent(mail.getProperties());

        String processedString = engine.process("email-template.html", context);

        helper.setText(processedString, true);

        sender.send(mimeMessage);
    }

    private Context getHtmlContent(Map<String,Object> properties) {
        Context context = new Context();
        context.setVariables(properties);
        return context;
    }
}

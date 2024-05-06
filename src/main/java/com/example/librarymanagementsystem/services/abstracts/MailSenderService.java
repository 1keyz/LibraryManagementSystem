package com.example.librarymanagementsystem.services.abstracts;

import com.example.librarymanagementsystem.model.entities.Mail;
import jakarta.mail.MessagingException;

public interface MailSenderService {
    void sendMail(Mail mail) throws MessagingException ;
}

package com.example.librarymanagementsystem.config.taskscheduler;

import com.example.librarymanagementsystem.model.entities.BorrowedBook;
import com.example.librarymanagementsystem.model.entities.Mail;
import com.example.librarymanagementsystem.services.abstracts.BorrowedBookService;
import com.example.librarymanagementsystem.services.abstracts.MailSenderService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class CustomControlTheBringTime {
    private BorrowedBookService borrowedBookService;
    private MailSenderService mailSenderService;

    @Scheduled(cron = "0 0 12 * * ?")
    public void threadPoolTaskSchedulerControlTheBringTime() {
        List<BorrowedBook> borrowedList = borrowedBookService.BringTimePassed();

        if (borrowedList != null){
            for (BorrowedBook book : borrowedList){
                Map<String , Object> prop = new HashMap<>();
                prop.put("bookName" , book.getBookName());
                prop.put("bookTakeDate" , Date.from(book.getTakenAt().atZone(ZoneId.systemDefault()).toInstant()));
                prop.put("bookBringDate" , Date.from(book.getBringBackAt().atZone(ZoneId.systemDefault()).toInstant()));
                prop.put("message" , "reminder");

                Mail mail = Mail.builder()
                        .to(book.getUser().getEmail())
                        .properties(prop)
                        .subject("your bring time passed")
                        .build();
                try {
                    mailSenderService.sendMail(mail);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}

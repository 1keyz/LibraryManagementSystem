package com.example.librarymanagementsystem.services.impl;

import com.example.librarymanagementsystem.core.advice.types.BusinessException;
import com.example.librarymanagementsystem.dtos.request.BorrowedBookRequestDto;
import com.example.librarymanagementsystem.dtos.request.BringBackBorrowedBookRequestDto;
import com.example.librarymanagementsystem.dtos.response.BorrowedBookResponseDto;
import com.example.librarymanagementsystem.dtos.response.BringBackBorrewedBookResponseDto;
import com.example.librarymanagementsystem.model.entities.BorrowedBook;
import com.example.librarymanagementsystem.model.entities.Mail;
import com.example.librarymanagementsystem.model.entities.User;
import com.example.librarymanagementsystem.repositories.BorrowedBookRepository;
import com.example.librarymanagementsystem.services.abstracts.*;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@AllArgsConstructor
public class BorrowedBookServiceImpl implements BorrowedBookService {
    private BorrowedBookRepository repository;
    private UserService userService;
    private ModelMapper modelMapper;
    private BookService bookService;
    private MailSenderService mailSenderService;
    private AuthService authService;
    @Override
    public BorrowedBookResponseDto borrowingBook(BorrowedBookRequestDto requestDto) {
        if (userService.userHasPenaltyAmount(authService.getCurrentUser().getId())){
            throw new BusinessException("user has penalty amount!");
        }
        if (bookService.bookIsBorrowed(requestDto.getBookId())){
            throw new BusinessException("book is borrowed");
        }
        BorrowedBook borrowedBook = BorrowedBook.builder()
                .bookName(bookService.getBookById(requestDto.getBookId()).getBookName())
                .takenAt(LocalDateTime.now())
                .user(authService.getCurrentUser())
                .book(bookService.getBookByIdForBorrow(requestDto.getBookId()))
                .howManyDay(requestDto.getHowManyDay())
                .bringBackAt(LocalDateTime.now().plusDays(requestDto.getHowManyDay()))
                .build();
        emailSender(borrowedBook);
        return modelMapper.map(repository.save(borrowedBook),BorrowedBookResponseDto.class);
    }

    @Override
    public BringBackBorrewedBookResponseDto bringBackTheBorrewedBook(BringBackBorrowedBookRequestDto requestDto) {
        BorrowedBook borrowedBook = repository.GetBorrowedBookByUserId(requestDto.getUserId());

        BringBackBorrewedBookResponseDto bringDto = new BringBackBorrewedBookResponseDto();
        bringDto.setPastedBringTime(bringTimePassed(borrowedBook));
        if (!bringDto.isPastedBringTime()){
            bringDto.setMessage("your penalty amount for returning your book later than the specified date : 5$");
            User user = userService.getUserById(requestDto.getUserId());
            user.setPenaltyAmount(5);
            userService.userUpdate(user);
        }
        bookService.bookHasCame(requestDto.getBookId());
        return bringDto;
    }


    private boolean bringTimePassed(BorrowedBook book){
        book.setBringBackAt(LocalDateTime.now());
        Duration duration = Duration.between(book.getTakenAt(),book.getBringBackAt());
        long time = duration.toHours();
        if (time > (long) book.getHowManyDay()){
            return false;
        }
        return true;
    }

    public List<BorrowedBook> BringTimePassed() {
        List<BorrowedBook> borrowedBookList = repository.borrowedBooksOverdueToBeBrought(LocalDateTime.now());
        if (borrowedBookList == null) return null;
        return borrowedBookList;
    }


    public void emailSender(BorrowedBook borrowedBook){
        Mail mail = Mail.builder()
                .to(borrowedBook.getUser().getEmail())
                .subject("you have took borrowedBook")
                .properties(properties(borrowedBook))
                .build();
        try {
            mailSenderService.sendMail(mail);
        }catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    private Map<String,Object> properties(BorrowedBook borrowedBook) {
        Map<String , Object> prop = new HashMap<>();
        prop.put("HÇY kutuphanesinden kitap alındığına dair" , "hçy_idaresi");
        prop.put("bookName" , borrowedBook.getBookName());
        prop.put("bookTakeDate" , Date.from(borrowedBook.getTakenAt().atZone(ZoneId.systemDefault()).toInstant()));
        prop.put("bookBringDate" , Date.from(borrowedBook.getBringBackAt().atZone(ZoneId.systemDefault()).toInstant()));
        return prop;
    }

}

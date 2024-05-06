package com.example.librarymanagementsystem.model.entities;

import com.example.librarymanagementsystem.model.entities.abstracts.AbstractDate;
import com.example.librarymanagementsystem.model.entities.abstracts.AbstractId;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "borrowed_book")
@Entity
public class BorrowedBook extends AbstractId {
    @Column(name = "book_name")
    private String bookName;

    private int howManyDay;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "taken_at")
    private LocalDateTime takenAt;

    @Column(name = "bring_back_at")
    private LocalDateTime bringBackAt;

}

package com.example.librarymanagementsystem.model.entities;


import com.example.librarymanagementsystem.model.entities.abstracts.AbstractDate;
import com.example.librarymanagementsystem.model.enums.BookSubject;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
@Entity
public class Book extends AbstractDate {
    @Column(name = "book_name")
    private String bookName;

    @Column(name = "number_of_pages")
    private int numberOfPages;

    @Column(name = "auther_name")
    private String autherName;

    @Column(name = "is_borrowed")
    private boolean isBorrowed = false;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL , fetch =  FetchType.LAZY)
    private List<BorrowedBook> borrowedBooks;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_subject")
    private BookSubject bookSubject;

}

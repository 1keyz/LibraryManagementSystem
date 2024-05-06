package com.example.librarymanagementsystem.repositories;

import com.example.librarymanagementsystem.model.entities.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook , Long> {

    @Query(value = "SELECT * FROM borrowed_book WHERE borrowed_book.user_id =:userId ORDER BY taken_at desc limit 1 " , nativeQuery = true)
    BorrowedBook GetBorrowedBookByUserId(@Param("userId") long userId);

    @Query(value = "SELECT * FROM borrowed_book where borrowed_book.bring_back_at <:date" , nativeQuery = true)
    List<BorrowedBook> borrowedBooksOverdueToBeBrought(@Param("date") LocalDateTime date);
}

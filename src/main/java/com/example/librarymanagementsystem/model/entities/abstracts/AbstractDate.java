package com.example.librarymanagementsystem.model.entities.abstracts;


import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
@AllArgsConstructor
public abstract class AbstractDate extends AbstractId{
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public AbstractDate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

}

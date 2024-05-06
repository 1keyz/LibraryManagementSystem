package com.example.librarymanagementsystem.dtos.response;

import lombok.Data;

@Data
public class BringBackBorrewedBookResponseDto {
    private boolean pastedBringTime;
    private String Message;
}

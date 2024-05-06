package com.example.librarymanagementsystem.model.entities;

import com.example.librarymanagementsystem.core.configuration.Mapper;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mail {
    private String to;
    private String subject;
    private Map<String , Object> properties;
}

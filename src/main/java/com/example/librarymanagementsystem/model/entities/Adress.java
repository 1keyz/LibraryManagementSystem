package com.example.librarymanagementsystem.model.entities;


import com.example.librarymanagementsystem.model.entities.abstracts.AbstractDate;
import com.example.librarymanagementsystem.model.entities.abstracts.AbstractId;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "adress")
@Entity
@Builder
public class Adress extends AbstractId {
    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private int postalCode;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}

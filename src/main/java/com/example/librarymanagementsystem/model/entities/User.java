package com.example.librarymanagementsystem.model.entities;


import com.example.librarymanagementsystem.model.entities.abstracts.AbstractDate;
import com.example.librarymanagementsystem.model.enums.RoleEnum;
import com.example.librarymanagementsystem.model.enums.UserStatusLevel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User_table")
@Entity
public class User extends AbstractDate {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    /*@ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "User_table_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<RoleEnum> role;
     */
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(name = "penalty_amount")
    private int penaltyAmount;

    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Adress userAdress;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<BorrowedBook> borrowedBooks;

    /*@Enumerated(EnumType.STRING)
    @Column(name = "status_level")
    private UserStatusLevel statusLevel; // geliştirme aşamasında eğer kütüphane yetki kısmına geçersek kütüphane üye olma suresine göre
     */                                    // status level artırma gibi bir şey yapılabilir

}

package com.app.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email")
    private String email;
    
    @Column(name = "user_password")
    private String password;

    @Column(name = "user_mobileNumber")
    private String mobileNumber;

    private String gender;

    private String about;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "image_id")
//    private FileData image;

    
//    ROLE_ADMIN,ROLE_USER,ROLE_ALL
    private String roles; 
}
package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password; // Bu hash'lenmiş olacak
    private String role;     // doctor, receptionist, admin
}
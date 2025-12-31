package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Getter, Setter, toString ve equals metodlarını otomatik oluşturur
@NoArgsConstructor // Boş constructor'ı (public Patient() {}) otomatik oluşturur
@AllArgsConstructor // Tüm alanları içeren constructor'ı oluşturur
@Document(collection = "patients")
public class Patient {
    
    @Id
    private String id;
    private String name;
    private String tcNo;
    private String complaint; // Frontend'deki 'complaint' ile tam olarak aynı isimde

} // Sınıf burada kapanmalı!
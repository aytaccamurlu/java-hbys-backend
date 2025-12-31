package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "appointments") // MongoDB'de bu isimle tablo oluşacak
@Data // Getter, Setter ve ToString'i otomatik oluşturur
public class Appointment {
    @Id
    private String id;
    private String patientName;   // Hasta Adı
    private String tcNo;         // Hastanın TC Kimlik No
    private String doctorName;    // Randevu alınan Doktor
    private String appointmentDate; // Tarih
    private String appointmentTime; // Saat
    private String complaint;     // Şikayet Notu
    private String status;        // "Bekliyor", "Onaylandı", "Tamamlandı", "İptal"
}
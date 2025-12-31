package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    @Autowired
    private AppointmentRepository repository;

    // Tüm randevuları getir
    @GetMapping
    public List<Appointment> getAll() {
        return repository.findAll();
    }

    // Yeni Randevu Oluştur
    @PostMapping
    public Appointment create(@RequestBody Appointment appointment) {
        if (appointment.getStatus() == null) {
            appointment.setStatus("Bekliyor"); // İlk kayıt her zaman bekliyor olur
        }
        return repository.save(appointment);
    }

    // Durum Güncelleme (Doktor Onayı veya İptali için)
    @PatchMapping("/{id}/status")
    public Appointment updateStatus(@PathVariable String id, @RequestBody Map<String, String> statusUpdate) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Randevu bulunamadı: " + id));
        
        appointment.setStatus(statusUpdate.get("status"));
        return repository.save(appointment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}
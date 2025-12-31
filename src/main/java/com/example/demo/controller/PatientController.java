package com.example.demo.controller;

import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(
    origins = "*", 
    allowedHeaders = {"Content-Type", "Authorization", "X-Requested-With", "Accept", "Origin"},
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
    allowCredentials = "false"
)
public class PatientController {

    @Autowired
    private PatientRepository repository; // Burada adı 'repository'

    @GetMapping
    public List<Patient> getPatients() {
        return repository.findAll();
    }

    @PostMapping
    public Patient create(@RequestBody Patient patient) {
        // Loglama için:
        System.out.println("Gelen Veri -> İsim: " + patient.getName() + " | Şikayet: " + patient.getComplaint());
        
        // HATALI YER BURASIYDI: 'patientRepository' yerine 'repository' olmalı
        return repository.save(patient); 
    }
    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable String id, @RequestBody Patient patientDetails) {
        Patient patient = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Hasta bulunamadı: " + id));
        
        patient.setName(patientDetails.getName());
        patient.setTcNo(patientDetails.getTcNo());
        patient.setComplaint(patientDetails.getComplaint());
        
        return repository.save(patient);
    }
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable String id) {
        repository.deleteById(id);
    }
}
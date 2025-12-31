package com.example.demo.repository;

import com.example.demo.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<Patient, String> {
    // Tüm temel işlemler (save, findAll, deleteById) otomatik gelir.
}
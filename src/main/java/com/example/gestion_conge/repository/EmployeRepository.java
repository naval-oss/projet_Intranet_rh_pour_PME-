package com.example.gestion_conge.repository;

import com.example.gestion_conge.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
    Optional<Employe> findByUserUsername(String username);
}


package com.example.gestion_conge.repository;

import com.example.gestion_conge.entities.Conge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CongeRepository extends JpaRepository<Conge, Long> {
    List<Conge> findByEmployeUserUsername(String username);
}

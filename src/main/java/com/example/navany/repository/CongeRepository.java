package com.example.navany.repository;

import com.example.navany.entities.Conge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CongeRepository extends JpaRepository<Conge, Long> {
    List<Conge> findByEmployeUserUsername(String username);
}

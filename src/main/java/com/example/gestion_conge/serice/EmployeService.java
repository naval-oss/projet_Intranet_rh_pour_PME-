package com.example.gestion_conge.serice;

import com.example.gestion_conge.entities.Employe;
import com.example.gestion_conge.repository.EmployeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeService {

    private final EmployeRepository employeRepository;

    public EmployeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public Optional<Employe> findByUsername(String username) {
        return employeRepository.findByUserUsername(username);
    }

    public Employe save(Employe employe) {
        return employeRepository.save(employe);
    }
}

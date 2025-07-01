package com.example.gestion_conge.serice;


import com.example.gestion_conge.entities.Conge;
import com.example.gestion_conge.entities.Employe;
import com.example.gestion_conge.repository.CongeRepository;
import com.example.gestion_conge.repository.EmployeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CongeService {

    private final CongeRepository congeRepository;
    private final EmployeRepository employeRepository;

    public CongeService(CongeRepository congeRepository, EmployeRepository employeRepository) {
        this.congeRepository = congeRepository;
        this.employeRepository = employeRepository;
    }

    public List<Conge> findAll() {
        return congeRepository.findAll();
    }

    public List<Conge> findAllByUser(String username) {
        return congeRepository.findByEmployeUserUsername(username);
    }

    public Optional<Conge> findById(Long id) {
        return congeRepository.findById(id);
    }

    public Conge saveCongeForUser(Conge conge, String username) {
        Employe employe = employeRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé"));
        conge.setEmploye(employe);
        conge.setStatut("EN_ATTENTE");
        return congeRepository.save(conge);
    }

    public void deleteById(Long id) {
        congeRepository.deleteById(id);
    }
}

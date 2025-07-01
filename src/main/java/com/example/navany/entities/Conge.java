package com.example.navany.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "conges")
@Data @NoArgsConstructor @AllArgsConstructor
public class Conge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // Ex: Congé payé, Maladie

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private String motif;

    private String statut; // EN_ATTENTE, VALIDE, REJETE

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;
}


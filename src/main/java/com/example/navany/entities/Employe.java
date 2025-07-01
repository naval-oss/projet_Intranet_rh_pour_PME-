package com.example.navany.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "employes")
@Data @NoArgsConstructor @AllArgsConstructor
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String poste;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private Set<Conge> conges;

    @OneToMany(mappedBy = "destinataire", cascade = CascadeType.ALL)
    private Set<Notification> notifications;
}


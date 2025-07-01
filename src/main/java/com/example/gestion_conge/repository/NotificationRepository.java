package com.example.gestion_conge.repository;

import com.example.gestion_conge.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByDestinataireUserUsername(String username);
}

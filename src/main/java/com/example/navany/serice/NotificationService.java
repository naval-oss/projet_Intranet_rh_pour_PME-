package com.example.navany.serice;


import com.example.navany.entities.Notification;
import com.example.navany.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> findAllByUser(String username) {
        return notificationRepository.findByDestinataireUserUsername(username);
    }

    public Notification save(Notification notification) {
        notification.setDateEnvoi(LocalDateTime.now());
        return notificationRepository.save(notification);
    }
}


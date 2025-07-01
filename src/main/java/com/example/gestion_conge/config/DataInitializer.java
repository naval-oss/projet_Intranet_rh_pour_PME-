package com.example.gestion_conge.config;

import com.example.gestion_conge.entities.Role;
import com.example.gestion_conge.entities.User;
import com.example.gestion_conge.repository.RoleRepository;
import com.example.gestion_conge.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository,
                                      RoleRepository roleRepository,
                                      BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepository.findByName("ROLE_EMPLOYE") == null) {
                Role roleEmploye = new Role(null, "ROLE_EMPLOYE");
                roleRepository.save(roleEmploye);
            }

            if (userRepository.findByUsername("admin").isEmpty()) {
                Role roleEmploye = roleRepository.findByName("ROLE_EMPLOYE");

                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("1234")); // mot de passe : 1234
                admin.setEnabled(true);

                Set<Role> roles = new HashSet<>();
                roles.add(roleEmploye);
                admin.setRoles(roles);

                userRepository.save(admin);
                System.out.println("✅ Utilisateur admin créé (admin/1234)");
            }
        };
    }
}


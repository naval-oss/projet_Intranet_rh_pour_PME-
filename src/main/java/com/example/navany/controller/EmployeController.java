package com.example.navany.controller;

import com.example.navany.entities.Employe;
import com.example.navany.serice.EmployeService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employes")
public class EmployeController {

    private final EmployeService employeService;

    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {
        if (authentication == null) {
            return "redirect:/login"; // ou vers une page publique
        }
        String username = authentication.getName();
        Employe employe = employeService.findByUsername(username).orElse(null);
        model.addAttribute("employe", employe);
        return "employe/profile";
    }

    @GetMapping("/edit")
    public String editProfile(Authentication authentication, Model model) {
        if (authentication == null) {
            return "redirect:/login";
        }
        String username = authentication.getName();
        Employe employe = employeService.findByUsername(username).orElse(null);
        model.addAttribute("employe", employe);
        return "employe/edit";
    }

    @PostMapping("/edit")
    public String updateProfile(@ModelAttribute Employe employe) {
        employeService.save(employe);
        return "redirect:/employes/profile";
    }
}

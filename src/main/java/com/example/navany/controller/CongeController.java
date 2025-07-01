package com.example.navany.controller;


import com.example.navany.entities.Conge;
import com.example.navany.serice.CongeService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/conges")
public class CongeController {

    private final CongeService congeService;

    public CongeController(CongeService congeService) {
        this.congeService = congeService;
    }

    @GetMapping
    public String listConge(Authentication authentication, Model model) {
        String username = authentication.getName();
        List<Conge> conges = congeService.findAllByUser(username);
        model.addAttribute("conges", conges);
        return "conge/list";
    }

    @GetMapping("/nouveau")
    public String nouveauConge(Model model) {
        model.addAttribute("conge", new Conge());
        return "conge/form";
    }

    @PostMapping("/nouveau")
    public String saveConge(@ModelAttribute Conge conge, Authentication authentication) {
        String username = authentication.getName();
        congeService.saveCongeForUser(conge, username);
        return "redirect:/conges";
    }
}


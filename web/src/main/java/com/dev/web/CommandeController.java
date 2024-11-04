package com.dev.web;

import com.dev.services.ICommande;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class CommandeController {
    private ICommande commande;
    @GetMapping(name="commande", value="/commande")
    public String commandeController(){
        return commande.getCommande();
    }
}

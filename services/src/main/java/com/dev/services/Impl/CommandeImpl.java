package com.dev.services.Impl;

import com.dev.services.ICommande;
import org.springframework.stereotype.Service;

@Service
public class CommandeImpl implements ICommande {

    @Override
    public String getCommande() {
        return "Commande de vehicule";
    }
}

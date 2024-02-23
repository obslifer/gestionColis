package com.example.gestioncolis.service;

import com.example.gestioncolis.entities.Expediteur;

public interface ExpediteurService {
    public boolean create(Expediteur expediteur);
    public void update(String login , String password);

}

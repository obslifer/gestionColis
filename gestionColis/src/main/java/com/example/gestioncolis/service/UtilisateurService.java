package com.example.gestioncolis.service;

import com.example.gestioncolis.entities.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    public void create(Utilisateur utilisateur);
    public void update(Utilisateur utilisateur);
    public void delete(int cni);
    public Utilisateur getByCNI(int cni);
    public List<Utilisateur> getAll();
}

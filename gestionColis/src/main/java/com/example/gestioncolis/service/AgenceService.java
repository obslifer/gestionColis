package com.example.gestioncolis.service;

import com.example.gestioncolis.entities.Agence;

import java.util.List;

public interface AgenceService {
    public void create(Agence agence);
    public void update(Agence agence);
    public void delete(int id);
    public Agence getById(int id);
    public List<Agence> getAll();
}

package com.example.gestioncolis.service;

import com.example.gestioncolis.entities.Agence;
import com.example.gestioncolis.entities.Colis;

import java.sql.SQLException;
import java.util.List;

public interface AgenceService {
    public void create(Agence agence);
    public void update(Agence agence);
    public void delete(int id);
    public Agence getById(int id);
    public List<Agence> getAll();
    public List<Colis> getColisEnAttentePourAgence(int idAgence);
    public void signalerReceptionColis(List<Colis> colisList, int idAgence);
    public List<Colis> getColisEnAttenteDepartPourAgence(int idAgence);
    public void signalerDepartColis(List<Colis> colisList, int idAgence);
    public List<Colis> getColisQuittesPourAgence(int idAgence);
}

package com.example.gestioncolis.service;

import com.example.gestioncolis.entities.Colis;

import java.sql.SQLException;
import java.util.List;

public interface ColisService {
    public void create(Colis colis);
    public void update(Colis colis);
    public void delete(int numeroColis);
    public Colis getByNumeroColis(int numeroColis);
    public List<Colis> getAll();
    public List<Colis> getColisEnAttentePourAgence(int idAgence);
    public List<Colis> getColisEnAttenteDepartPourAgence(int idAgence);
    public List<Colis> getColisQuittesPourAgence(int idAgence);
}

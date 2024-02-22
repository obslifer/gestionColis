package com.example.gestioncolis.service;

import com.example.gestioncolis.entities.Relais;

import java.sql.SQLException;
import java.util.List;

public interface RelaisService {
    public void create(Relais relais);
    public void update(Relais relais);
    public void updateDateArrivee(Relais relais);
    public void updateDateDepart(Relais relais);
    public void delete(int numeroColis, int idAgence);
    public Relais getByColisAndAgence(int numeroColis, int idAgence);
    public List<Relais> getAll();
}

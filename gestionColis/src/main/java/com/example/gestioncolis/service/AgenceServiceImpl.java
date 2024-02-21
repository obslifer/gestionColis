package com.example.gestioncolis.service;

import com.example.gestioncolis.dao.AgenceDAO;
import com.example.gestioncolis.dao.ColisDAO;
import com.example.gestioncolis.dao.RelaisDAO;
import com.example.gestioncolis.entities.Agence;
import com.example.gestioncolis.entities.Colis;
import com.example.gestioncolis.entities.Relais;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class AgenceServiceImpl implements AgenceService {
    private AgenceDAO agenceDAO;
    private ColisDAO colisDAO;
    private RelaisDAO relaisDAO;

    public AgenceServiceImpl() throws SQLException {
        this.agenceDAO = new AgenceDAO();
        this.colisDAO = new ColisDAO();
        this.relaisDAO = new RelaisDAO();
    }

    @Override
    public void create(Agence agence) {
        try {
            agenceDAO.create(agence);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public void update(Agence agence) {
        try {
            agenceDAO.update(agence);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public void delete(int id) {
        try {
            agenceDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public Agence getById(int id) {
        try {
            return agenceDAO.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return null;
    }

    @Override
    public List<Agence> getAll() {
        try {
            return agenceDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return null;
    }

    @Override
    public List<Colis> getColisEnAttentePourAgence(int idAgence) {
        try {
            return colisDAO.getColisEnAttentePourAgence(idAgence);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public void signalerReceptionColis(int numeroColis, int idAgence) {
        try {
            Relais relais = new Relais(numeroColis, idAgence, new Timestamp(System.currentTimeMillis()), null);
            relaisDAO.update(relais);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}



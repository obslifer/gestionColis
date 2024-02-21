package com.example.gestioncolis.service;

import com.example.gestioncolis.dao.AgenceDAO;
import com.example.gestioncolis.entities.Agence;

import java.sql.SQLException;
import java.util.List;

public class AgenceServiceImpl implements AgenceService {
    private AgenceDAO agenceDAO;

    public AgenceServiceImpl() throws SQLException {
        this.agenceDAO = new AgenceDAO();
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
}



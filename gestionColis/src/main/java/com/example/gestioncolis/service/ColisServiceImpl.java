package com.example.gestioncolis.service;

import com.example.gestioncolis.dao.ColisDAO;
import com.example.gestioncolis.entities.Colis;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColisServiceImpl implements ColisService {
    private ColisDAO colisDAO;

    public ColisServiceImpl() throws SQLException {
        this.colisDAO = new ColisDAO();
    }

    @Override
    public void create(Colis colis) {
        try {
            colisDAO.create(colis);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public void update(Colis colis) {
        try {
            colisDAO.update(colis);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public void delete(int numeroColis) {
        try {
            colisDAO.delete(numeroColis);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public Colis getByNumeroColis(int numeroColis) {
        try {
            return colisDAO.getByNumeroColis(numeroColis);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return null;
    }

    @Override
    public List<Colis> getAll() {
        try {
            return colisDAO.getAll();
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
    public List<Colis> getColisEnAttenteDepartPourAgence(int idAgence) {
        try {
            return colisDAO.getColisEnAttenteDepartPourAgence(idAgence);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public List<Colis> getColisQuittesPourAgence(int idAgence) {
        try {
            return colisDAO.getColisQuittesPourAgence(idAgence);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
}



package com.example.gestioncolis.service;

import com.example.gestioncolis.dao.RelaisDAO;
import com.example.gestioncolis.entities.Relais;

import java.sql.SQLException;
import java.util.List;

public class RelaisServiceImpl implements RelaisService {
    private RelaisDAO relaisDAO;

    public RelaisServiceImpl() throws SQLException {
        this.relaisDAO = new RelaisDAO();
    }

    @Override
    public void create(Relais relais) {
        try {
            relaisDAO.create(relais);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public void update(Relais relais) {
        try {
            relaisDAO.update(relais);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public void updateDateArrivee(Relais relais) {
        try {
            relaisDAO.updateDateArrivee(relais);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void updateDateDepart(Relais relais) {
        try {
            relaisDAO.updateDateDepart(relais);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void delete(int numeroColis, int idAgence) {
        try {
            relaisDAO.delete(numeroColis, idAgence);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public Relais getByColisAndAgence(int numeroColis, int idAgence) {
        try {
            return relaisDAO.getByColisAndAgence(numeroColis, idAgence);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return null;
    }

    @Override
    public List<Relais> getAll() {
        try {
            return relaisDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return null;
    }
}


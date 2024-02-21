package com.example.gestioncolis.service;

import com.example.gestioncolis.dao.ColisDAO;
import com.example.gestioncolis.entities.Colis;

import java.sql.SQLException;
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
            // Gérer l'exception selon vos besoins
        }
    }

    @Override
    public void update(Colis colis) {
        try {
            colisDAO.update(colis);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon vos besoins
        }
    }

    @Override
    public void delete(int numeroColis) {
        try {
            colisDAO.delete(numeroColis);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon vos besoins
        }
    }

    @Override
    public Colis getByNumeroColis(int numeroColis) {
        try {
            return colisDAO.getByNumeroColis(numeroColis);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon vos besoins
        }
        return null;
    }

    @Override
    public List<Colis> getAll() {
        try {
            return colisDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon vos besoins
        }
        return null;
    }
}



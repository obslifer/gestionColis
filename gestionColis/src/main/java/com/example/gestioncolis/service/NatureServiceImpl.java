package com.example.gestioncolis.service;

import com.example.gestioncolis.dao.NatureDAO;
import com.example.gestioncolis.entities.Nature;

import java.sql.SQLException;
import java.util.List;

public class NatureServiceImpl implements NatureService {
    private NatureDAO natureDAO;

    public NatureServiceImpl() throws SQLException {
        this.natureDAO = new NatureDAO();
    }

    @Override
    public void create(Nature nature) {
        try {
            natureDAO.create(nature);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public void update(Nature nature) {
        try {
            natureDAO.update(nature);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public void delete(int id) {
        try {
            natureDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public Nature getById(int id) {
        try {
            return natureDAO.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return null;
    }

    @Override
    public List<Nature> getAll() {
        try {
            return natureDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return null;
    }
}



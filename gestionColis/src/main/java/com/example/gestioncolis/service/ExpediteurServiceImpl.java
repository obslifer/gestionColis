package com.example.gestioncolis.service;

import com.example.gestioncolis.dao.ExpediteurDAO;
import com.example.gestioncolis.entities.Expediteur;

import java.sql.SQLException;

public class ExpediteurServiceImpl implements ExpediteurService {
    ExpediteurDAO expediteurDAO=new ExpediteurDAO();

    public ExpediteurServiceImpl() throws SQLException {
    }

    @Override
    public boolean create(Expediteur expediteur){
        return this.expediteurDAO.create(expediteur);
    }
    public void update(String login , String password) {
        this.expediteurDAO.update(login,password);
    }
}

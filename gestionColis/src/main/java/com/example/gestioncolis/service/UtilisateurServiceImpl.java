package com.example.gestioncolis.service;

import com.example.gestioncolis.dao.UtilisateurDAO;
import com.example.gestioncolis.entities.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public class UtilisateurServiceImpl implements UtilisateurService {
    private UtilisateurDAO utilisateurDAO;

    public UtilisateurServiceImpl() throws SQLException {
        this.utilisateurDAO = new UtilisateurDAO();
    }

    @Override
    public void create(Utilisateur utilisateur) {
        try {
            utilisateurDAO.create(utilisateur);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public void update(Utilisateur utilisateur) {
        try {
            utilisateurDAO.update(utilisateur);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public void delete(int cni) {
        try {
            utilisateurDAO.delete(cni);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public Utilisateur getByCNI(int cni) {
        try {
            return utilisateurDAO.getByCNI(cni);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return null;
    }

    @Override
    public List<Utilisateur> getAll() {
        try {
            return utilisateurDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return null;
    }
}

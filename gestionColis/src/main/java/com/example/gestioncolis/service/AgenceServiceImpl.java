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
    public void signalerReceptionColis(List<Colis> colisList, int idAgence) {
        try {
            for (Colis colis : colisList) {
                Relais relais = new Relais(colis.getNumeroColis(), idAgence, new Timestamp(System.currentTimeMillis()), null);
                relaisDAO.updateDateArrivee(relais);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public void signalerDepartColis(List<Colis> colisList, int idAgence) {
        try {
            for (Colis colis : colisList) {
                Relais relais = new Relais(colis.getNumeroColis(), idAgence, null, new Timestamp(System.currentTimeMillis()));
                relaisDAO.updateDateDepart(relais);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Colis> getColisQuittesPourAgence(int idAgence) {
        try {
            return colisDAO.getColisQuittesPourAgence(idAgence);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon vos besoins
        }
        return null;
    }
}



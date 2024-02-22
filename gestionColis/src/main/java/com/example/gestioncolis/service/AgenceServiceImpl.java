package com.example.gestioncolis.service;

import com.example.gestioncolis.dao.AgenceDAO;
import com.example.gestioncolis.entities.Agence;
import com.example.gestioncolis.entities.Colis;
import com.example.gestioncolis.entities.Relais;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class AgenceServiceImpl implements AgenceService {
    private AgenceDAO agenceDAO;
    private RelaisServiceImpl relaisService;
    private ColisServiceImpl colisService;

    public AgenceServiceImpl() throws SQLException {
        this.agenceDAO = new AgenceDAO();
        this.colisService = new ColisServiceImpl();
        this.relaisService = new RelaisServiceImpl();
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
    public Agence getByLogin(String login) {
        try {
            return agenceDAO.getByLogin(login);
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
        return colisService.getColisEnAttentePourAgence(idAgence);
    }

    @Override
    public void signalerReceptionColis(List<Colis> colisList, int idAgence) {
        for (Colis colis : colisList) {
            Relais relais = new Relais(colis.getNumeroColis(), idAgence, new Timestamp(System.currentTimeMillis()), null);
            relaisService.updateDateArrivee(relais);
        }
    }

    @Override
    public List<Colis> getColisEnAttenteDepartPourAgence(int idAgence) {
        return colisService.getColisEnAttenteDepartPourAgence(idAgence);
    }

    @Override
    public void signalerDepartColis(List<Colis> colisList, int idAgence) {
        for (Colis colis : colisList) {
            Relais relais = new Relais(colis.getNumeroColis(), idAgence, null, new Timestamp(System.currentTimeMillis()));
            relaisService.updateDateDepart(relais);
        }
    }

    @Override
    public List<Colis> getColisQuittesPourAgence(int idAgence) {
        return colisService.getColisQuittesPourAgence(idAgence);
    }

    @Override
    public void enregistrerColis(Colis colis, int idAgenceActuelle, List<Agence> itineraire) {
        // Étape 1 : Créer le colis
        colisService.create(colis);

        // Étape 2 : Créer le relais associé à l'agence actuelle avec la date d'arrivée actuelle
        Relais relaisAgenceActuelle = new Relais(colis.getNumeroColis(), idAgenceActuelle, new Timestamp(System.currentTimeMillis()), null);
        relaisService.create(relaisAgenceActuelle);
        relaisService.updateDateArrivee(relaisAgenceActuelle);

        // Étape 3 : Créer les relais pour les agences constituant l'itinéraire avec des dates nulles
        for (Agence agence : itineraire) {
            if (agence.getId() != idAgenceActuelle) { // Ne crée pas de relais pour l'agence actuelle
                Relais relais = new Relais(colis.getNumeroColis(), agence.getId(), null, null);
                relaisService.create(relais);
            }
        }
    }
}



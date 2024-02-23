package com.example.gestioncolis.entities;

import java.sql.Timestamp;

public class SuiviColis2 {
    private String etat;
    private String agence;
    private Timestamp dateDepart;
    private Timestamp dateArrivee;

    public SuiviColis2(String etat, String agence, Timestamp dateDepart, Timestamp dateArrivee) {
        this.etat = etat;
        this.agence = agence;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public Timestamp getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Timestamp dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Timestamp getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Timestamp dateArrivee) {
        this.dateArrivee = dateArrivee;
    }
}

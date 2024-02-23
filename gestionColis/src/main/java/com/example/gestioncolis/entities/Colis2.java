package com.example.gestioncolis.entities;

public class Colis2 {
    private int numeroColis;
    private String type;
    private String CNIDestinataire;
    private String CNIExpediteur;
    private String etat;
    private String AgenceDestination;

    public Colis2(int numeroColis, String type, String CNIDestinataire, String CNIExpediteur, String etat, String agenceDestination) {
        this.numeroColis = numeroColis;
        this.type = type;
        this.CNIDestinataire = CNIDestinataire;
        this.CNIExpediteur = CNIExpediteur;
        this.etat = etat;
        AgenceDestination = agenceDestination;
    }

    public int getNumeroColis() {
        return numeroColis;
    }

    public void setNumeroColis(int numeroColis) {
        this.numeroColis = numeroColis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCNIDestinataire() {
        return CNIDestinataire;
    }

    public void setCNIDestinataire(String CNIDestinataire) {
        this.CNIDestinataire = CNIDestinataire;
    }

    public String getCNIExpediteur() {
        return CNIExpediteur;
    }

    public void setCNIExpediteur(String CNIExpediteur) {
        this.CNIExpediteur = CNIExpediteur;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getAgenceDestination() {
        return AgenceDestination;
    }

    public void setAgenceDestination(String agenceDestination) {
        AgenceDestination = agenceDestination;
    }
}

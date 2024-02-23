package com.example.gestioncolis.entities;

public class Nature {
    private int idNature;
    private String type;

    public Nature(int idNature, String type) {
        this.idNature = idNature;
        this.type = type;
    }

    public Nature(String type)
    {
        this.type = type;
    }

    public int getIdNature() {
        return idNature;
    }

    public void setIdNature(int idNature) {
        this.idNature = idNature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


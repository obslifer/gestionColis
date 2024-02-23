package com.example.gestioncolis.service;

import com.example.gestioncolis.entities.Colis2;
import com.example.gestioncolis.entities.SuiviColis2;
import javafx.collections.ObservableList;

public interface PageExpediteurService {
    public ObservableList<Colis2> afficher();
    public ObservableList<SuiviColis2> afficher2();
}

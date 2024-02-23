package com.example.gestioncolis.service;

import com.example.gestioncolis.dao.PageExpediteurDAO;
import com.example.gestioncolis.entities.Colis2;
import com.example.gestioncolis.entities.SuiviColis2;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class PageExpediteurServiceImpl implements PageExpediteurService{
    private PageExpediteurDAO pageExpediteurDAO=new PageExpediteurDAO();

    public PageExpediteurServiceImpl() throws SQLException {
    }

    public ObservableList<Colis2> afficher(){
        return this.pageExpediteurDAO.afficher();
    }

    public ObservableList<SuiviColis2> afficher2(){
        return this.pageExpediteurDAO.afficher2();
    }
}

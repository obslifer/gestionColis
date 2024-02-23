package com.example.gestioncolis.dao;

import com.example.gestioncolis.entities.Colis2;
import com.example.gestioncolis.entities.SuiviColis2;
import com.example.gestioncolis.service.PasswordHacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class PageExpediteurDAO extends ConnectionDAO{
    public PageExpediteurDAO() throws SQLException {
        super();
    }
    public ObservableList<Colis2> afficher(){
        ObservableList<Colis2> colis = FXCollections.observableArrayList();

        try{
            Statement statement = getConnection().createStatement();
            Statement statement2 = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT c.numero_colis, n.type, exp.nom as expediteur, dest.nom as destinataire, c.etat, a.quartier, a.ville FROM utilisateur u\n" +
                    "JOIN colis c\n" +
                    "JOIN nature n\n" +
                    "JOIN agence a\n" +
                    "Join utilisateur exp ON exp.CNI = c.CNI_expediteur\n" +
                    "Join utilisateur dest ON dest.CNI = c.CNI_destinataire\n" +
                    "where c.type=n.id_nature and a.id=c.id_agence_destination and u.cni in(c.CNI_expediteur, c.CNI_destinataire) and u.login='"+PasswordHacher.getSession()+"'");

            while (resultSet.next()) {
                int numero = resultSet.getInt("numero_colis");
                String dest = resultSet.getString("destinataire");
                String exp = resultSet.getString("expediteur");
                String etat = resultSet.getString("etat");
                String quartier = resultSet.getString("a.quartier");
                String ville = resultSet.getString("a.ville");
                String type = resultSet.getString("n.type");


                Colis2 uncolis = new Colis2(numero,type,dest,exp,etat,ville+"-"+quartier);
                colis.add(uncolis);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colis;
    }

    public ObservableList<SuiviColis2> afficher2(){
        ObservableList<SuiviColis2> colis = FXCollections.observableArrayList();

        try{
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT s.etat, s.date_arrivee, s.date_depart, a.quartier, a.ville FROM suivi_colis s, agence a where s.id_agence=a.id and s.numero_colis="+ PasswordHacher.getNumeroColis() +" and s.date_arrivee IS NOT NULL order by s.date_arrivee desc");

            while (resultSet.next()) {
                String etat = resultSet.getString("s.etat");
                Timestamp dateArrivee = resultSet.getTimestamp("s.date_arrivee");
                Timestamp dateDepart = resultSet.getTimestamp("s.date_depart");
                String quartier = resultSet.getString("a.quartier");
                String ville = resultSet.getString("a.ville");

                SuiviColis2 uncolis = new SuiviColis2(etat,ville+"-"+quartier,dateDepart,dateArrivee);
                colis.add(uncolis);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colis;
    }
}

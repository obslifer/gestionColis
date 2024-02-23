package com.example.gestioncolis.dao;

import com.example.gestioncolis.entities.Expediteur;
import com.example.gestioncolis.service.PasswordHacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExpediteurDAO extends ConnectionDAO{
    public ExpediteurDAO() throws SQLException {
        super();
    }
    public boolean create(Expediteur expediteur){
        boolean retour=false;
        List<String> listeLogins = new ArrayList<>();

        try {
            // Créer une requête SQL pour récupérer les noms des étudiants
            String sql = "SELECT login FROM admin";
            String sql2 = "SELECT login FROM agence";
            String sql3 = "SELECT login FROM utilisateur";

            Statement statement = getConnection().createStatement();
            Statement statement2 = getConnection().createStatement();
            Statement statement3 = getConnection().createStatement();

            // Exécuter la requête SQL
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSet resultSet2 = statement2.executeQuery(sql2);
            ResultSet resultSet3 = statement3.executeQuery(sql3);

            // Parcourir les résultats de la requête
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                listeLogins.add(login);
            }
            while (resultSet2.next()) {
                String login = resultSet2.getString("login");
                listeLogins.add(login);
            }
            while (resultSet3.next()) {
                String login = resultSet3.getString("login");
                listeLogins.add(login);
            }

            // Fermer les ressources
            resultSet.close();
            resultSet2.close();
            resultSet3.close();
            statement.close();
            statement2.close();
            statement3.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean trouve=false;
        for(int i=0;i<listeLogins.size();i++){
            if(Objects.equals(listeLogins.get(i), expediteur.getLogin())){
                trouve=true;
                break;
            }
        }


        if(Objects.equals(expediteur.getLogin(), "admin1")){
            retour=false;
        } else if (trouve==true) {
            retour=false;
        } else
        {
            retour=true;
            try {
                PreparedStatement ps = getConnection().prepareStatement("INSERT INTO utilisateur() VALUES(?,?,?,?,?,?,?)");
                ps.setInt(1, expediteur.getCni());
                ps.setInt(3, expediteur.getTel());
                ps.setString(4, expediteur.getEmail());
                ps.setString(5, expediteur.getAdresse());
                ps.setString(6, expediteur.getLogin());
                ps.setString(7, PasswordHacher.hacher(expediteur.getPassword()));
                ps.setString(2, expediteur.getNom());

                ps.executeUpdate();
                getConnection().close();
            } catch (SQLException ex) {
                Logger.getLogger(ExpediteurDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retour;
    }

    public void update(String login , String password){
        try {
            PreparedStatement ps = getConnection().prepareStatement("update utilisateur set login=?, password=? where login ='"+ PasswordHacher.getSession()+"'");
           ps.setString(1, login);
            ps.setString(2, PasswordHacher.hacher(password));

            ps.executeUpdate();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(ExpediteurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

package com.example.gestioncolis.dao;

import com.example.gestioncolis.entities.ShowMessage;
import com.example.gestioncolis.service.PasswordHacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO extends ConnectionDAO{
    public LoginDAO() throws SQLException {
        super();
    }


    public int checkCredentials(String login, String password){
        int retour=0;
        String sql = "select count(*) AS count from admin where login=? and password=?";
        String sql2 = "select count(*) AS count from agence where login=? and password=?";
        String sql3 = "select count(*) AS count from utilisateur where login=? and password=?";
        try {
            // Création de l'objet PreparedStatement pour exécuter la requête avec des paramètres
            PreparedStatement statement = getConnection().prepareStatement(sql);
            PreparedStatement statement2 = getConnection().prepareStatement(sql2);
            PreparedStatement statement3 = getConnection().prepareStatement(sql3);

            // Attribution de la valeur du paramètre (âge seuil)
            statement.setString(1, login);
            statement.setString(2, PasswordHacher.hacher(password));

            statement2.setString(1, login);
            statement2.setString(2, PasswordHacher.hacher(password));

            statement3.setString(1, login);
            statement3.setString(2, PasswordHacher.hacher(password));

            // Exécution de la requête et récupération du résultat
            ResultSet resultSet = statement.executeQuery();
            ResultSet resultSet2 = statement2.executeQuery();
            ResultSet resultSet3 = statement3.executeQuery();


            // Récupération du nombre d'étudiants
            int count = 0;
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
            int count2 = 0;
            if (resultSet2.next()) {
                count2 = resultSet2.getInt("count");
            }
            int count3 = 0;
            if (resultSet3.next()) {
                count3 = resultSet3.getInt("count");
            }

            // Fermeture des ressources
            resultSet.close();
            resultSet2.close();
            resultSet3.close();
            statement.close();
            statement2.close();
            statement3.close();
            getConnection().close();
            // Retour du résultat

            if(count3>0){
                retour= 3;
            } else if (count2>0) {
                retour= 2;
            }
            else if(count>0){
                retour= 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ShowMessage.showMessage(e.getMessage());
            return 0;
        }
        return retour;
    }
}

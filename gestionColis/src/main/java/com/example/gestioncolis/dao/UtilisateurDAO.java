package com.example.gestioncolis.dao;

import com.example.gestioncolis.entities.Utilisateur;
import com.example.gestioncolis.service.PasswordHacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {
    private final Connection connection;

    public UtilisateurDAO() throws SQLException {
        this.connection = DatabaseConfig.getConnection();
    }

    public void create(Utilisateur utilisateur) throws SQLException {
        String query = "INSERT INTO utilisateur (nom, tel, email, adresse, login, password, CNI) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, utilisateur.getNom());
            statement.setInt(2, utilisateur.getTel());
            statement.setString(3, utilisateur.getEmail());
            statement.setString(4, utilisateur.getAdresse());
            statement.setString(5, utilisateur.getLogin());
            statement.setString(6, PasswordHacher.hacher(utilisateur.getPassword()));
            statement.setInt(7, utilisateur.getCNI());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                utilisateur.setCNI(rs.getInt(1));
            }
        }
    }

    public void update(Utilisateur utilisateur) throws SQLException {
        String query = "UPDATE utilisateur SET nom = ?, tel = ?, email = ?, adresse = ?, login = ?, password = ? WHERE CNI = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, utilisateur.getNom());
            statement.setInt(2, utilisateur.getTel());
            statement.setString(3, utilisateur.getEmail());
            statement.setString(4, utilisateur.getAdresse());
            statement.setString(5, utilisateur.getLogin());
            statement.setString(6, utilisateur.getPassword());
            statement.setInt(7, utilisateur.getCNI());
            statement.executeUpdate();
        }
    }

    public void delete(int cni) throws SQLException {
        String query = "DELETE FROM utilisateur WHERE CNI = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cni);
            statement.executeUpdate();
        }
    }

    public Utilisateur getByCNI(int cni) throws SQLException {
        String query = "SELECT * FROM utilisateur WHERE CNI = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cni);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractUtilisateurFromResultSet(resultSet);
            }
        }
        return null;
    }

    public List<Utilisateur> getAll() throws SQLException {
        List<Utilisateur> utilisateurList = new ArrayList<>();
        String query = "SELECT * FROM utilisateur";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Utilisateur utilisateur = extractUtilisateurFromResultSet(resultSet);
                utilisateurList.add(utilisateur);
            }
        }
        return utilisateurList;
    }

    private Utilisateur extractUtilisateurFromResultSet(ResultSet resultSet) throws SQLException {
        Utilisateur utilisateur = new Utilisateur(resultSet.getInt("CNI"), resultSet.getString("nom"), resultSet.getInt("tel"), resultSet.getString("email"), resultSet.getString("adresse"), resultSet.getString("login"), resultSet.getString("password"));
        return utilisateur;
    }
}

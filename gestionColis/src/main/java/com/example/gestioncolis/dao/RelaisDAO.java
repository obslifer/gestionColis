package com.example.gestioncolis.dao;

import com.example.gestioncolis.entities.Relais;
import java.sql.Timestamp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelaisDAO {
    private final Connection connection;

    public RelaisDAO() throws SQLException {
        this.connection = DatabaseConfig.getConnection();
    }

    public void create(Relais relais) throws SQLException {
        String query = "INSERT INTO relais (numero_colis, id_agence, date_arrivee, date_depart) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, relais.getNumeroColis());
            statement.setInt(2, relais.getIdAgence());
            statement.setTimestamp(3, relais.getDateArrivee());
            statement.setTimestamp(4, relais.getDateDepart());
            statement.executeUpdate();
        }
    }

    public void update(Relais relais) throws SQLException {
        String query = "UPDATE relais SET date_arrivee = ?, date_depart = ? WHERE numero_colis = ? AND id_agence = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, relais.getDateArrivee());
            statement.setTimestamp(2, relais.getDateDepart());
            statement.setInt(3, relais.getNumeroColis());
            statement.setInt(4, relais.getIdAgence());
            statement.executeUpdate();
        }
    }

    public void updateDateArrivee(Relais relais) throws SQLException {
        String query = "UPDATE relais SET date_arrivee = ? WHERE numero_colis = ? AND id_agence = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, relais.getDateArrivee());
            statement.setInt(2, relais.getNumeroColis());
            statement.setInt(3, relais.getIdAgence());
            statement.executeUpdate();
        }
    }

    public void updateDateDepart(Relais relais) throws SQLException {
        String query = "UPDATE relais SET date_depart = ? WHERE numero_colis = ? AND id_agence = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, relais.getDateDepart());
            statement.setInt(2, relais.getNumeroColis());
            statement.setInt(3, relais.getIdAgence());
            statement.executeUpdate();
        }
    }

    public void delete(int numeroColis, int idAgence) throws SQLException {
        String query = "DELETE FROM relais WHERE numero_colis = ? AND id_agence = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, numeroColis);
            statement.setInt(2, idAgence);
            statement.executeUpdate();
        }
    }

    public Relais getByColisAndAgence(int numeroColis, int idAgence) throws SQLException {
        String query = "SELECT * FROM relais WHERE numero_colis = ? AND id_agence = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, numeroColis);
            statement.setInt(2, idAgence);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractRelaisFromResultSet(resultSet);
            }
        }
        return null;
    }

    public List<Relais> getAll() throws SQLException {
        List<Relais> relaisList = new ArrayList<>();
        String query = "SELECT * FROM relais";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Relais relais = extractRelaisFromResultSet(resultSet);
                relaisList.add(relais);
            }
        }
        return relaisList;
    }

    private Relais extractRelaisFromResultSet(ResultSet resultSet) throws SQLException {
        Relais relais = new Relais(resultSet.getInt("numero_colis"), resultSet.getInt("id_agence"), resultSet.getTimestamp("date_arrivee"), resultSet.getTimestamp("date_depart"));
        return relais;
    }
}

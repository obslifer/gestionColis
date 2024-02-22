package com.example.gestioncolis.service;

import com.example.gestioncolis.entities.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TextField tfirstname;
    @FXML
    private TextField tlastname;
    @FXML
    private TextField tcourse;
    @FXML
    private TextField tphone;
    @FXML
    private Label success;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        success.setVisible(false);
    }




}
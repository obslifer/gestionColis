package com.example.gestioncolis.service;

import com.example.gestioncolis.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateController {

    @FXML
    private Button bsend;

    @FXML
    private Label labelErreurLogin;

    @FXML
    private TextField tlogin;

    @FXML
    private PasswordField tpassword;

    @FXML
    void bouttonClick(ActionEvent event) throws SQLException {
        ExpediteurService expediteurService=new ExpediteurServiceImpl();
        expediteurService.update(tlogin.getText(), tpassword.getText());

        PasswordHacher.setSession(tlogin.getText());

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pageExpediteur.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            Stage stage = (Stage) bsend.getScene().getWindow();
            stage.setTitle("Colis Manager");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

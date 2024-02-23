package com.example.gestioncolis.service;

import com.example.gestioncolis.HelloApplication;
import com.example.gestioncolis.entities.Expediteur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SigninPageController {

    @FXML
    private Button bsend;

    @FXML
    private TextField tadresse;

    @FXML
    private TextField tcni;

    @FXML
    private TextField temail;

    @FXML
    private TextField tlogin;

    @FXML
    private TextField tnom;

    @FXML
    private TextField tpassword;

    @FXML
    private TextField ttel;

    @FXML
    private Label labelErreurLogin;

    @FXML
    void bouttonClick(ActionEvent event) throws SQLException {
        Expediteur expediteur=new Expediteur(Integer.parseInt(tcni.getText()),tnom.getText(),Integer.parseInt(ttel.getText()),temail.getText(),tadresse.getText(),tlogin.getText(),tpassword.getText());
        ExpediteurService expediteurService=new ExpediteurServiceImpl();
        boolean retour=expediteurService.create(expediteur);
        if(retour==false){
            labelErreurLogin.setVisible(true);
        }
        else{
            labelErreurLogin.setVisible(false);

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginPage.fxml"));
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

    @FXML
    void connecter(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginPage.fxml"));
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

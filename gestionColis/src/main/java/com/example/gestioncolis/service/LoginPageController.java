package com.example.gestioncolis.service;

import com.example.gestioncolis.HelloApplication;
import com.example.gestioncolis.entities.Session;
import com.example.gestioncolis.entities.ShowMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginPageController {

    @FXML
    private Button bsend;

    @FXML
    private Label labelCreerCompte;

    @FXML
    private TextField tlogin;

    @FXML
    private PasswordField tpassword;

    @FXML
    private Label labelErreur;

    public void bouttonClick(ActionEvent event) throws SQLException {
        LoginService loginService = new LoginServiceImpl();
        int retour = loginService.checkCredentials(tlogin.getText(), tpassword.getText());
        PasswordHacher.setSession(tlogin.getText());
        if (retour == 1){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
                Stage stage = (Stage) bsend.getScene().getWindow();
                stage.setTitle("Colis Manager");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (retour==2) {
            try {
                Session.login = tlogin.getText();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EnregistrerColis.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1195, 602);
                Stage stage = (Stage) bsend.getScene().getWindow();
                stage.setTitle("Colis Manager");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (retour==3) {
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
        else{
            labelErreur.setVisible(true);
        }
    }

    public void creer(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signinPage.fxml"));
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

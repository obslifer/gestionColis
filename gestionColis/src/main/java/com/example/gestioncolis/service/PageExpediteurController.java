package com.example.gestioncolis.service;

import com.example.gestioncolis.HelloApplication;
import com.example.gestioncolis.entities.Colis;
import com.example.gestioncolis.entities.Colis2;
import com.example.gestioncolis.entities.SuiviColis2;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class PageExpediteurController{

    @FXML
    private TableColumn<Colis, LocalDate> dateArriveeColumn;

    @FXML
    private TableColumn<Colis, LocalDate> dateDepartColumn;

    @FXML
    private TableColumn<Colis, Integer> agenceColumn;

    @FXML
    private TableColumn<Colis, Integer> agenceDestinationColumn;

    @FXML
    private Button bexit;

    @FXML
    private Button bedit;

    @FXML
    private TableColumn<Colis, Integer> destinataireColumn;

    @FXML
    private TableColumn<Colis, String> etatColumn;

    @FXML
    private Label labelEssai;

    @FXML
    private TableColumn<Colis, Integer> numeroColumn;

    @FXML
    private TableColumn<Colis, Integer> expediteurColumn;

    @FXML
    private Pane panel;

    @FXML
    private TableView<Colis2> tableView;

    @FXML
    private TableView<SuiviColis2> tableview2;

    @FXML
    private TableColumn<Colis, Integer> typeColumn;

    @FXML
    private VBox vbox;

    @FXML
    void exit(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            Stage stage = (Stage) bexit.getScene().getWindow();
            stage.setTitle("Colis Manager");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void edit(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("update.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            Stage stage = (Stage) bedit.getScene().getWindow();
            stage.setTitle("Colis Manager");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void initialize() {
        try {

            numeroColumn.setCellValueFactory(new PropertyValueFactory<Colis, Integer>("numeroColis"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<Colis, Integer>("type"));
            destinataireColumn.setCellValueFactory(new PropertyValueFactory<Colis, Integer>("CNIDestinataire"));
            expediteurColumn.setCellValueFactory(new PropertyValueFactory<Colis, Integer>("CNIExpediteur"));
            agenceDestinationColumn.setCellValueFactory(new PropertyValueFactory<Colis, Integer>("AgenceDestination"));

            /*ObservableList<Colis> colis = FXCollections.observableArrayList();
            colis.add(new Colis(1, 1, 1));
            colis.add(new Colis(2, 2, 2));*/
            PageExpediteurService pageExpediteurService=new PageExpediteurServiceImpl();
            ObservableList<Colis2> colis= pageExpediteurService.afficher();

            tableView.setItems(colis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void consulter(ActionEvent event) {
        TableView.TableViewSelectionModel<Colis2> selectionModel = tableView.getSelectionModel();
        Colis2 colisSelectionne = selectionModel.getSelectedItem();
        PasswordHacher.setNumeroColis(colisSelectionne.getNumeroColis());
        if (colisSelectionne != null) {
            try {

                etatColumn.setCellValueFactory(new PropertyValueFactory<Colis, String>("etat"));
                agenceColumn.setCellValueFactory(new PropertyValueFactory<Colis, Integer>("agence"));
                dateArriveeColumn.setCellValueFactory(new PropertyValueFactory<Colis, LocalDate>("dateArrivee"));
                dateDepartColumn.setCellValueFactory(new PropertyValueFactory<Colis, LocalDate>("dateDepart"));


                PageExpediteurService pageExpediteurService=new PageExpediteurServiceImpl();
                ObservableList<SuiviColis2> colis2= pageExpediteurService.afficher2();

                tableview2.setItems(colis2);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }





    }

}

package com.example.gestioncolis;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EnregistrerColisController {
    @FXML
    public Button btnValider;
    @FXML
    public javafx.scene.control.ListView ListView;

    @FXML
    private TableColumn<?, ?> TableColumnAgenceDestination;

    @FXML
    private TableColumn<?, ?> TableColumnDestinataire;

    @FXML
    private TableColumn<?, ?> TableColumnEtat;

    @FXML
    private TableColumn<?, ?> TableColumnExpediteur;

    @FXML
    private TableColumn<?, ?> TableColumnNumeroColis;

    @FXML
    private TableColumn<?, ?> TableColumnType;

    @FXML
    private TableView<?> TableView;

    @FXML
    private TextField TextFieldAgenceDestination;

    @FXML
    private TextField TextFieldDestinataire;

    @FXML
    private TextField TextFieldEtat;

    @FXML
    private TextField TextFieldExpediteur;

    @FXML
    private TextField TextFieldType;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnAnnuler;

    @FXML
    private TextField textFieldNumeroColis;

}

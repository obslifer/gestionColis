package com.example.gestioncolis;

import com.example.gestioncolis.entities.*;
import com.example.gestioncolis.service.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class EnregistrerColisController implements Initializable {

    public Button btnAnnuler2;
    public Button btnColisRecus;
    @FXML
    private AnchorPane gestionColisPane;

    @FXML
    private TextField textFieldNumeroColis;

    @FXML
    private ComboBox<Nature> ComboBoxType;

    @FXML
    private TextField TextFieldDestinataire;

    @FXML
    private TextField TextFieldExpediteur;

    @FXML
    private TextField TextFieldEtat;

    @FXML
    private ComboBox<Agence> ComboBoxAgenceDestination;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnAnnuler;

    @FXML
    private Button btnColisEnvoyé;

    @FXML
    private Button btnAnnuler1;

    @FXML
    private AnchorPane ittinerairePane;

    @FXML
    private ListView<Agence> ListView;

    @FXML
    private Button btnValider;

    @FXML
    private TableView<?> TableView;

    @FXML
    private TableColumn<?, ?> TableColumnNumeroColis;

    @FXML
    private TableColumn<?, ?> TableColumnType;

    @FXML
    private TableColumn<?, ?> TableColumnDestinataire;

    @FXML
    private TableColumn<?, ?> TableColumnExpediteur;

    @FXML
    private TableColumn<?, ?> TableColumnEtat;

    @FXML
    private TableColumn<?, ?> TableColumnAgenceDestination;

    @FXML
    private TableColumn<?, ?> TableColumnDateDepart;

    @FXML
    private TableColumn<?, ?> TableColumnDateArrivé;

    @FXML
    private TableColumn<?, ?> TableColumnSelection;

    @FXML
    private RadioButton radioButtonSortie;

    @FXML
    private RadioButton radioButtonEnStock;

    @FXML
    private RadioButton radioButtonAttendus;

    @FXML
    private Button deconnexion;

    @FXML
    private ListView<Colis> ListViewColis;

    private ColisServiceImpl colisService;

    AgenceServiceImpl agenceServiceImpl;
    NatureServiceImpl natureServiceImpl;
    UtilisateurServiceImpl utilisateurServiceImpl;
    private Colis colis;
    private Agence sessionAgence;
    private List<Agence> agences;

    @FXML
    void initialize() throws SQLException {

    }

    @FXML
    void deconnecter(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            Stage stage = (Stage) deconnexion.getScene().getWindow();
            stage.setTitle("Colis Manager");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onBtnAjouterClicked() {
        if (!ComboBoxType.getSelectionModel().isEmpty() &&
            !TextFieldDestinataire.getText().isEmpty() && !TextFieldExpediteur.getText().isEmpty() &&
            !ComboBoxAgenceDestination.getSelectionModel().isEmpty()) {

            // Récupérer l'agence de destination sélectionnée
            Agence agenceDestination = ComboBoxAgenceDestination.getSelectionModel().getSelectedItem();
            // Récupérer le type de colis sélectionné
            Nature typeColis = ComboBoxType.getSelectionModel().getSelectedItem();

            // Vérifier ou créer l'utilisateur destinataire
            Utilisateur destinataire = verifierOuCreerUtilisateur(TextFieldDestinataire.getText(), "destinataire");
            // Vérifier ou créer l'utilisateur expéditeur
            Utilisateur expediteur = verifierOuCreerUtilisateur(TextFieldExpediteur.getText(), "expediteur");

            // Si les utilisateurs ont été créés ou existent déjà, procéder à la création du colis
            if (destinataire != null && expediteur != null) {
                // Créer l'objet Colis avec les informations saisies
                colis = new Colis(0,
                        typeColis.getIdNature(),
                        destinataire.getCNI(),
                        expediteur.getCNI(),
                        "En attente de transit",
                        agenceDestination.getId());
                // Afficher le pane d'itinéraire
                ittinerairePane.setVisible(true);
                chargerAgences(agenceDestination.getId());
            } else {
                ShowMessage.showMessage("erreur1");
            }
        } else {
            ShowMessage.showMessage("erreur2");
        }
    }

    // Méthode pour charger toutes les agences dans la ListView
    private void chargerAgences(int destId) {
        try {
            // Récupérer toutes les agences en utilisant AgenceServiceImpl
            agenceServiceImpl = new AgenceServiceImpl();
            List<Agence> agences = agenceServiceImpl.getAll();

            // Effacer la ListView
            ListView.getItems().clear();

            // Créez une usine de cellules personnalisée pour afficher uniquement les informations nécessaires de l'agence
            ListView.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(Agence agence, boolean empty) {
                    super.updateItem(agence, empty);

                    if (empty || agence == null) {
                        setText(null);
                    } else {
                        // Affichez uniquement les informations souhaitées de l'agence
                        setText("ID: " + agence.getId() + ", Quartier: " + agence.getQuartier() + ", Ville: " + agence.getVille());
                    }
                }
            });

            // Ajouter chaque agence à la ListView
            for (Agence agence : agences) {
                if ((agence.getId() != sessionAgence.getId()) && (agence.getId() != destId)) {
                    ListView.getItems().add(agence);
                }
            }
        } catch (NullPointerException e) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onBtnValiderClicked() throws SQLException {
        // Méthode pour gérer le clic sur le bouton "Valider L'Itinéraire"
        // Vérifier si un objet Colis a été créé
        if (colis != null) {
            // Récupérer les agences sélectionnées dans la ListView
            ObservableList<Agence> selectedAgences = ListView.getSelectionModel().getSelectedItems();
            List<Agence> agenceList = new ArrayList<>(selectedAgences);

            // on enregistre le colis et son ittineraire
            agenceServiceImpl = new AgenceServiceImpl();
            agenceServiceImpl.enregistrerColis(colis, sessionAgence.getId(), agenceList);

            // Masquer l'anchorPane d'itinéraire
            ittinerairePane.setVisible(false);
            colis = null;
        } else {
            // erreur
            ShowMessage.showMessage("erreur3");
        }
    }

    // Méthode pour charger les options de l'agence de destination depuis la base de données
    private void chargerOptionsAgenceDestination() {
        List<Agence> agences = agenceServiceImpl.getAll();

        // Créez une usine de cellules personnalisée pour afficher le quartier et la ville
        Callback<ListView<Agence>, ListCell<Agence>> agenceCellFactory = new Callback<>() {
            @Override
            public ListCell<Agence> call(ListView<Agence> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Agence agence, boolean empty) {
                        super.updateItem(agence, empty);
                        if (empty || agence == null) {
                            setText(null);
                        } else {
                            setText(agence.getQuartier() + ", " + agence.getVille());
                        }
                    }
                };
            }
        };

        // Définissez l'usine de cellules personnalisée pour la ComboBox
        ComboBoxAgenceDestination.setCellFactory(agenceCellFactory);
        // Définissez également l'usine de cellules pour la liste déroulante
        ComboBoxAgenceDestination.setButtonCell(agenceCellFactory.call(null));

        if (agences != null) {
            for (Agence agence : agences) {
                if(agence.getId() != sessionAgence.getId()) {
                    ComboBoxAgenceDestination.getItems().add(agence);
                }
            }
        }
    }

    // Méthode pour charger les options de type de colis depuis la base de données
    private void chargerOptionsTypeColis() {
        List<Nature> types = natureServiceImpl.getAll();

        // Créez une usine de cellules personnalisée pour afficher le type de colis
        Callback<ListView<Nature>, ListCell<Nature>> typeCellFactory = new Callback<>() {
            @Override
            public ListCell<Nature> call(ListView<Nature> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Nature type, boolean empty) {
                        super.updateItem(type, empty);
                        if (empty || type == null) {
                            setText(null);
                        } else {
                            setText(type.getType());
                        }
                    }
                };
            }
        };

        // Définissez l'usine de cellules personnalisée pour la ComboBox
        ComboBoxType.setCellFactory(typeCellFactory);
        // Définissez également l'usine de cellules pour la liste déroulante
        ComboBoxType.setButtonCell(typeCellFactory.call(null));

        if (types != null) {
            for (Nature type : types) {
                ComboBoxType.getItems().add(type);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ittinerairePane.setVisible(false);
        ListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ListViewColis.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ToggleGroup toggleGroup = new ToggleGroup();
        radioButtonSortie.setToggleGroup(toggleGroup);
        radioButtonEnStock.setToggleGroup(toggleGroup);
        radioButtonAttendus.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RadioButton selectedRadioButton = (RadioButton) newValue;
                String text = selectedRadioButton.getText();

                // Appeler la méthode appropriée en fonction du bouton radio sélectionné
                switch (text) {
                    case "Sortie":
                        afficherColis(colisService.getColisQuittesPourAgence(sessionAgence.getId()));
                        break;
                    case "En Stock":
                        afficherColis(colisService.getColisEnAttenteDepartPourAgence(sessionAgence.getId()));
                        break;
                    case "Attendus":
                        afficherColis(colisService.getColisEnAttentePourAgence(sessionAgence.getId()));
                        break;
                    default:
                        break;
                }
            }
        });

        try {
            natureServiceImpl = new NatureServiceImpl();
            agenceServiceImpl = new AgenceServiceImpl();
            utilisateurServiceImpl = new UtilisateurServiceImpl();
            colisService = new ColisServiceImpl();

            sessionAgence = agenceServiceImpl.getByLogin(Session.login);
            //sessionAgence = agenceServiceImpl.getById(1);

            chargerOptionsAgenceDestination();
            chargerOptionsTypeColis();
        } catch (SQLException e) {
            ShowMessage.showMessage("werr");
            throw new RuntimeException(e);
        }

    }

    private void afficherColis(List<Colis> colisList) {
        ListViewColis.getItems().clear();
        ListViewColis.setCellFactory(new Callback<ListView<Colis>, ListCell<Colis>>() {
            @Override
            public ListCell<Colis> call(ListView<Colis> param) {
                return new ListCell<Colis>() {
                    @Override
                    protected void updateItem(Colis colis, boolean empty) {
                        super.updateItem(colis, empty);
                        if (empty || colis == null) {
                            setText(null);
                        } else {
                            // Si c'est la première ligne, afficher les titres des colonnes
                            if (getIndex() == 0) {
                                setText("Numéro de colis    |    Type    |  CNI Destinataire     |  CNI Expediteur    |  État     |  Agence Destination");
                                setStyle("-fx-font-weight: bold;-fx-font-size: 16px;");
                            } else {
                                // Afficher les informations sur le colis
                                setStyle("-fx-font-size: 16px;");
                                String typeColisLabel = natureServiceImpl.getById(colis.getType()).getType();
                                Agence agenceDest = agenceServiceImpl.getById(colis.getIdAgenceDestination());
                                String agenceDestInfo = "" + agenceDest.getVille() + ", " + agenceDest.getQuartier();
                                setText(colis.getNumeroColis() + "      |    " +
                                        typeColisLabel + "      |     " +
                                        colis.getCNIDestinataire() + "  |    " +
                                        colis.getCNIExpediteur() + "    |    " +
                                        colis.getEtat() + "     |    " +
                                        agenceDestInfo);
                            }
                        }
                    }
                };
            }
        });
        // Ajouter les éléments à partir de l'index 1
        Colis colisNull = new Colis(0, 0, 0, 0, "0", 0);
        ListViewColis.getItems().add(colisNull); // Ajouter une ligne vide après les titres

        for (Colis colis : colisList) {
            ListViewColis.getItems().add(colis);
        }
    }
    @FXML
    private void signalerDepartColis() {
        if (radioButtonEnStock.isSelected()) {
            List<Colis> selectedColis = ListViewColis.getSelectionModel().getSelectedItems();
            if (!selectedColis.isEmpty()) {
                agenceServiceImpl.signalerDepartColis(selectedColis, sessionAgence.getId());
            } else {
                ShowMessage.showMessage("Veuillez sélectionner au moins un colis.");
            }
        } else {
            ShowMessage.showMessage("Veuillez cocher le bouton 'En Stock' pour signaler le départ des colis.");
        }
    }
    @FXML
    private void signalerReceptionColis() {
        if (radioButtonAttendus.isSelected()) {
            List<Colis> selectedColis = ListViewColis.getSelectionModel().getSelectedItems();
            if (!selectedColis.isEmpty()) {
                agenceServiceImpl.signalerReceptionColis(selectedColis, sessionAgence.getId());
            } else {
                ShowMessage.showMessage("Veuillez sélectionner au moins un colis.");
            }
        } else {
            ShowMessage.showMessage("Veuillez cocher le bouton 'Attendus' pour signaler la réception des colis.");
        }
    }

    public void onBtnAnnuler2Clicked(ActionEvent actionEvent) {
        ittinerairePane.setVisible(false);
        ListView.getItems().clear();
    }

    private Utilisateur showUtilisateurDialog(String textCNI, String role) {
        // Créer une nouvelle fenêtre de dialogue
        Dialog<Utilisateur> dialog = new Dialog<>();
        dialog.setTitle("Iformations personnelles");
        dialog.setHeaderText(""+ role);

        // Créer les champs de saisie pour les informations de l'utilisateur
        TextField nomField = new TextField();
        nomField.setPromptText("Nom");
        TextField telField = new TextField();
        telField.setPromptText("Téléphone");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        TextField adresseField = new TextField();
        adresseField.setPromptText("Adresse");

        // Créer le contenu de la fenêtre de dialogue
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Nom:"), 0, 0);
        grid.add(nomField, 1, 0);
        grid.add(new Label("Téléphone:"), 0, 1);
        grid.add(telField, 1, 1);
        grid.add(new Label("Email:"), 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(new Label("Adresse:"), 0, 3);
        grid.add(adresseField, 1, 3);

        // Ajouter les boutons de confirmation et d'annulation à la fenêtre de dialogue
        dialog.getDialogPane().setContent(grid);
        ButtonType confirmerButtonType = new ButtonType("Confirmer", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmerButtonType, ButtonType.CANCEL);

        // Convertir les résultats de la fenêtre de dialogue en objet Utilisateur lorsque le bouton Confirmer est cliqué
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirmerButtonType) {
                return new Utilisateur(
                        Integer.parseInt(textCNI),
                        nomField.getText(),
                        Integer.parseInt(telField.getText()),
                        emailField.getText(),
                        adresseField.getText(),
                        textCNI,
                        "000"
                );
            }
            return null;
        });

        // Afficher la fenêtre de dialogue et attendre que l'utilisateur entre les informations
        Optional<Utilisateur> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private Utilisateur verifierOuCreerUtilisateur(String textCNI, String role) {
        Utilisateur utilisateur = null;
        try {
            int CNI = Integer.parseInt(textCNI);
            utilisateur = utilisateurServiceImpl.getByCNI(CNI);
            if (utilisateur == null) {
                // Afficher une fenêtre de dialogue pour enregistrer les informations de l'utilisateur
                utilisateur = showUtilisateurDialog(textCNI, role);
                if (utilisateur != null) {
                    // Enregistrer le nouvel utilisateur dans la base de données
                    utilisateurServiceImpl.create(utilisateur);
                } else {
                    // erreur
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }
}
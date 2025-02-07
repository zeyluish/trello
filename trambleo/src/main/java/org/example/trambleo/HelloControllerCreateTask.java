package org.example.trambleo;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class HelloControllerCreateTask {

    ArrayList<Employe> employeChoisi =  new ArrayList<>();

    @FXML
    TextField nomTache;

    @FXML
    TextField descTache;

    @FXML
    DatePicker dateFin;

    @FXML
    ComboBox<Employe> comboEmploye;

    @FXML
    Label messageConfirmation;

    @FXML
    ToggleGroup groupPriorite;

    @FXML
    Button addTacheAFaire = new Button();

    Projet projetSelected;
    String etat;

    public void setEtat(String etat) {
        this.etat = etat;
    }

    private ControllerViewInpProject controllerViewInpProject;

    public void setControllerViewInpProject(ControllerViewInpProject controllerViewInpProject) {
        this.controllerViewInpProject = controllerViewInpProject;
    }


    @FXML
    public void initialize() {
        Employe.importEmploye();
        comboEmploye.getItems().addAll(Employe.listeEmploye);
    }

    @FXML
    public void onAttribuerEmployeBouton() throws IOException {
        Employe employeSelectionne = comboEmploye.getSelectionModel().getSelectedItem();
        if (employeSelectionne == null) {
            messageConfirmation.setText("Veuillez sélectionner un employé.");
            return;
        }
        if (employeChoisi.contains(employeSelectionne)) {
            messageConfirmation.setText("L'employé " + employeSelectionne.prenom + " " + employeSelectionne.nom + " est déjà dans la liste.");
        } else {
            employeChoisi.add(employeSelectionne);
            messageConfirmation.setText("L'employé " + employeSelectionne.prenom + " " + employeSelectionne.nom + " a bien été ajouté.");
        }
    }

    String prioriteTache;

    public String getSelectedValue() throws IOException {
        RadioButton selectedRadioButton = (RadioButton) groupPriorite.getSelectedToggle();
        prioriteTache = selectedRadioButton.getText();
        System.out.println(prioriteTache);
        return prioriteTache;
    }

    @FXML
    public void onCreerTacheBouton() throws IOException {
        String nomTacheText = nomTache.getText();
        String descriptionTacheText = descTache.getText();
        String prioriteTacheText = getSelectedValue();
        LocalDate dateFinTacheDate = dateFin.getValue();
        Projet projetSelect = Projet.projetSelected;
        Tache nouvelleTache = ChefProjet.creerTache(projetSelect, nomTacheText,descriptionTacheText,prioriteTacheText,dateFinTacheDate, etat);
        //projetSelect.listeTache.add(nouvelleTache);
        System.out.println(projetSelect);
        for (Employe employe : employeChoisi) {
            nouvelleTache.listeEmployeTache.add(employe);
        }
        System.out.println(nouvelleTache);
        messageConfirmation.setText("La tâche a bien été créée");
        controllerViewInpProject.initialize();
        Stage stage = (Stage) nomTache.getScene().getWindow();
        stage.close(); //Fermer la pop up
    }

    public void onAnnulerTacheBouton(){ //Fermer la pop up
        Stage stage = (Stage) dateFin.getScene().getWindow();
        stage.close();
    }
}

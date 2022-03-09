/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.achat;
import entities.livraison;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServiceAchat;
import services.ServiceLivraison;

/**
 * FXML Controller class
 *
 * @author khalil kouki
 */
public class AjouterLivraisonController implements Initializable {

    @FXML
    private TextField tf_ref_com;
    @FXML
    private TextField tf_donnee;
    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_frais;
    @FXML
    private TextField tf_localisation;
    @FXML
    private Button btn_ajout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
                              new animatefx.animation.LightSpeedIn(btn_ajout).setCycleCount(10000).play();
    }

    @FXML
    private void ajouter(ActionEvent event) {
        String errors = "";
        if (tf_ref_com.getText().trim().isEmpty()) {

            errors += "Saisire reference commande\n";
        }
        if (tf_frais.getText().trim().isEmpty()) {
            errors += "Saisire frais livraison\n";
        }
        if (tf_id.getText().trim().isEmpty()) {
            errors += "Saisire votre ID\n";
        }
        if (tf_donnee.getText().trim().isEmpty()) {
            errors += "Saisire votre donnees\n";
        }
        if (tf_localisation.getText().trim().isEmpty()) {
            errors += "Saisire votre localisation\n";
        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText(errors);
            alert.showAndWait();
        } else {

            ServiceLivraison sa = new ServiceLivraison();
            livraison l = new livraison();
            l.setRef_commande(Integer.parseInt(tf_ref_com.getText()));
            l.setFrais_livraison((Float) Float.parseFloat(tf_frais.getText()));
            l.setId_user(Integer.parseInt(tf_id.getText()));
            l.setDonnees_user(tf_donnee.getText());
            l.setLocalisation(tf_localisation.getText());
            sa.ajouter(l);
        }
    }

}

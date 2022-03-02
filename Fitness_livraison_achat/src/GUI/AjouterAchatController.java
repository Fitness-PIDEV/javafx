/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.achat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.ServiceAchat;

/**
 * FXML Controller class
 *
 * @author khalil kouki
 */
public class AjouterAchatController implements Initializable {

    @FXML
    private TextField tf_ref_prod;
    @FXML
    private TextField tf_prix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Ajout_Achat(ActionEvent event) {
        String errors = "";
        if (tf_ref_prod.getText().trim().isEmpty()) {

            errors += "Saisire reference produit\n";
        }
        if (tf_prix.getText().trim().isEmpty()) {
            errors += "Saisire prix \n";
        }

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText(errors);
            alert.showAndWait();
        } else {
            ServiceAchat sa = new ServiceAchat();
            achat a = new achat();
            a.setRef_produit(Integer.parseInt(tf_ref_prod.getText()));
            a.setPrix((Float) Float.parseFloat(tf_prix.getText()));
            sa.ajouter(a);

        }
    }

}

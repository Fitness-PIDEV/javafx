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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import services.ServiceAchat;
import services.ServiceLivraison;

/**
 * FXML Controller class
 *
 * @author khalil kouki
 */
public class AfficherLivraisonController implements Initializable {

    @FXML
    private ListView<livraison> listviewlivraison;
    @FXML
    private TextField tf_recherche;
    @FXML
    private TextField tf_ref_com;
    @FXML
    private TextField tf_frais;
    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_donnee;
    @FXML
    private TextField tf_localisation;
     ObservableList<achat> livraison = FXCollections.observableArrayList();
    ServiceLivraison sl = new ServiceLivraison();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
     public void refreshlist() {
        livraison = FXCollections.observableArrayList(sl.afficher());
        listviewlivraison.setItems(livraison);
    }


    @FXML
    private void recherche(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
          sl.supprimer(listviewlivraison.getSelectionModel().getSelectedItem().getId_livraison());
        refreshlist();
    }

    @FXML
    private void modifier(ActionEvent event) {
    }
    
}

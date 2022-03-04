/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Alert.AlertDialog;
import Service.cours_service;
import entites.Cours;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AffichagecoursController implements Initializable {

    @FXML
    private Button GestProduit5;
    @FXML
    private Button Gestcategorie45;
    @FXML
    private Button Gestcours5;
    @FXML
    private ListView<Cours> list;
    @FXML
    private TextField nomcours;
    @FXML
    private TextField etatcours;
    @FXML
    private TextField nomcoach;
    @FXML
    private TextField sallecours;
    @FXML
    private TextField durecours;
    @FXML
    private Button ajoutc;
    @FXML
    private Button supprimer1;
    @FXML
    private Button mod1;
    @FXML
    private TextField searchc;

    /**
     * Initializes the controller class.
     */
    ObservableList<Cours> cours=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(AffichagecoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
 public void afficher() throws SQLException{
        cours_service cr = new cours_service();
        cours=FXCollections.observableArrayList(cr.Affichertout());
        list.setItems(cours);
    }
   @FXML
    private void Fill(MouseEvent event) {
            Cours c= list.getSelectionModel().getSelectedItem();
        nomcours.setText(c.getNom_cours());
        etatcours.setText(String.valueOf(c.getEtat()));
        durecours.setText(c.getDuree_cours());
        nomcoach.setText(c.getNom_coach());
        sallecours.setText(c.getSalle());            
    }
    @FXML
    private void GestProduit5(ActionEvent event) {
         try {

                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("affichageproduit.fxml"));
            Stage stage =new Stage();
            
                Scene scene = new Scene(root);
            
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Gestcategorie5(ActionEvent event) {
         try {

                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("affichercategorie.fxml"));
            Stage stage =new Stage();
            
                Scene scene = new Scene(root);
            
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Gestcours5(ActionEvent event) {
        try {

                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("affichagecours.fxml"));
            Stage stage =new Stage();
            
                Scene scene = new Scene(root);
            
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    @FXML
    private void ajoutc(ActionEvent event) {
          try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("ajoutercours.fxml"));
            Stage stage =new Stage();
            
                Scene scene = new Scene(root);
            
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimer1(ActionEvent event) throws SQLException {
          cours_service cr = new cours_service();
        Cours c = new Cours();
//        p.setID(idsupp.getSelectionModel().getSelectedItem());
//        sr.Supprimer(p,idsupp);
//        JOptionPane.showMessageDialog(null, "Produit Supprimée !");
//        afficher();
        //afficherID();
        cr.Supprimer(c,list.getSelectionModel().getSelectedItem().getID());
        AlertDialog.showNotification("supprimer","avec succee", AlertDialog.image_checked);
        afficher();

    }

    @FXML
    private void modifier1(ActionEvent event) throws SQLException {
            Cours c = new Cours();
         cours_service cr = new cours_service();
        c.setNom_cours(nomcours.getText());
                c.setEtat(Integer.parseInt(etatcours.getText()));
                c.setDuree_cours(durecours.getText());
                c.setSalle(sallecours.getText());
                c.setNom_coach(nomcoach.getText());
                cr.Modifier(c, list.getSelectionModel().getSelectedItem().getID());
                AlertDialog.showNotification("modifier","avec succee", AlertDialog.image_checked);
                afficher();
    }

    @FXML
    private void searchc(ActionEvent event) throws SQLException {
         cours_service cr = new cours_service();
                 List<Cours> c=cr.Recherche(searchc.getText());
                 list.getItems().clear();
                 list.getItems().removeAll(cours);
                 list.getItems().addAll(c);
    }
    
}

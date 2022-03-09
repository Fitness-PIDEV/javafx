/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Alert.AlertDialog;
import services.cours_service;
import entities.Cours;
import fitness_user.FXMain;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AffichagecoursController implements Initializable {

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
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
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
        
        //AlertDialog.showNotification("supprimer","avec succee", AlertDialog.image_checked);
        
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous vraiment supprimer ce cours?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            cr.Supprimer(c,list.getSelectionModel().getSelectedItem().getID());
            afficher();
            /*TrayNotification tray=new TrayNotification();
            tray.setAnimationType(AnimationType.POPUP);
            tray.setTitle("suppression avec succes");
            tray.setMessage("Reclmation a ete supprime");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2000));*/
            AlertDialog.showNotification("supprimer","avec succee", AlertDialog.image_checked);
        nomcours.setText("");
        etatcours.setText("");
        durecours.setText("");
        nomcoach.setText("");
        sallecours.setText("");
        } else {
            alert2.close();
        }

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
                cr.Modifier(c,list.getSelectionModel().getSelectedItem().getID());
                AlertDialog.showNotification("modifier","avec succee", AlertDialog.image_checked);
                afficher();
        nomcours.setText("");
        etatcours.setText("");
        durecours.setText("");
        nomcoach.setText("");
        sallecours.setText("");
    }

    @FXML
    private void searchc(ActionEvent event) throws SQLException {
         cours_service cr = new cours_service();
                 List<Cours> c=cr.Recherche(searchc.getText());
                 list.getItems().clear();
                 list.getItems().removeAll(cours);
                 list.getItems().addAll(c);
    }

    @FXML
    private void gotouser(ActionEvent event) {
     try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();

                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLGSTUser.fxml"));
                Stage stage =new Stage();

                Scene scene = new Scene(root);

                stage.setTitle("Gestion Utilisateur");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void gotoRec(ActionEvent event) {
         try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();

                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLAfficherRec.fxml"));
                Stage stage =new Stage();

                Scene scene = new Scene(root);

                stage.setTitle("Reclamations");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void goHome(ActionEvent event) {
         try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();

                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/GUI/Admin_stat.fxml"));
                Stage stage =new Stage();

                Scene scene = new Scene(root);

                stage.setTitle("Home");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

    @FXML
    private void goplanning(ActionEvent event) {
        try {

                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("afficherplanning.fxml"));
            Stage stage =new Stage();
            
                Scene scene = new Scene(root);
            
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goreservation(ActionEvent event) {
        try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("AfficherReservation.fxml"));
            Stage stage =new Stage();
            
                Scene scene = new Scene(root);
            
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

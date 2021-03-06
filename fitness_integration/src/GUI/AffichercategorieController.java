/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Alert.AlertDialog;
import services.categorie_service;
import services.produit_service;
import entities.Categorie;
import entities.Produit;
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

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AffichercategorieController implements Initializable {

    @FXML
    private ListView<Categorie> listeid;
    @FXML
    private TextField nomcategorie;
    @FXML
    private TextField etatca;
    @FXML
    private Button idmod;
    @FXML
    private Button idsupp;
    @FXML
    private Button idajoutc;

    /**
     * Initializes the controller class.
     */
        ObservableList<Categorie> categorie=FXCollections.observableArrayList();
    @FXML
    private TextField cherchercat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficher();
            //afficherID();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AffichercategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }  
     public void afficher() throws SQLException{
         categorie_service sr = new categorie_service();
        categorie =FXCollections.observableArrayList(sr.Affichertout());
        listeid.setItems(categorie);
    }
     

    
    
    
    
    
    @FXML
    private void listinsert(MouseEvent event) {
        
          Categorie c= listeid.getSelectionModel().getSelectedItem();
        nomcategorie.setText(c.getNom_categorie());
        etatca.setText(String.valueOf(c.getEtat()));
        
    }

    @FXML
    private void modification(ActionEvent event) throws SQLException {
        
          Categorie c = new Categorie();
         categorie_service sr = new categorie_service();
        c.setNom_categorie(nomcategorie.getText());
                c.setEtat(Integer.parseInt(etatca.getText()));

                sr.Modifier(c,listeid.getSelectionModel().getSelectedItem().getID());
                AlertDialog.showNotification("modifier","avec succee", AlertDialog.image_checked);
                afficher();
    }

    @FXML
    private void suppression(ActionEvent event) throws SQLException {
         categorie_service sr = new categorie_service();
        Categorie c = new Categorie();
//        p.setID(idsupp.getSelectionModel().getSelectedItem());
//        sr.Supprimer(p,idsupp);
//        JOptionPane.showMessageDialog(null, "Produit Supprim??e !");
//        afficher();
        //afficherID();
        
        
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous vraiment supprimer ce categorie?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            sr.Supprimer(c,listeid.getSelectionModel().getSelectedItem().getID());
            afficher();
            /*TrayNotification tray=new TrayNotification();
            tray.setAnimationType(AnimationType.POPUP);
            tray.setTitle("suppression avec succes");
            tray.setMessage("Reclmation a ete supprime");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2000));*/
            AlertDialog.showNotification("supprimer","avec succee", AlertDialog.image_checked);
    }
    }

    @FXML
    private void ajoutb(ActionEvent event) {
         try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("categorie.fxml"));
            Stage stage =new Stage();
            
                Scene scene = new Scene(root);
            
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cherchercat(ActionEvent event) throws SQLException {
           categorie_service sr = new categorie_service();
                 List<Categorie> c=sr.Recherche(cherchercat.getText());
                 listeid.getItems().clear();
                 listeid.getItems().removeAll(categorie);
                 listeid.getItems().addAll(c);
              
    }

    @FXML
    private void GestProduit4(ActionEvent event) {
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
    private void Gestcategorie4(ActionEvent event) {
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
    private void Gestcours4(ActionEvent event) {
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

                stage.setTitle("Gestion Reclamation");
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

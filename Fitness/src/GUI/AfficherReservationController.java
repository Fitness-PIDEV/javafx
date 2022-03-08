/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Alert.AlertDialog;
import services.reservation_service;
import utils.Mailapi;
import entities.Reservation;
import fitness_user.FXMain;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AfficherReservationController implements Initializable {

    @FXML
    private Button ajout;
    @FXML
    private Button supp;
    @FXML
    private Button modif;
    @FXML
    private TextField rech;
    @FXML
    private ListView<Reservation> idlist;
    @FXML
    private TextField nom;
    @FXML
    private DatePicker date;

    /**
     * Initializes the controller class.
     */
    ObservableList<Reservation> Reservation=FXCollections.observableArrayList();
    @FXML
    private TextField nb;
    @FXML
    private TextField dispo;
    @FXML
    private Button ref;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void afficher() throws SQLException{
        reservation_service sr = new reservation_service();
        Reservation=FXCollections.observableArrayList(sr.Affichertout());
        idlist.setItems(Reservation);
    }
    @FXML
    private void ajouter(ActionEvent event) {
     try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("AjouterReservation.fxml"));
            Stage stage =new Stage();
            
                Scene scene = new Scene(root);
            
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void suppression(ActionEvent event) throws SQLException {
    reservation_service sr = new reservation_service();
        Reservation p = new Reservation();
        
                
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous vraiment supprimer la reservation ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
        sr.Supprimer(p,idlist.getSelectionModel().getSelectedItem().getId());
        Mailapi.send("fitnessesprit8@gmail.com", "mailapi123", "ahmed.tlili@esprit.tn" , "Reservation", "Vous avez supprimez votre reservation");
        afficher();
                nom.setText("");
                nb.setText("");
                dispo.setText("");
            /*TrayNotification tray=new TrayNotification();
            tray.setAnimationType(AnimationType.POPUP);
            tray.setTitle("suppression avec succes");
            tray.setMessage("Reclmation a ete supprime");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2000));*/
                AlertDialog.showNotification("supprimer","avec succee", AlertDialog.image_checked);
                
        } else {
            alert2.close();
        }
    }

    @FXML
    private void modification(ActionEvent event) throws SQLException {
     Reservation p = new Reservation();
         reservation_service sr = new reservation_service();
                p.setNom_cours(nom.getText());
                p.setNbr_participant(Integer.parseInt(nb.getText()));
                p.setDispo(Integer.parseInt(dispo.getText()));
                LocalDate Fdate = date.getValue();
                String Sdate = String.valueOf(Fdate);
                p.setDate(Date.valueOf(Sdate));
                sr.Modifier(p, idlist.getSelectionModel().getSelectedItem().getId());
                afficher();
                nom.setText("");
                nb.setText("");
                dispo.setText("");
                
    }

    @FXML
    private void recherche(ActionEvent event) {
    reservation_service ps = new reservation_service();
    List<Reservation> Reservation = ps.Recherche (rech.getText());
    idlist.getItems().clear();
    idlist.getItems().removeAll(Reservation);
    idlist.getItems().addAll(Reservation);
    }

    @FXML
    private void list(MouseEvent event) {
    Reservation p= idlist.getSelectionModel().getSelectedItem();
        
        nom.setText(p.getNom_cours());
        date.setValue(LocalDate.now());
        dispo.setText(String.valueOf(p.getDispo()));
        nb.setText(String.valueOf(p.getNbr_participant()));
    }

    
    @FXML
    private void refresh(ActionEvent event) {
    try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void versp(ActionEvent event) {
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
    
}

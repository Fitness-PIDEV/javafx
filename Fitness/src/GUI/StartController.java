/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Alert.AlertDialog;
import services.planning_service;
import entities.planning;
import fitness_user.FXMain;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Mailapi;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class StartController implements Initializable {

    @FXML
    private Button ajout;
  
    @FXML
    private Button supp;
    @FXML
    private Button modif;
    @FXML
    private TextField rech;
    @FXML
    private ListView<planning> idlist;
    @FXML
    private DatePicker dateid;
    @FXML
    private TextField salleid;
    @FXML
    private TextField nomcoach;
    @FXML
    private TextField noomc;
    @FXML
    private TextField dureec;

    /**
     * Initializes the controller class.
     */
     ObservableList<planning> planning=FXCollections.observableArrayList();
    @FXML
    private TextField idc;
    @FXML
    private Button ref;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(StartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
 public void afficher() throws SQLException{
        planning_service sr = new planning_service();
        planning=FXCollections.observableArrayList(sr.Affichertout());
        idlist.setItems(planning);
    }
    @FXML
    private void ajouter(ActionEvent event) {
        
        try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("ajouterPlanning.fxml"));
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
        planning_service sr = new planning_service();
        planning p = new planning();
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous vraiment supprimer ce palnning?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
        sr.Supprimer(p,idlist.getSelectionModel().getSelectedItem().getId());
        afficher();
                salleid.setText("");
                dureec.setText("");
                idc.setText("");
                nomcoach.setText("");
                noomc.setText("");
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
    private void modification(ActionEvent event) throws SQLException{
        

            
            planning p = new planning();
         planning_service sr = new planning_service();
                p.setSalle(salleid.getText());
                p.setDuree(Integer.parseInt(dureec.getText()));
                p.setId_cours(Integer.parseInt(idc.getText()));
                LocalDate Fdate = dateid.getValue();
                String Sdate = String.valueOf(Fdate);
                p.setDate(Date.valueOf(Sdate));
                p.setNom_coach(nomcoach.getText());
                p.setNom_cours(noomc.getText());
                sr.Modifier(p, idlist.getSelectionModel().getSelectedItem().getId());
                afficher();
                salleid.setText("");
                dureec.setText("");
                idc.setText("");
                nomcoach.setText("");
                noomc.setText("");
    }
    @FXML
    private void recherche(ActionEvent event) throws SQLException {
    planning_service ps = new planning_service();
    List<planning> planning = ps.RechercheNomCours (rech.getText());
    idlist.getItems().clear();
    idlist.getItems().removeAll(planning);
    idlist.getItems().addAll(planning);
    
    }

    @FXML
    private void list(MouseEvent event) {
//         int y = ( dateid.getValue().getYear() % 100 )+ 100 ;
//            int m = dateid.getValue().getMonthValue()-1;
//            int day= dateid.getValue().getDayOfMonth();
//            java.util.Date d = new java.util.Date(y,m,day);
        planning p= idlist.getSelectionModel().getSelectedItem();
        idc.setText(String.valueOf(p.getId_cours()));
        salleid.setText(p.getSalle());
        dateid.setValue(LocalDate.now());
        nomcoach.setText(p.getNom_coach());
        noomc.setText(p.getNom_cours());
        dureec.setText(String.valueOf(p.getDuree()));
        
    }

    @FXML
    private void refrech(ActionEvent event) {
    try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(FRONTPController.class.getName()).log(Level.SEVERE, null, ex);
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
    

    @FXML
    private void gohome(ActionEvent event) {
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
    
}

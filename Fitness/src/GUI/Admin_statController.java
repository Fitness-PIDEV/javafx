/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import fitness_user.FXMain;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.ServiceReclamation;
import services.ServiceUsers;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class Admin_statController implements Initializable {

    @FXML
    private Group groupRegistered;
    @FXML
    private Label nb_reclamation;
    @FXML
    private Label nb_cours;
    @FXML
    private Label nb_produit;
    @FXML
    private Label nb_reservation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nb_reclamation.setText(String.valueOf(get_Number_Reclamation()));
        nb_cours.setText(String.valueOf(get_Number_cours()));
        nb_produit.setText(String.valueOf(get_Number_Produit()));
        nb_reservation.setText(String.valueOf(get_Number_reservation()));
    }    
    public int get_Number_Reclamation() {
        ResultSet rs = null;
        int Message_Number = 989898;
        try {
            String req = "SELECT COUNT(*) FROM `reclamation` ";
            PreparedStatement preparedStatement = MyDB.getInstance().getCnx().prepareStatement(req);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message_Number = rs.getInt(1);
            }
            return Message_Number;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Message_Number;
    }
     public int get_Number_Produit() {
        ResultSet rs = null;
        int Message_Number = 989898;
        try {
            String req = "SELECT COUNT(*) FROM `produit` where etat=1 ";
            PreparedStatement preparedStatement = MyDB.getInstance().getCnx().prepareStatement(req);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message_Number = rs.getInt(1);
            }
            return Message_Number;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Message_Number;
    }
     public int get_Number_cours() {
        ResultSet rs = null;
        int Message_Number = 989898;
        try {
            String req = "SELECT COUNT(*) FROM `cours` where etat=1 ";
            PreparedStatement preparedStatement = MyDB.getInstance().getCnx().prepareStatement(req);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message_Number = rs.getInt(1);
            }
            return Message_Number;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Message_Number;
    }
     public int get_Number_reservation() {
        ResultSet rs = null;
        int Message_Number = 989898;
        try {
            String req = "SELECT COUNT(*) FROM `reservation` where state=1 ";
            PreparedStatement preparedStatement = MyDB.getInstance().getCnx().prepareStatement(req);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message_Number = rs.getInt(1);
            }
            return Message_Number;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Message_Number;
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

                stage.setTitle("Gestion Utilisateur");
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
    
}

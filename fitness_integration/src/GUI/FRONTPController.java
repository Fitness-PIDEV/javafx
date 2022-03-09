/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import services.planning_service;
import utils.MyDB;
import entities.planning;
import fitness_user.FXMain;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FRONTPController implements Initializable {
private Connection c = MyDB.getInstance().getCnx();
    @FXML
    private ListView<planning> idlist;
    @FXML
    private Button res;
    @FXML
    private ComboBox<String> coachs;
    @FXML
    private ComboBox<String> cours;
    @FXML
    private ComboBox<String> salles;
    ObservableList<planning> planning=FXCollections.observableArrayList();
    @FXML
    private Button ref;
    @FXML
    private Label sedeconnecter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        coachs.setItems(FXCollections.observableArrayList(LoadCoach()));
        cours.setItems(FXCollections.observableArrayList(LoadCours()));
        salles.setItems(FXCollections.observableArrayList(LoadSalles()));

    } catch (SQLException ex) {
        Logger.getLogger(FRONTPController.class.getName()).log(Level.SEVERE, null, ex);
    }
        try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(FRONTPController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
 public void afficher() throws SQLException{
        planning_service sr = new planning_service();
        planning=FXCollections.observableArrayList(sr.Affichertout());
        idlist.setItems(planning);
    }

public List<String> LoadCoach() throws SQLException {
      List<String> options = new ArrayList<>();
      
      String query ="SELECT nom_coach FROM planning WHERE state = 1";
                  PreparedStatement statement = c.prepareStatement(query);

            ResultSet set = statement.executeQuery();
      while(set.next()){
      options.add(set.getString("nom_coach"));
      }
statement.close();
set.close();
return options;
}
public List<String> LoadCours() throws SQLException {
      List<String> options1 = new ArrayList<>();
      
      String query ="SELECT nom_cours FROM planning WHERE state = 1";
                  PreparedStatement statement = c.prepareStatement(query);

            ResultSet set = statement.executeQuery();
      while(set.next()){
      options1.add(set.getString("nom_cours"));
      }
statement.close();
set.close();
return options1;
}
public List<String> LoadSalles() throws SQLException {
      List<String> options2 = new ArrayList<>();
      
      String query ="SELECT salle FROM planning WHERE state = 1";
                  PreparedStatement statement = c.prepareStatement(query);

            ResultSet set = statement.executeQuery();
      while(set.next()){
      options2.add(set.getString("salle"));
      }
statement.close();
set.close();
return options2;
}
    @FXML
    private void list(MouseEvent event) {
    }

    @FXML
    private void versr(ActionEvent event) {
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
    private void FiltreCoach(ActionEvent event) throws SQLException {
    planning_service ps = new planning_service();
    planning p = new planning();
    List <planning> y = ps.RechercheNomCoachs(coachs.getSelectionModel().getSelectedItem());
    idlist.getItems().clear();
    idlist.getItems().removeAll(y);
    idlist.getItems().addAll(y);
    
    }
    @FXML
    private void FiltreSalles(ActionEvent event) throws SQLException {
    planning_service ps = new planning_service();
    planning p = new planning();
    List <planning> lp = ps.RechercheSalles(salles.getSelectionModel().getSelectedItem());
    idlist.getItems().clear();
    idlist.getItems().removeAll(lp);
    idlist.getItems().addAll(lp);
    }
@FXML
    private void FiltreCours(ActionEvent event) throws SQLException {
    planning_service ps = new planning_service();
    planning p = new planning();
    List <planning> x = ps.RechercheNomCours(cours.getSelectionModel().getSelectedItem());
    idlist.getItems().clear();
    idlist.getItems().removeAll(x);
    idlist.getItems().addAll(x);
    
    }

    @FXML
    private void refresh(ActionEvent event) {
     try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(FRONTPController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void sedeconnecter(MouseEvent event) {
        try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void moncompte(MouseEvent event) {
        try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("ProfileUser.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void afficherrec(MouseEvent event) {
        try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAjoutRec.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void magasin(MouseEvent event) {
    }

    @FXML
    private void planning(MouseEvent event) {
    }

    @FXML
    private void workout(MouseEvent event) {
    }
}


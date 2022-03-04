/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Alert.AlertDialog;
import Service.produit_service;
import entites.Produit;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class StartController implements Initializable {
    
    
    @FXML
    private Button ajout;
    @FXML
    private Button supprimer;
    @FXML
    private Button mod;
    @FXML
    private ListView<Produit> idlist;
    @FXML
    private TextField Fnom;
    @FXML
    private TextField Fetat;
    @FXML
    private TextField Fcategorie;
    @FXML
    private TextField Fquantite;
    @FXML
    private TextField Fimage;
    @FXML
    private TextField Fprix;

    /**
     * Initializes the controller class.
     */
    ObservableList<Produit> produit=FXCollections.observableArrayList();
    @FXML
    private TextField search;
    @FXML
    private Button GestProduit2;
    @FXML
    private Button Gestcategorie2;
    @FXML
    private Button Gestcours2;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficher();
            //afficherID();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(StartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
//    public void afficherID(){
//    produit_service sr = new produit_service();
//
//        ArrayList lv;
//
//        lv = (ArrayList) sr.afficherid();
//        ObservableList data = FXCollections.observableArrayList(lv);
//        idsupp.setItems(data);    
//    }
    public void afficher() throws SQLException{
        produit_service sr = new produit_service();
        produit=FXCollections.observableArrayList(sr.Affichertout());
        idlist.setItems(produit);
    }

    
    @FXML
    private void ajout(ActionEvent event) {
        try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("AjouterProduit.fxml"));
            Stage stage =new Stage();
            
                Scene scene = new Scene(root);
            
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        produit_service sr = new produit_service();
        Produit p = new Produit();
//        p.setID(idsupp.getSelectionModel().getSelectedItem());
//        sr.Supprimer(p,idsupp);
//        JOptionPane.showMessageDialog(null, "Produit Supprimée !");
//        afficher();
        //afficherID();
        sr.Supprimer(p,idlist.getSelectionModel().getSelectedItem().getID());
        AlertDialog.showNotification("supprimer","avec succee", AlertDialog.image_checked);
        afficher();

    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
         Produit p = new Produit();
         produit_service sr = new produit_service();
        p.setNom_produit(Fnom.getText());
                p.setPrix_produit(Integer.parseInt(Fprix.getText()));
                p.setEtat(Integer.parseInt(Fetat.getText()));
                p.setQuantite_produit(Integer.parseInt(Fquantite.getText()));
                p.setID_categorie(Integer.parseInt(Fcategorie.getText()));
                p.setImage_produit(Fimage.getText());
                sr.Modifier(p, idlist.getSelectionModel().getSelectedItem().getID());
                AlertDialog.showNotification("modifier","avec succee", AlertDialog.image_checked);
                afficher();
    }

    @FXML
    private void fill(MouseEvent event) {
        Produit p= idlist.getSelectionModel().getSelectedItem();
        Fnom.setText(p.getNom_produit());
        Fprix.setText(String.valueOf(p.getPrix_produit()));
        Fetat.setText(String.valueOf(p.getEtat()));
        Fquantite.setText(String.valueOf(p.getQuantite_produit()));
        Fcategorie.setText(String.valueOf(p.getID_categorie()));
        Fimage.setText(p.getImage_produit());
        
        
        
    }

    @FXML
    private void search(ActionEvent event) throws SQLException {
                 produit_service sr = new produit_service();
                 List<Produit> p=sr.Recherche(search.getText());
                 idlist.getItems().clear();
                 idlist.getItems().removeAll(produit);
                 idlist.getItems().addAll(p);
              

        
    }

    @FXML
    private void GestProduit2(ActionEvent event) {
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
    private void Gestcategorie2(ActionEvent event) {
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
    private void Gestcours2(ActionEvent event) {
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
    
}

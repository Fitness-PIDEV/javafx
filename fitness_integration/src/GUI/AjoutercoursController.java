/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import services.cours_service;
import services.produit_service;
import entities.Cours;
import entities.Produit;
import fitness_user.FXMain;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjoutercoursController implements Initializable {

    @FXML
    private TextField cnom;
    @FXML
    private TextField csalle;
    @FXML
    private TextField cetat;
    @FXML
    private TextField cduree;
    @FXML
    private TextField cnomcoach;
    @FXML
    private Button Cinsert;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void insertionc(ActionEvent event) throws SQLException {
     if(verifUserChampsajouter()){
         cours_service sp=new cours_service(); 
        Cours u = new Cours();
        u.setNom_cours(cnom.getText());
        u.setEtat(Integer.parseInt(cetat.getText()));
        u.setNom_coach(cnomcoach.getText());
        u.setDuree_cours(cduree.getText());
        u.setSalle(csalle.getText());
      
            sp.Ajouter(u);
//            System.out.println("GUI.AjouterProduitController.insertion()");
           

          

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
    }
    public boolean verifUserChampsajouter() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

        cnom.setStyle(styledefault);
        cnomcoach.setStyle(styledefault);
        cduree.setStyle(styledefault);
        csalle.setStyle(styledefault);
        cetat.setStyle(styledefault);

        if (cnom.getText().equals("")) {
            cnom.setStyle(style);
            verif = 1;
        }
//        if (dateC.getValue().equals("")) {
//            dateC.setStyle(style);
//            verif = 1;
//        }
        if (cnomcoach.getText().equals("")) {
            cnomcoach.setStyle(style);
            verif = 1;
        }
        if (cduree.getText().equals("")) {
            cduree.setStyle(style);
            verif = 1;
        }
         if (csalle.getText().equals("")) {
            csalle.setStyle(style);
            verif = 1;
        }
          if (cetat.getText().equals("")) {
            cetat.setStyle(style);
            verif = 1;
        }
           
        if (verif == 0) {
            return true;
        }
        
        JOptionPane.showMessageDialog(null, "Remplir tous les champs!");
        return false;
    }

    private void GestProduit6(ActionEvent event) {
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

    private void Gestcategorie6(ActionEvent event) {
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
    private void Gestcours6(ActionEvent event) {
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
    
}

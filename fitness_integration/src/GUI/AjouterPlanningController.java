/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import services.planning_service;
import entities.planning;
import fitness_user.FXMain;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterPlanningController implements Initializable {

    @FXML
    private TextField dure;
    @FXML
    private DatePicker date;
    @FXML
    private TextField salle;
    @FXML
    private TextField nom;
    @FXML
    private TextField nomcoach;
    @FXML
    private Button insert;
    @FXML
    private TextField id_cours;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    /**
     * Initializes the controller class.
     */
  

    @FXML
    private void insertion(ActionEvent event) throws SQLException {
        
        if(verifUserChampsajouter()){
        planning_service sp=new planning_service(); 
        planning u = new planning();
        String d = dure.getText();
        int d0 = Integer.parseInt(d);
        String i = id_cours.getText();
        int i1 = Integer.parseInt(i);
        //u.setState(Integer.parseInt(state.getText()));
        //u.setDuree(Integer.parseInt(dure.getText()));
        //u.setId_cours(Integer.parseInt(id_cours.getText()));
        LocalDate d1= date.getValue();
        Date datex= Date.valueOf(d1);
        String sa = salle.getText();
        String n = nom.getText();
        String nc = nomcoach.getText();
            sp.Ajouter(new planning(i1,d0,n,nc,sa,datex));
            JOptionPane.showMessageDialog(null, "Planning ajouter");
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
    
    public boolean verifUserChampsajouter() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

        nom.setStyle(styledefault);
        nomcoach.setStyle(styledefault);
        salle.setStyle(styledefault);
        date.setStyle(styledefault);
        id_cours.setStyle(styledefault);
        dure.setStyle(styledefault);

        if (nom.getText().equals("")) {
            nom.setStyle(style);
            verif = 1;
        }

        //System.out.println(strDate);
       if (date.toString().equals("")) {
            date.setStyle(style);
            System.out.println("aaa");
            verif = 1;
        }
        if (nomcoach.getText().equals("")) {
            nomcoach.setStyle(style);
            verif = 1;
        }
        if (salle.getText().equals("")) {
            salle.setStyle(style);
            verif = 1;
        }
          if (id_cours.getText().equals("")) {
            id_cours.setStyle(style);
            verif = 1;
        }
           if (dure.getText().equals("")) {
            dure.setStyle(style);
            verif = 1;
        }

        if (verif == 0) {
            return true;
        }
        
        JOptionPane.showMessageDialog(null, "Remplir tous les champs!");
        return false;
    }

    @FXML
    private void afficherplanning(ActionEvent event) {
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
        
        
    
    


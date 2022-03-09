/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.achat;
import fitness_user.FXMain;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceAchat;

/**
 * FXML Controller class
 *
 * @author khalil kouki
 */
public class AjouterAchatController implements Initializable {

    @FXML
    private TextField tf_ref_prod;
    @FXML
    private TextField tf_prix;
    @FXML
    private Button g_livraison;
    @FXML
    private Button btn_ajout;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         new animatefx.animation.LightSpeedIn(g_livraison).setCycleCount(10000).play();
                              new animatefx.animation.LightSpeedIn(btn_ajout).setCycleCount(10000).play();
    }    

    @FXML
    private void Ajout_Achat(ActionEvent event) {
          String errors = "";
        if (tf_ref_prod.getText().trim().isEmpty()) {

            errors += "Saisire reference produit\n";
        }
        if (tf_prix.getText().trim().isEmpty()) {
            errors += "Saisire prix \n";
        }
        
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText(errors);
            alert.showAndWait();
        } else {
        ServiceAchat sa = new ServiceAchat();
        achat a = new achat();
        a.setRef_produit(Integer.parseInt(tf_ref_prod.getText()));
        a.setPrix((Float)Float.parseFloat(tf_prix.getText()));
        sa.ajouter(a);
         try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/GUI/AjouterLivraison.fxml"));
            Stage stage =new Stage();
            
                Scene scene = new Scene(root);
            
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }}
    

    @FXML
    private void gotolivraison(ActionEvent event) {
         try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("AjouterLivraison.fxml"));
            Stage stage =new Stage();
            
                Scene scene = new Scene(root);
            
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

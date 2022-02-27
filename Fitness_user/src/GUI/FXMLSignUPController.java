/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.User;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceUsers;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class FXMLSignUPController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfnumero;
    @FXML
    private PasswordField pfpassword;
    ServiceUsers su=new ServiceUsers();
    
    
    @FXML
    private Label erreur_mail;
    @FXML
    private Label erreur_num;
    @FXML
    private Label erreur_prenom;
    @FXML
    private Label erreur_password;
    @FXML
    private Label erreur_nom;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void gotologin(ActionEvent event) {
        try {
            
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLLogin.fxml"));
            Stage stage =new Stage();
            
            Scene scene = new Scene(root);
            
            stage.setTitle("signup");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void signin(ActionEvent event) {
        String errors="";
        if(tfnom.getText().trim().isEmpty()){
            erreur_nom.setText("veuillez saisir votre nom");
            
        }else erreur_nom.setText("");
        if(tfprenom.getText().trim().isEmpty()){
            erreur_prenom.setText("veuillez saisir votre prenom");
           
        }else  erreur_prenom.setText("");
        if(tfemail.getText().trim().isEmpty()){
            erreur_mail.setText("veuillez saisir votre mail");
          
        }else erreur_mail.setText("");
        if(tfnumero.getText().trim().isEmpty()){
            erreur_num.setText("veuillez saisir votre numero");
           
        }else erreur_num.setText("");
        if(pfpassword.getText().trim().isEmpty()){
            erreur_password.setText("veuillez saisir votre password");
           
        }else erreur_password.setText("");
        if(errors.length()>0){
            //Alert alert =new Alert(Alert.AlertType.ERROR);
            //alert.setTitle("Erreur de saisie");
            //alert.setContentText(errors);
            //alert.showAndWait(); 
        }
        else{
            //erreur_nom.setText("");
            User u=new User();
            u.setNom(tfnom.getText());
            u.setPrenom(tfprenom.getText());
            u.setEmail(tfemail.getText());
            u.setMdp(pfpassword.getText());
            u.setNum(Integer.parseInt(tfnumero.getText()));
            u.setPhoto("ee");
            su.ajouter(u);
            try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();

                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLLogin.fxml"));
                Stage stage =new Stage();

                Scene scene = new Scene(root);

                stage.setTitle("signup");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @FXML
    private void uploadimage(ActionEvent event) {
    }
    
}

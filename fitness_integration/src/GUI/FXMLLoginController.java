/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import fitness_user.FXMain;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.ServiceUsers;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class FXMLLoginController implements Initializable {
    public static int idglobaluser;
    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField pfpassword;
    ServiceUsers su=new ServiceUsers();
    @FXML
    private ImageView imageload;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imageload.setVisible(false);
        
    }    

    @FXML
    private void login(ActionEvent event) {
        if(su.checklogin(tfemail.getText(), pfpassword.getText())){
     
        if(su.findRoleByEmail(tfemail.getText()).equals("ADMIN")){
        imageload.setVisible(true);
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(2));
        pauseTransition.setOnFinished(ev -> {
                try {
                    Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
                    stageclose.close();
                    Parent root=FXMLLoader.load(getClass().getResource("/GUI/Admin_stat.fxml"));
                    Stage stage =new Stage();
                    Scene scene = new Scene(root);
                    stage.setTitle("signup");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
                }});
               pauseTransition.play();
            }
        else if(su.findRoleByEmail(tfemail.getText()).equals("USER")){
        idglobaluser=su.findIdByEmail(tfemail.getText());
        imageload.setVisible(true);
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(2));
        pauseTransition.setOnFinished(ev -> {
                try {
                    Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
                    stageclose.close();
                    Parent root=FXMLLoader.load(getClass().getResource("/GUI/ProfileUser.fxml"));
                    Stage stage =new Stage();
                    Scene scene = new Scene(root);
                    stage.setTitle("Menu");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
                }});
               pauseTransition.play();
            }
            else if(su.findRoleByEmail(tfemail.getText()).equals("COACH")){
               
            }
            else{
               
            }
            TrayNotification tray=new TrayNotification();
            tray.setAnimationType(AnimationType.POPUP);
            tray.setTitle("connexion");
            tray.setMessage("Bienvenue !");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2000));
        }
        else{
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de connexion");
            alert.setContentText("email ou mpd invalide");
            alert.showAndWait();
        }
    }

    @FXML
    private void gotosignup(ActionEvent event) {
        try {
            
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLSignUP.fxml"));
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

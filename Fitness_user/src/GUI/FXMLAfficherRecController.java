/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reclamation;
import entities.User;
import fitness_user.FXMain;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.ServiceReclamation;
import services.ServiceUsers;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.Mailapi;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class FXMLAfficherRecController implements Initializable {

    @FXML
    private ListView<Reclamation> listviewRec;
    ServiceReclamation sr =new ServiceReclamation();
    ServiceUsers su=new ServiceUsers();
    ObservableList<Reclamation> Reclamation=FXCollections.observableArrayList();
    @FXML
    private TextField tfsujet;
    @FXML
    private TextArea tfmessage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshlist();
    }    

    @FXML
    private void supprimer(ActionEvent event) {
        sr.supprimer(listviewRec.getSelectionModel().getSelectedItem().getId());
        refreshlist();
        TrayNotification tray=new TrayNotification();
            tray.setAnimationType(AnimationType.POPUP);
            tray.setTitle("suppression avec succes");
            tray.setMessage("Reclmation a ete supprime");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2000));
    }

    @FXML
    private void repondreRec(ActionEvent event) {
        if(listviewRec.getSelectionModel().getSelectedItem()!=null){
            User u=su.findById(listviewRec.getSelectionModel().getSelectedItem().getIduser());
            Mailapi.send("fitnessesprit8@gmail.com", "mailapi123", u.getEmail(), tfsujet.getText(), tfmessage.getText());
            tfsujet.setText("");
            tfmessage.setText("");
            sr.supprimer(listviewRec.getSelectionModel().getSelectedItem().getId());
            refreshlist();
        }
    }
    public void refreshlist(){
        Reclamation=FXCollections.observableArrayList(sr.afficher());
        listviewRec.setItems(Reclamation);
    }

    @FXML
    private void gotoGSTusr(ActionEvent event) {
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
}

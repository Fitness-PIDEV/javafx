/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Admin;
import entities.Coach;
import entities.Livreur;
import entities.User;
import fitness_user.FXMain;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
public class FXMLGSTUserController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField pfpassword;
    @FXML
    private TextField tfnumero;
    @FXML
    private ComboBox<String> comborole;
    @FXML
    private CheckBox chechdisponible;
    @FXML
    private ListView<User> listviewuser;
    @FXML
    private TextField tfrecherche;
    ObservableList<String> roles=FXCollections.observableArrayList();
    ObservableList<User> users=FXCollections.observableArrayList();
    ServiceUsers su=new ServiceUsers();
    @FXML
    private Button btncertificat;
    @FXML
    private ImageView image;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        chechdisponible.setDisable(true);
        btncertificat.setDisable(true);
        roles.add("USER");
        roles.add("ADMIN");
        roles.add("COACH");
        roles.add("LIVREUR");
        comborole.setItems(roles);
        refreshlist();
        recherche_avance();
        
        /*
        if(comborole.getValue().equals("COACH")){
            btncertificat.setDisable(false);
        }
        if(comborole.getValue().equals("LIVREUR")){
            chechdisponible.setDisable(false);
        }
        */
        
    }    

    @FXML
    private void uploaduserimage(ActionEvent event) {
    }

    @FXML
    private void uploadcertificat(ActionEvent event) {
    }

    @FXML
    private void fillforum(MouseEvent event) throws FileNotFoundException {
        User u=listviewuser.getSelectionModel().getSelectedItem();
        tfnom.setText(u.getNom());
        tfprenom.setText(u.getPrenom());
        tfemail.setText(u.getEmail());
        tfnumero.setText(String.valueOf(u.getNum()));
        pfpassword.setText(u.getMdp());
        if(u instanceof Admin){
            comborole.setValue("ADMIN");
        }
        else if(u instanceof Coach){
            comborole.setValue("COACH");
        }
        else if(u instanceof Livreur){
            comborole.setValue("LIVREUR");
            chechdisponible.setSelected(((Livreur) u).isDispo());
        }
        else{
            comborole.setValue("USER");
        }
        recherche_avance();
    }

    @FXML
    private void ajouter(ActionEvent event) {
        String errors="";
        if(tfnom.getText().trim().isEmpty()){
            
            errors+="Saisire votre nom\n";
        }
        if(tfprenom.getText().trim().isEmpty()){
            errors+="Saisire votre prenom\n";
        }
        if(tfemail.getText().trim().isEmpty()){
            errors+="Saisire votre email\n";
        }
        if(tfnumero.getText().trim().isEmpty()){
            errors+="Saisire votre numero\n";
        }
        if(pfpassword.getText().trim().isEmpty()){
            errors+="Saisire votre mot de passe\n";
        }
        if(errors.length()>0){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText(errors);
            alert.showAndWait(); 
        }
        else{
            
            User u=new User();
            Admin a =new Admin();
            Livreur l=new Livreur();
            Coach c=new Coach();
            
            if(comborole.getValue().equals("COACH")){
                c.setNom(tfnom.getText());
                c.setPrenom(tfprenom.getText());
                c.setEmail(tfemail.getText());
                c.setMdp(pfpassword.getText());
                c.setNum(Integer.parseInt(tfnumero.getText()));
                c.setPhoto("ee");
                c.setCertificateURL("test");
                su.ajouter(c);
            }
            else if(comborole.getValue().equals("LIVREUR")){
                l.setNom(tfnom.getText());
                l.setPrenom(tfprenom.getText());
                l.setEmail(tfemail.getText());
                l.setMdp(pfpassword.getText());
                l.setNum(Integer.parseInt(tfnumero.getText()));
                l.setPhoto("ee");
                l.setDispo(chechdisponible.isSelected());
                su.ajouter(l);
            }
            else if(comborole.getValue().equals("ADMIN")){
                a.setNom(tfnom.getText());
                a.setPrenom(tfprenom.getText());
                a.setEmail(tfemail.getText());
                a.setMdp(pfpassword.getText());
                a.setNum(Integer.parseInt(tfnumero.getText()));
                a.setPhoto("ee");
                su.ajouter(a);
            }
            else{
                u.setNom(tfnom.getText());
                u.setPrenom(tfprenom.getText());
                u.setEmail(tfemail.getText());
                u.setMdp(pfpassword.getText());
                u.setNum(Integer.parseInt(tfnumero.getText()));
                u.setPhoto("ee");
                su.ajouter(u);
            }
            tfnom.setText("");
            tfprenom.setText("");
            tfemail.setText("");
            tfnumero.setText("");
            pfpassword.setText("");
            TrayNotification tray=new TrayNotification();
            tray.setAnimationType(AnimationType.POPUP);
            tray.setTitle("Ajout avec succes");
            tray.setMessage("User a ete ajouter");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2000));
            
        }
        refreshlist();
        recherche_avance();
    }

    @FXML
    private void modifier(ActionEvent event) throws MalformedURLException {
        String errors="";
        if(tfnom.getText().trim().isEmpty()){
            
            errors+="Saisire votre nom\n";
        }
        if(tfprenom.getText().trim().isEmpty()){
            errors+="Saisire votre prenom\n";
        }
        if(tfemail.getText().trim().isEmpty()){
            errors+="Saisire votre email\n";
        }
        if(tfnumero.getText().trim().isEmpty()){
            errors+="Saisire votre numero\n";
        }
        if(pfpassword.getText().trim().isEmpty()){
            errors+="Saisire votre mot de passe\n";
        }
        if(errors.length()>0){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText(errors);
            alert.showAndWait(); 
        }
        else{
            
            User u=new User();
            Admin a =new Admin();
            Livreur l=new Livreur();
            Coach c=new Coach();
            
            if(comborole.getValue().equals("COACH")){
                c.setNom(tfnom.getText());
                c.setPrenom(tfprenom.getText());
                c.setEmail(tfemail.getText());
                c.setMdp(pfpassword.getText());
                c.setNum(Integer.parseInt(tfnumero.getText()));
                c.setPhoto("ee");
                c.setCertificateURL("test");
                su.modifer(c, listviewuser.getSelectionModel().getSelectedItem().getId());
            }
            else if(comborole.getValue().equals("LIVREUR")){
                l.setNom(tfnom.getText());
                l.setPrenom(tfprenom.getText());
                l.setEmail(tfemail.getText());
                l.setMdp(pfpassword.getText());
                l.setNum(Integer.parseInt(tfnumero.getText()));
                l.setPhoto("ee");
                l.setDispo(chechdisponible.isSelected());
                su.modifer(l, listviewuser.getSelectionModel().getSelectedItem().getId());
            }
            else if(comborole.getValue().equals("ADMIN")){
                a.setNom(tfnom.getText());
                a.setPrenom(tfprenom.getText());
                a.setEmail(tfemail.getText());
                a.setMdp(pfpassword.getText());
                a.setNum(Integer.parseInt(tfnumero.getText()));
                a.setPhoto("ee");
                su.modifer(a, listviewuser.getSelectionModel().getSelectedItem().getId());
            }
            else{
                u.setNom(tfnom.getText());
                u.setPrenom(tfprenom.getText());
                u.setEmail(tfemail.getText());
                u.setMdp(pfpassword.getText());
                u.setNum(Integer.parseInt(tfnumero.getText()));
                u.setPhoto("ee");
                su.modifer(u, listviewuser.getSelectionModel().getSelectedItem().getId());
            }
            tfnom.setText("");
            tfprenom.setText("");
            tfemail.setText("");
            tfnumero.setText("");
            pfpassword.setText("");
            TrayNotification tray=new TrayNotification();
            tray.setAnimationType(AnimationType.POPUP);
            tray.setTitle("modification avec succes");
            tray.setMessage("User a ete modifie");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2000));
            
        }
        refreshlist();
        recherche_avance();
    }

    @FXML
    private void supprimer(ActionEvent event) {
           
           
           // refreshlist();
            
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous vraiment supprimer l'utilisateur ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            su.supprimer(listviewuser.getSelectionModel().getSelectedItem().getId());
            refreshlist();
            tfnom.setText("");
            tfprenom.setText("");
            tfemail.setText("");
            tfnumero.setText("");
            pfpassword.setText("");
            TrayNotification tray=new TrayNotification();
            tray.setAnimationType(AnimationType.POPUP);
            tray.setTitle("suppression avec succes");
            tray.setMessage("User a ete supprime");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2000));
            recherche_avance();

        } else {
            alert2.close();
        }
    }

    @FXML
    private void trie(ActionEvent event) {
        users=FXCollections.observableArrayList(su.sortByNum());
        listviewuser.setItems(users);
        recherche_avance();
    }
    public void refreshlist(){
        users=FXCollections.observableArrayList(su.afficher());
        listviewuser.setItems(users);
    }

    

    @FXML
    private void checkrole(ActionEvent event) {
        chechdisponible.setDisable(true);
        btncertificat.setDisable(true);
        if(comborole.getValue()!=null){
            if(comborole.getValue().equals("COACH")){
                btncertificat.setDisable(false);
            }
            if(comborole.getValue().equals("LIVREUR")){
                chechdisponible.setDisable(false);
            }
        }
    }
    public void recherche_avance(){
        FilteredList<User> filtredlist=new FilteredList<>(users,b->true);
        tfrecherche.textProperty().addListener(
                (observable,oldValue,newValue)->{
                    
                    filtredlist.setPredicate(user->{
                        if(newValue==null||newValue.isEmpty()){
                            return true;
                        }
                        if(user.getNom().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                            return true;
                        }
                        else if(user.getPrenom().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                            return true;
                        }
                        else if(user.getEmail().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                            return true;
                        }
                        else if(user.getMdp().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                            return true;
                        }
                        else if(String.valueOf(user.getNum()).toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                            return true;
                        }
                        else if(user.getPhoto().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                            return true;
                        }
                        return false;
                    });
                }
        );
        listviewuser.setItems(filtredlist);
    }

    @FXML
    private void gotoRec(ActionEvent event) {
        try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();

                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLAfficherRec.fxml"));
                Stage stage =new Stage();

                Scene scene = new Scene(root);

                stage.setTitle("Reclamations");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void gotologin(ActionEvent event) {
        try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();

                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLLogin.fxml"));
                Stage stage =new Stage();

                Scene scene = new Scene(root);

                stage.setTitle("Login");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void goHome(ActionEvent event) {
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
    
}

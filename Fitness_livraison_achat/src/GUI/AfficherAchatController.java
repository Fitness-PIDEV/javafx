/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.DocumentException;
import entities.achat;
import fitness_user.FXMain;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import services.ServiceAchat;
import services.ServiceLivraison;
import services.ServicePdf;

/**
 * FXML Controller class
 *
 * @author khalil kouki
 */
public class AfficherAchatController implements Initializable {

    @FXML
    private ListView<achat> listviewachat;
    @FXML
    private TextField tf_ref_prod;
    @FXML
    private TextField tf_prix;
    ObservableList<achat> achat = FXCollections.observableArrayList();
    ServiceAchat sa = new ServiceAchat();
    @FXML
    private TextField recherche;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btnn_notif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
               new animatefx.animation.LightSpeedIn(btn_modifier).setCycleCount(10000).play();
                              new animatefx.animation.LightSpeedIn(btn_supprimer).setCycleCount(10000).play();
                              
                              ServiceAchat ach = new ServiceAchat();
        
         btnn_notif.setOnAction(event -> {

             if(ach.nombreachat()>0){
                 
                 Notifications.create()
                         .title("Livraison")
                         .text("   VOUS AVEZ "+ach.nombreachat()+" ACHATS !")
                         .darkStyle()
                         .position(Pos.TOP_RIGHT)
                         .showWarning();
             }
        });

        
        
        refreshlist();
        recherche();
    }

    public void refreshlist() {
        achat = FXCollections.observableArrayList(sa.afficher());
        listviewachat.setItems(achat);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        sa.supprimer(listviewachat.getSelectionModel().getSelectedItem().getId_produit());
        refreshlist();
    }

    @FXML
    private void modifier(ActionEvent event) {
          String errors = "";
        if (tf_ref_prod.getText().trim().isEmpty()) {

            errors += "Saisire reference produit\n";
        }
        if (tf_prix.getText().trim().isEmpty()) {
            errors += "Saisire prix\n";
        }
       
        
        if (tf_ref_prod.getText().trim().length() <=8) {
            int nbChar = 0;
            for (int i = 1; i < tf_ref_prod.getText().trim().length(); i++) {
                char ch = tf_ref_prod.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;
                     errors += "format reference produit invalide  \n";

                }
                
            }

           
            }
        
         if (tf_prix.getText().trim().length() <=12) {
            int nbChar = 0;
            for (int i = 1; i < tf_ref_prod.getText().trim().length(); i++) {
                char ch = tf_prix.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;
                     errors += "format prix invalide\n";

                }
                
            }

           
            }

       
        
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText(errors);
            alert.showAndWait();
        } else {
        achat a = new achat();
        a.setRef_produit(Integer.parseInt(tf_ref_prod.getText()));
        a.setPrix((Float) Float.parseFloat(tf_prix.getText()));
        sa.modifer(a, listviewachat.getSelectionModel().getSelectedItem().getId_produit());
        refreshlist();
    }}

    @FXML
    private void fill(MouseEvent event) {
        achat a = listviewachat.getSelectionModel().getSelectedItem();
        tf_ref_prod.setText(String.valueOf(a.getRef_produit()));
        tf_prix.setText(String.valueOf(a.getPrix()));

        
        
        
    }

    private void tri(ActionEvent event) {
       // sa.tri();
        refreshlist();
    }

    public void recherche(){
        FilteredList<achat> filtredlist=new FilteredList<>(achat,b->true);
        recherche.textProperty().addListener(
                (observable,oldValue,newValue)->{
                    
                    filtredlist.setPredicate(achat->{
                       if(String.valueOf(achat.getPrix()).toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                            return true;
                        }
                        else if(String.valueOf(achat.getRef_produit()).toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                            return true;
                        }
                       
                        
                        return false;
                    });
                }
        );
        listviewachat.setItems(filtredlist);
    }

    @FXML
    private void PDF(ActionEvent event) throws FileNotFoundException, DocumentException {
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste de vos achats?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
             ServicePdf sp= new ServicePdf();
        sp.liste_achatPDF();
        }
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

    @FXML
    private void goabonnement(ActionEvent event) {
        try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();

                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/GUI/afficherabonnement.fxml"));
                Stage stage =new Stage();

                Scene scene = new Scene(root);

                stage.setTitle("Gestion abonnement");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void gohome(ActionEvent event) {
        try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();

                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/GUI/Admin_stat.fxml"));
                Stage stage =new Stage();

                Scene scene = new Scene(root);

                stage.setTitle("Gestion abonnement");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}

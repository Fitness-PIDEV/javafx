/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.DocumentException;
import entities.achat;
import entities.livraison;
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
public class AfficherLivraisonController implements Initializable {

    @FXML
    private ListView<livraison> listviewlivraison;
    @FXML
    private TextField tf_recherche;
    @FXML
    private TextField tf_ref_com;
    @FXML
    private TextField tf_frais;
    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_donnee;
    @FXML
    private TextField tf_localisation;
    ObservableList<livraison> livraison = FXCollections.observableArrayList();
    ServiceLivraison sl = new ServiceLivraison();
    @FXML
    private Button btnn_notif;
    @FXML
    private Button btn_pdf;
    @FXML
    private Button btn_supp;
    @FXML
    private Button btn_mod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          new animatefx.animation.LightSpeedIn(btn_mod).setCycleCount(10000).play();
                              new animatefx.animation.LightSpeedIn(btn_supp).setCycleCount(10000).play();
        
        ServiceLivraison liv = new ServiceLivraison();
        
         btnn_notif.setOnAction(event -> {

             if(liv.nombrelivraison()>0){
                 
                 Notifications.create()
                         .title("Livraison")
                         .text("   VOUS AVEZ "+liv.nombrelivraison()+" LIVRAISONS !")
                         .darkStyle()
                         .position(Pos.TOP_RIGHT)
                         .showWarning();
             }
        });
        refreshlist();
        recherche();

    }

    public void refreshlist() {
        livraison = FXCollections.observableArrayList(sl.afficher());
        listviewlivraison.setItems(livraison);
    }

    @FXML
    private void recherche() {
        FilteredList<livraison> filtredlist = new FilteredList<>(livraison, b -> true);
        tf_recherche.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    filtredlist.setPredicate(livraison -> {
                        if (String.valueOf(livraison.getRef_commande()).toLowerCase().indexOf(newValue.toLowerCase()) != -1) {
                            return true;
                        } else if (String.valueOf(livraison.getFrais_livraison()).toLowerCase().indexOf(newValue.toLowerCase()) != -1) {
                            return true;
                        } else if (String.valueOf(livraison.getId_user()).toLowerCase().indexOf(newValue.toLowerCase()) != -1) {
                            return true;
                        } else if (String.valueOf(livraison.getDonnees_user()).toLowerCase().indexOf(newValue.toLowerCase()) != -1) {
                            return true;
                        } else if (String.valueOf(livraison.getLocalisation()).toLowerCase().indexOf(newValue.toLowerCase()) != -1) {
                            return true;
                        }

                        return false;
                    });
                }
        );
        listviewlivraison.setItems(filtredlist);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        sl.supprimer(listviewlivraison.getSelectionModel().getSelectedItem().getId_livraison());
        refreshlist();
    }

    @FXML
    private void modifier(ActionEvent event) {
        String errors = "";
        if (tf_ref_com.getText().trim().isEmpty()) {

            errors += "Saisire reference commande\n";
        }
        if (tf_frais.getText().trim().isEmpty()) {
            errors += "Saisire frais livraison\n";
        }
        if (tf_id.getText().trim().isEmpty()) {
            errors += "Saisire votre ID\n";
        }
        if (tf_donnee.getText().trim().isEmpty()) {
            errors += "Saisire votre donnees\n";
        }
        if (tf_localisation.getText().trim().isEmpty()) {
            errors += "Saisire votre localisation\n";
        }
        
        
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText(errors);
            alert.showAndWait();
        } else {
            livraison l = new livraison();
            l.setRef_commande(Integer.parseInt(tf_ref_com.getText()));
            l.setFrais_livraison((Float) Float.parseFloat(tf_frais.getText()));
            l.setId_user(Integer.parseInt(tf_id.getText()));
            l.setDonnees_user(tf_donnee.getText());
            l.setLocalisation(tf_localisation.getText());
            sl.modifer(l, listviewlivraison.getSelectionModel().getSelectedItem().getId_livraison());
            refreshlist();
        }
    }

    @FXML
    private void fill(MouseEvent event) {
        livraison a = listviewlivraison.getSelectionModel().getSelectedItem();
        tf_ref_com.setText(String.valueOf(a.getRef_commande()));
        tf_frais.setText(String.valueOf(a.getFrais_livraison()));
        tf_id.setText(String.valueOf(a.getId_user()));
        tf_donnee.setText(String.valueOf(a.getDonnees_user()));
        tf_localisation.setText(String.valueOf(a.getLocalisation()));

    }

    @FXML
    private void notif(ActionEvent event) {
    }

    @FXML
   private void PDF(ActionEvent event) throws FileNotFoundException, DocumentException {
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste de vos livraisons?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
             ServicePdf sp= new ServicePdf();
        sp.liste_livraisonPDF();
        }
    }

    @FXML
    private void gotologin(MouseEvent event) {
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

}

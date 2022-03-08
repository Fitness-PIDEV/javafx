/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Alert.AlertDialog;
import entities.Produit;
import fitness_user.FXMain;
import services.produit_service;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterProduitController implements Initializable {
    
    @FXML
    private TextField Ffnom;
    @FXML
    private TextField Ffcategorie;
    @FXML
    private TextField Ffquantite;
    @FXML
    private TextField Ffetat;
    @FXML
    private Button Ffimage;
    @FXML
    private TextField Ffprix;
    @FXML
    private Button Finsert;
    @FXML
    private TextField Ffdecrip1;
    private ImageView img;
    @FXML
    private Button bb;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML

    private void insertion(ActionEvent event) throws SQLException {
        if(verifUserChampsajouter()){
        produit_service sp=new produit_service(); 
        Produit u = new Produit();
        u.setNom_produit(Ffnom.getText());
        u.setPrix_produit(Integer.parseInt(Ffprix.getText()));
        u.setID_categorie(Integer.parseInt(Ffcategorie.getText()));
        u.setDescription(Ffdecrip1.getText());
        u.setQuantite_produit(Integer.parseInt(Ffquantite.getText()));
        u.setEtat(Integer.parseInt(Ffetat.getText()));
            sp.Ajouter(u);
//            System.out.println("GUI.AjouterProduitController.insertion()");
         
            try {
                Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageclose.close();
                Parent root = FXMLLoader.load(getClass().getResource("affichageproduit.fxml"));
                Stage stage = new Stage();
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

        Ffnom.setStyle(styledefault);
        Ffcategorie.setStyle(styledefault);
        Ffprix.setStyle(styledefault);
        Ffetat.setStyle(styledefault);
        Ffquantite.setStyle(styledefault);
        Ffdecrip1.setStyle(styledefault);

        if (Ffnom.getText().equals("")) {
            Ffnom.setStyle(style);
            verif = 1;
        }
//        if (dateC.getValue().equals("")) {
//            dateC.setStyle(style);
//            verif = 1;
//        }
        if (Ffcategorie.getText().equals("")) {
            Ffcategorie.setStyle(style);
            verif = 1;
        }
        if (Ffprix.getText().equals("")) {
            Ffprix.setStyle(style);
            verif = 1;
        }
         if (Ffetat.getText().equals("")) {
            Ffetat.setStyle(style);
            verif = 1;
        }
          if (Ffquantite.getText().equals("")) {
            Ffquantite.setStyle(style);
            verif = 1;
        }
           if (Ffdecrip1.getText().equals("")) {
            Ffdecrip1.setStyle(style);
            verif = 1;
        }
          
        if (verif == 0) {
            return true;
        }
        
        JOptionPane.showMessageDialog(null, "Remplir tous les champs!");
        return false;
    }
    @FXML

    private void GestProduit1(ActionEvent event) {
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
    private void Gestcategorie1(ActionEvent event) {
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
    private void Gestcours1(ActionEvent event) {
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
byte[] produit_image = null;
    @FXML
    private void Ffimageinsertion(ActionEvent event) throws IOException {
        
        final FileChooser fileChooser = new FileChooser();
    String imagepath = "";
    String imageViewpath = "";
        File file = fileChooser.showOpenDialog(null);
        
        if (file != null) {
             String location = (file.getAbsoluteFile().toURI().toString());
           
            File dest = new File("C:\\Users\\Ahmed\\OneDrive\\Bureau\\javafx\\Fitness_user\\src\\Images"+file.getName());
            Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
          
             imagepath = file.getName();
             imageViewpath = file.getName();
             Image image = new Image("C:/Users/Ahmed/OneDrive/Bureau/javafx/Fitness_user/src/Images"+file.getName());
             img.setImage(image);
        
    }

   
}

}

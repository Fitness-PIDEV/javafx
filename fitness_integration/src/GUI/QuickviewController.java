/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.R;
import entities.Produit;
import services.produit_service;


public class QuickviewController implements Initializable {

    private Produit p1;
    produit_service service = new produit_service();

    @FXML
    private ImageView idimage_view;
   
    @FXML
    private Label nom;
    @FXML
    private Label categorie;
    @FXML
    private Label description;
    @FXML
    private Label Prix;
    @FXML
    private Label idcategorie;
    @FXML
    private Label name;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            p1 = service.GetRestobyid(Produit.getId_courant());
        } catch (SQLException ex) {
            Logger.getLogger(QuickviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        name.setText(p1.getNom_produit());
        String a = p1.getImage_produit();
        File file = new File(a);
         System.out.println(p1.getImage_produit());
        Image image1 = new Image(file.toURI().toString());
        idimage_view.setImage(image1);
     

        description.setText(p1.getDescription());

        idcategorie.setText(String.valueOf(p1.getID_categorie()));

    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Alert.AlertDialog;
import Service.categorie_service;
import Service.produit_service;
import entites.Categorie;
import entites.Produit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static javafx.scene.input.KeyCode.P;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author aymen
 */
public class main extends Application {
 
  @Override
    public void start(Stage primaryStage) throws SQLException {
      Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Starter.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
//    Produit P= new Produit("granooola",50,"image6",7,4,1);
//       produit_service ps = new produit_service();
//        ps.Ajouter(P);
//Produit p = new Produit("sss", 0, "STYLESHEET_MODENA", 0, 0, 1);
//    produit_service ps = new produit_service();
//        System.out.println(ps.Affichertout());
//        ps.Modifier(p, 4);
//        ps.Supprimer(p,12);
//        System.out.println(ps.Affichertout());
    }
}
    


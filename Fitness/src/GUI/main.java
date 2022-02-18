/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.categorie_service;
import entites.Categorie;
import Service.produit_service;
import entites.Produit;
import Service.cours_service;
import entites.Cours;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author aymen
 */
public class main extends Application {
 private double x, y;
    @Override
    public void start(Stage primaryStage) throws Exception {
/*  Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setScene(new Scene(root));
        //set stage borderless
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        //drag it here
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {

            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);

        });
        primaryStage.show();
    */


//----------------------------

      /* Categorie c = new Categorie();
        c.setNom_categorie("dedezdez");
        categorie_service cs = new categorie_service();
        cs.Ajouter(c);*/
        
       // Produit P= new Produit("granola",45,"image1",8,5);
        //produit_service ps = new produit_service();
        // ps.Ajouter(P);
       // ps.Supprimer(12);
       
         Cours P= new Cours("45","image1","salle8","aziz");
        cours_service ps = new cours_service();
         ps.Ajouter(P);
    
    }
    
}

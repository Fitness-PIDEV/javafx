/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Alert.AlertDialog;
import Service.categorie_service;
import entites.Categorie;
import Service.produit_service;
import entites.Produit;
import Service.cours_service;
import entites.Cours;
import java.util.ArrayList;
import java.util.List;
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
        
        /*produit_service ps = new produit_service();
         List<Produit> p= new ArrayList<>();
         p= ps.select_produit_by_cat(3);
      for(int i=0; i<p.size();i++)
      {
          System.out.println(p.get(i).getNom_produit());
      }
        */
        
     /*   produit_service ps = new produit_service();
        System.out.println(ps.recherche_par_prix(30));*/
        


//----------------------------

      /* Categorie c = new Categorie();
        c.setNom_categorie("dedezdez");
        categorie_service cs = new categorie_service();
        cs.Ajouter(c);*/
        /*
      Produit P= new Produit("granola",50,"image6",7,4);
       produit_service ps = new produit_service();
        ps.Ajouter(P);*/
       // ps.Supprimer(12);
       
        /* Cours P= new Cours("45","image1","salle8","aziz");
        cours_service ps = new cours_service();
         ps.Ajouter(P);*/
        
       /* produit_service ps = new produit_service();
        System.out.println(ps.Affichertout());*/
        
        /////// ------------notif----------/////
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
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
    
    }
    
}

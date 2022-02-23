/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.reservation_service;
import entites.Reservation;
import Service.planning_service;
import entites.planning;
import javafx.application.Application;
import javafx.collections.ObservableList;
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
    public void start(Stage primaryStage) throws Exception  {
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

       //Reservation c = new Reservation(5,"youssef","youssef",5);
        //reservation_service rs = new reservation_service();
//      rs.Ajouter(c);
//      rs.Supprimer(2);
//      rs.Modifier(c,2);
        
        
        planning p = new planning(6,"","","1","");
        planning_service ps = new planning_service();
        //ps.Ajouter(p);
        //ps.Supprimer(1);
        //ps.Modifier(p,2);
        //ps.Affichertout();
        //ps.AfficherPlanningParId();  
        //ps.AfficherPlanningParDuree();
        //ps.AfficherPlanningParNom_cours();
        //AfficherPlanningParNom_coach();
        //ps.AfficherPlanningParSalle();
        //ps.AfficherPlanningParSDate();
        //ps.TrierPlanningParId_cours();
        //ps.TrierPlanningParId_coursEtDuree();
    }

    
}

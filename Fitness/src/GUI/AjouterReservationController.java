/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import javafx.event.ActionEvent;
import services.reservation_service;
import utils.Mailapi;
import com.sun.javafx.tk.Toolkit;
import static com.sun.javafx.tk.Toolkit.getToolkit;
import entities.Reservation;
import fitness_user.FXMain;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public  class AjouterReservationController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private Button insert;
    @FXML
    private DatePicker date;
    @FXML
    private TextField dispo;
    @FXML
    private TextField nb;
    @FXML
    private TextField state;
    @FXML
    private Label erreur_nom;
    
    private boolean verificationUsernom ;
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
        reservation_service sp=new reservation_service(); 
        Reservation u = new Reservation();
        String s = state.getText();
        int s1 = Integer.parseInt(s);
        String d = dispo.getText();
        int d0 = Integer.parseInt(d);
       String nbr = nb.getText();
        int nbr0 = Integer.parseInt(nbr);
        //u.setState(Integer.parseInt(state.getText()));
        //u.setDuree(Integer.parseInt(dure.getText()));
        //u.setId_cours(Integer.parseInt(id_cours.getText()));
        LocalDate d1= date.getValue();
        Date datex= Date.valueOf(d1);
        String n = nom.getText();
        
            sp.Ajouter(new Reservation(d0,n,datex,nbr0,s1));
            
            JOptionPane.showMessageDialog(null, "reservation ajouter");
            Mailapi.send("fitnessesprit8@gmail.com", "mailapi123", "youssefbazdeh99@gmail.com" , "Reservation", "Vous avez reservez vos places avec succes");

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
    
    public boolean verifUserChampsajouter() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

        nom.setStyle(styledefault);
        dispo.setStyle(styledefault);
        nb.setStyle(styledefault);
        date.setStyle(styledefault);
        state.setStyle(styledefault);
        if (nom.getText().equals("")) {
            nom.setStyle(style);
            verif = 1;
        }
//        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
//        String strDate = dateFormat.format(date);  
        //System.out.println(strDate);
       if (date.toString().equals("")) {
            date.setStyle(style);
            System.out.println("aaa");
            verif = 1;
        }
        if (dispo.getText().equals("")) {
            dispo.setStyle(style);
            verif = 1;
        }
        if (nb.getText().equals("")) {
            nb.setStyle(style);
            verif = 1;
        }
           if (state.getText().equals("")) {
            state.setStyle(style);
            verif = 1;
        }
        if (verif == 0) {
            return true;
        }
        
        JOptionPane.showMessageDialog(null, "Remplir tous les champs!");
        return false;
    }

    @FXML
    private void testnom(KeyEvent event) {
    int nbNonChar = 0;
            for (int i = 1; i < nom.getText().trim().length(); i++) {
                char ch = nom.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && nom.getText().trim().length() >=3) {
            
            erreur_nom.setText("Nom valide");
            
            verificationUsernom = true;
            } else {
              
              erreur_nom.setText("Il faut au moins 3 caracteres");
              verificationUsernom = false;

            }

    }

    private void testdispo(java.awt.event.KeyEvent event) {
    char c= event.getKeyChar() ;
    if(!(Character.isDigit(c) || Character.isWhitespace(c) || Character.isISOControl(c) )){
 event.consume();
    }
    }

    @FXML
    private void testdispo(KeyEvent event) {
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
     
   
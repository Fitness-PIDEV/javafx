/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.FXMLLoginController.idglobaluser;
import entities.User;
import fitness_user.FXMain;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
public class ProfileUserController implements Initializable {

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
    private ImageView imgprofil;
    @FXML
    private Label sedeconnecter;
    @FXML
    private Label erreur_mail;
    @FXML
    private Label erreur_num;
    @FXML
    private Label erreur_prenom;
    @FXML
    private Label erreur_password;
    @FXML
    private Label erreur_nom;
    @FXML
    private Label labelConfirmationMdp;
    
    private boolean verificationUserPhone;
    private boolean verificationUserpasword;
    private boolean verificationUserEmail;
    private boolean verificationUsernom;
    private boolean verificationUserPrenom;
    private boolean verificationUserConfirmpasword;
    @FXML
    private TextField Confirmation_password;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void uploaduserimage(ActionEvent event) {
    }

    @FXML
    private void modifierProfile(ActionEvent event) {
        if (  verificationUserEmail &&  verificationUserPhone
                && verificationUserpasword  && verificationUsernom
                && verificationUserPrenom && verificationUserConfirmpasword ) 
        {
        ServiceUsers su=new ServiceUsers();
        User u=new User();
        idglobaluser=su.findIdByEmail(tfemail.getText());
        u.setNom(tfnom.getText());
                u.setPrenom(tfprenom.getText());
                u.setEmail(tfemail.getText());
                u.setMdp(pfpassword.getText());
                u.setNum(Integer.parseInt(tfnumero.getText()));
                u.setPhoto("ee");
                su.modifer(u, idglobaluser);
            tfnom.setText("");
            tfprenom.setText("");
            tfemail.setText("");
            tfnumero.setText("");
            pfpassword.setText("");
            Confirmation_password.setText("");
            TrayNotification tray=new TrayNotification();
            tray.setAnimationType(AnimationType.POPUP);
            tray.setTitle("modification avec succes");
            tray.setMessage("User a ete modifie");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2000));
            
        }
    }

    @FXML
    private void sedeconnecter(MouseEvent event) {
        try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void moncompte(MouseEvent event) {
    }

    @FXML
    private void afficherrec(MouseEvent event) {
        try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAjoutRec.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void magasin(MouseEvent event) {
    }

    @FXML
    private void planning(MouseEvent event) {
        try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("FRONTP.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void workout(MouseEvent event) {
    }

    @FXML
    private void testnom(KeyEvent event) {
        int nbNonChar = 0;
            for (int i = 1; i < tfnom.getText().trim().length(); i++) {
                char ch = tfnom.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && tfnom.getText().trim().length() >=3) {
           
            erreur_nom.setText("Nom valide");
            
            verificationUsernom = true;
            } else {
              
              erreur_nom.setText("Il faut au moins 3 caracteres");
              verificationUsernom = false;

            }
    }

    @FXML
    private void testprenom(KeyEvent event) {
        int nbNonChar = 0;
            for (int i = 1; i < tfprenom.getText().trim().length(); i++) {
                char ch = tfprenom.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && tfprenom.getText().trim().length() >=3) {
            
            erreur_prenom.setText("Prenom valide");
            
            verificationUserPrenom = true;
            } else {
               
                erreur_prenom.setText("Il faut au moins 3 caracteres");
                verificationUserPrenom = false;

            }
    }

    @FXML
    private boolean testmail(KeyEvent event) {
         String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (tfemail.getText() == null) {
            return false;
        }

        if (pat.matcher(tfemail.getText()).matches() == false) {
            verificationUserEmail = false;
            
            erreur_mail.setText("Veuillez verifier la forme ***@**.**");
            return false;
//            

        } else {
            
             erreur_mail.setText("Mail valide");
             verificationUserEmail = true;
        }
        return true;
    }

    @FXML
    private void testpassword(KeyEvent event) {
        String PAS = pfpassword.getText().trim();

        if (PAS.length() >= 6) {
            
            erreur_password.setText("Longeur juste");
            verificationUserpasword = true;
        }else{
       
        verificationUserpasword = false;
            erreur_password.setText("Utilisez au moins six caractères");
            
        }
    }
    
    public boolean isNumeric(String str){
        if(str==null){
            return false;
        }
        try
        {
            int x=Integer.parseInt(str);
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    @FXML
    private void testtel(KeyEvent event) {
         if (tfnumero.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < tfnumero.getText().trim().length(); i++) {
                char ch = tfnumero.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;

                }
                //System.out.println(nbChar);
            }

            if (isNumeric(tfnumero.getText())) {
                erreur_num.setText("Tel valide");
                 
                verificationUserPhone = true;
            } else {            
                erreur_num.setText("Tel non valide");
                verificationUserPhone = false;

            }

        } else {;
            erreur_num.setText("Il faut 8 chiffres");
            verificationUserPhone = false;
        }
        
    }

    @FXML
    private void testconfirmermdp(KeyEvent event) {
         if (pfpassword.getText().trim().equals(Confirmation_password.getText().trim())) {
            labelConfirmationMdp.setText("Mot de passe Conforme!");
             
            verificationUserConfirmpasword = true;
        } else {  
            labelConfirmationMdp.setText("Verifier votre mot de passe");
            verificationUserConfirmpasword = false;
        }
    }
    

    
}

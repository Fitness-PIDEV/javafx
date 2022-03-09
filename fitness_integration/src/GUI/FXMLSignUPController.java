/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.User;
import fitness_user.FXMain;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import services.ServiceUsers;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.Smsapi;


/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class FXMLSignUPController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfnumero;
    @FXML
    private PasswordField pfpassword;
    ServiceUsers su=new ServiceUsers();
    
    
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
    private ImageView emailCM;
    @FXML
    private ImageView nomCM;
    @FXML
    private ImageView numCM;
    @FXML
    private ImageView prenomCM;
    private boolean verificationUserPhone;
    private boolean verificationUserpasword;
    private boolean verificationUserEmail;
    private boolean verificationUsernom;
    private boolean verificationUserPrenom;
    private boolean verificationUserConfirmpasword;
    @FXML
    private ImageView passwordCM;
    @FXML
    private ImageView img;
    @FXML
    private Label labelConfirmationMdp;
    @FXML
    private ImageView Confirmation_passwordCheck;
    @FXML
    private TextField Confirmation_password;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void gotologin(ActionEvent event) {
        try {
            
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLLogin.fxml"));
            Stage stage =new Stage();
            
            Scene scene = new Scene(root);
            
            stage.setTitle("Fitness");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    User u=new User();
    @FXML
    private void signin(ActionEvent event) {
        if (  verificationUserEmail &&  verificationUserPhone
                && verificationUserpasword  && verificationUsernom
                && verificationUserPrenom && verificationUserConfirmpasword ) 
        {
            
            u.setNom(tfnom.getText());
            u.setPrenom(tfprenom.getText());
            u.setEmail(tfemail.getText());
            u.setMdp(pfpassword.getText());
            u.setNum(Integer.parseInt(tfnumero.getText()));
            //u.setPhoto("ee");
            su.ajouter(u);
            //Smsapi.sendSMS("+216"+u.getNum(), "Welcome to our application");
            TrayNotification tray=new TrayNotification();
            tray.setAnimationType(AnimationType.POPUP);
            tray.setTitle("User ajouté");
            tray.setMessage("Bienvenue !");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2000));
            
            try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();

                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLLogin.fxml"));
                Stage stage =new Stage();

                Scene scene = new Scene(root);

                stage.setTitle("signup");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
    }
    byte[] person_image = null;
    
    @FXML
    private void uploadimage(ActionEvent event) throws IOException {
      final FileChooser fileChooser = new FileChooser();
    String imagepath = "";
    String imageViewpath = "";
        File file = fileChooser.showOpenDialog(null);
        
        if (file != null) {
             String location = (file.getAbsoluteFile().toURI().toString());
           
            File dest = new File("C:\\Users\\Ahmed\\OneDrive\\Images"+file.getName());
            Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
          
            imagepath = file.getName();
            imageViewpath = file.getName();
            Image image = new Image("file:/C:/Users/Ahmed/OneDrive/Images"+file.getName());
            img.setImage(image);

    }
    }

    @FXML
    private boolean testmail() {
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
            emailCM.setImage(new Image("Images/erreurcheckMark.png"));
            erreur_mail.setText("Veuillez verifier la forme ***@**.**");
            return false;
//            

        } else {
            emailCM.setImage(new Image("Images/checkMark.png"));
             erreur_mail.setText("Mail valide");
             verificationUserEmail = true;
        }
        return true;
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
            nomCM.setImage(new Image("Images/checkMark.png"));
            erreur_nom.setText("Nom valide");
            
            verificationUsernom = true;
            } else {
              nomCM.setImage(new Image("Images/erreurcheckMark.png"));
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
            prenomCM.setImage(new Image("Images/checkMark.png"));
            erreur_prenom.setText("Prenom valide");
            
            verificationUserPrenom = true;
            } else {
                prenomCM.setImage(new Image("Images/erreurcheckMark.png"));
                erreur_prenom.setText("Il faut au moins 3 caracteres");
                verificationUserPrenom = false;

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
                 numCM.setImage(new Image("Images/checkMark.png"));
                verificationUserPhone = true;
            } else {             numCM.setImage(new Image("Images/erreurcheckMark.png"));
                erreur_num.setText("Tel non valide");
                verificationUserPhone = false;

            }

        } else {numCM.setImage(new Image("Images/erreurcheckMark.png"));
            erreur_num.setText("Il faut 8 chiffres");
            verificationUserPhone = false;
        }
        
    }

    @FXML
    private void testpassword(KeyEvent event) {
        String PAS = pfpassword.getText().trim();

        if (PAS.length() >= 6) {
            passwordCM.setImage(new Image("Images/checkMark.png"));
            erreur_password.setText("Longeur juste");
            verificationUserpasword = true;
        }else{
        passwordCM.setImage(new Image("Images/erreurcheckMark.png"));
        verificationUserpasword = false;
            erreur_password.setText("Utilisez au moins six caractères");
            
        }
    }

    @FXML
    private void testconfirmermdp(KeyEvent event) {
         if (pfpassword.getText().trim().equals(Confirmation_password.getText().trim())) {
            labelConfirmationMdp.setText("Mot de passe Conforme!");
             Confirmation_passwordCheck.setImage(new Image("Images/checkMark.png"));
            verificationUserConfirmpasword = true;
        } else {  Confirmation_passwordCheck.setImage(new Image("Images/erreurcheckMark.png"));
            labelConfirmationMdp.setText("Verifier votre mot de passe");
            verificationUserConfirmpasword = false;
        }
    }
}

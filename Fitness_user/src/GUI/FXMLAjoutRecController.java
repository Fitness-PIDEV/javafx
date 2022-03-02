/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reclamation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class FXMLAjoutRecController implements Initializable {

    @FXML
    private TextField TFtitre;
    @FXML
    private TextField TFmessage;
    ServiceReclamation sr =new ServiceReclamation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyerRec(ActionEvent event) {
        
        
        Reclamation r=new Reclamation();
        r.setTitre(TFtitre.getText());
        r.setMessage(TFmessage.getText());
        r.setIduser(FXMLLoginController.idglobaluser);
        sr.ajouter(r);
        TFtitre.setText("");
        TFmessage.setText("");
    }
    
}

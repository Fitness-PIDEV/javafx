/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.achat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.ServiceAchat;

/**
 * FXML Controller class
 *
 * @author khalil kouki
 */
public class AfficherAchatController implements Initializable {

    @FXML
    private ListView<achat> listviewachat;
    @FXML
    private TextField tf_ref_prod;
    @FXML
    private TextField tf_prix;
    ObservableList<achat> achat = FXCollections.observableArrayList();
    ServiceAchat sa = new ServiceAchat();
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshlist();
        recherche();
    }

    public void refreshlist() {
        achat = FXCollections.observableArrayList(sa.afficher());
        listviewachat.setItems(achat);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        sa.supprimer(listviewachat.getSelectionModel().getSelectedItem().getId_produit());
        refreshlist();
    }

    @FXML
    private void modifier(ActionEvent event) {
        achat a = new achat();
        a.setRef_produit(Integer.parseInt(tf_ref_prod.getText()));
        a.setPrix((Float) Float.parseFloat(tf_prix.getText()));
        sa.modifer(a, listviewachat.getSelectionModel().getSelectedItem().getId_produit());
        refreshlist();
    }

    @FXML
    private void fill(MouseEvent event) {
        achat a = listviewachat.getSelectionModel().getSelectedItem();
        tf_ref_prod.setText(String.valueOf(a.getRef_produit()));
        tf_prix.setText(String.valueOf(a.getPrix()));

    }

    @FXML
    private void tri(ActionEvent event) {
       // sa.tri();
        refreshlist();
    }

    public void recherche(){
        FilteredList<achat> filtredlist=new FilteredList<>(achat,b->true);
        recherche.textProperty().addListener(
                (observable,oldValue,newValue)->{
                    
                    filtredlist.setPredicate(achat->{
                       if(String.valueOf(achat.getPrix()).toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                            return true;
                        }
                        else if(String.valueOf(achat.getRef_produit()).toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                            return true;
                        }
                       
                        
                        return false;
                    });
                }
        );
        listviewachat.setItems(filtredlist);
    }

}

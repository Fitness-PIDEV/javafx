/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Alert.AlertDialog;
import IService.IService;
import Utils.MyConnexion;
import entites.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author USER
 */
public class produit_service implements IService<Produit>{

    private Connection c = MyConnexion.getInsCon().getcnx();
    @Override
    public void Ajouter(Produit u) throws SQLException {
       PreparedStatement ps;
        
        
        String query = "INSERT INTO `produit`( `nom_produit`, `prix_produit`, `image_produit`, `quantite_produit`, `ID_categorie`) VALUES ('"+u.getNom_produit()+"','"+u.getPrix_produit()+"','"+u.getImage_produit()+"','"+u.getQuantite_produit()+"','"+u.getID_categorie()+"')";
        try {
            ps = c.prepareStatement(query);

           /*  ps.setString(1, u.getNom_produit());
          ps.setInt(1, u.getPrix_produit());
          ps.setString(2, u.getImage_produit());
          ps.setInt(3, u.getQuantite_produit());
          ps.setInt(4, u.getID_categorie());*/
            ps.execute();    
            System.out.println(u);
           AlertDialog.showNotification("ajout","avec succee", AlertDialog.image_cross);
        } catch (Exception e) { 
            System.out.println(e);
        }  



    }

    @Override
    public void Supprimer(int t) throws SQLException {
     PreparedStatement ps;

        String query = "UPDATE `produit` SET `etat`=?";
  
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1,0);

            ps.execute();

            System.out.println("suppression de produit");
        } catch (Exception e) {
            System.out.println(e);
        } 
    
    
    }

    @Override
    public void Modifier(Produit u, int id) throws SQLException {
 
      PreparedStatement ps;
        String query = "UPDATE `produit` SET `nom_produit`=? WHERE `ID`=?";
        try {
            
            ps = c.prepareStatement(query);
         
            ps.setString(1, u.getNom_produit());
          
       
            ps.setInt(2, id);
            ps.execute();
   

        } catch (Exception e) {
        }
    
    
    }

    @Override
    public ObservableList<Produit> Affichertout() throws SQLException {
    ObservableList<Produit> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `produit` WHERE etat=1";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Produit(rs.getInt("ID"),rs.getString("nom_produit"),rs.getInt("prix_produit"),rs.getString("image_produit"),rs.getInt("quantite_produit"),rs.getInt("ID_categorie")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    
    }
    
     public ObservableList<Produit> recherche_par_prix(float p) throws SQLException {
        ObservableList<Produit> list = FXCollections.observableArrayList();
        list=Affichertout();
        List<Produit> list2 = list.stream().filter(c->c.getPrix_produit()>p).collect(Collectors.toList());

        return FXCollections.observableArrayList(list2);
    }

    public List<Produit> select_produit_by_cat(int categorie_id)
     { PreparedStatement ps;
        List<Produit> produits=new ArrayList<>();
        String sql = "SELECT * FROM `produit` where `ID_categorie`=?" ;
       
        try {
            ps = c.prepareStatement(sql);

            ps.setInt(1,categorie_id);
           ResultSet rs = ps.executeQuery();
          while (rs.next()){
          produits.add(new Produit(rs.getInt("ID"),rs.getString("nom_produit"),rs.getInt("prix_produit"),rs.getString("image_produit"),rs.getInt("quantite_produit"),rs.getInt("ID_categorie")));
          
          }
            System.out.println("affichage produit par categorie");
        } catch (Exception e) {
            System.out.println(e);
        } 
        return produits;
     
     }
    
}

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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 *
 * @author USER
 */
public class produit_service implements IService<Produit>{

    private Connection c = MyConnexion.getInsCon().getcnx();
    @Override
    public void Ajouter(Produit u) throws SQLException {
       PreparedStatement ps;
        
        
        String query = "INSERT INTO `produit`( `nom_produit`, `prix_produit`, `image_produit`, `quantite_produit`, `ID_categorie`, `etat`, `description`) VALUES ('"+u.getNom_produit()+"','"+u.getPrix_produit()+"','"+u.getImage_produit()+"','"+u.getQuantite_produit()+"','"+u.getID_categorie()+"','"+u.getEtat()+"','"+u.getDescription()+"')";
        try {
            ps = c.prepareStatement(query);

           /*  ps.setString(1, u.getNom_produit());
          ps.setInt(1, u.getPrix_produit());
          ps.setString(2, u.getImage_produit());
          ps.setInt(3, u.getQuantite_produit());
          ps.setInt(4, u.getID_categorie());*/
            ps.execute();    
            System.out.println(u);
         AlertDialog.showNotification("ajout","avec succee", AlertDialog.image_checked);
        } catch (Exception e) { 
            System.out.println(e);
        }  



    }
    
    public List afficherid() {
        List list = new ArrayList<>();
        
        try {
            String requete = "SELECT id FROM produit";
            Statement pst = c.prepareStatement(requete);          
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Produit(rs.getInt(1)).getID()); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
    @Override
     public void Supprimer(Produit p,int id) throws SQLException {
        PreparedStatement ps;

        String query = "UPDATE produit SET etat=0 WHERE id ='"+id+"'";
  
        try {
            ps = c.prepareStatement(query);

//            ps.setInt(1,p.getEtat());
            //ps.setInt(2,r.getId());
            ps.execute();

            System.out.println("suppression de produit");
        } catch (Exception e) {
            System.out.println(e);
        } 
    }

   /* @Override
    public void Supprimer( int t) throws SQLException {
     PreparedStatement ps;

        String query = "UPDATE `produit` SET `etat`=? ";
  
        try {
            ps = c.prepareStatement(query);
             // ps.setInt(1, t.getID());
            ps.setInt(1,0);

            ps.execute();

            System.out.println("suppression de produit");
        } catch (Exception e) {
            System.out.println(e);
        } 
    
    
    }*/

    @Override
  public void Modifier(Produit p, int id) {
       PreparedStatement ps;
        String query = "UPDATE produit SET `nom_produit`=?,`prix_produit`=?,`image_produit`=?,`quantite_produit`=?,`ID_categorie`=?,`etat`=?,`descriprion`=? WHERE `ID`="+id;
        try {
            
            ps = c.prepareStatement(query);
         
            ps.setString(1, p.getNom_produit());
            ps.setInt(2, p.getPrix_produit());
            ps.setString(3, p.getImage_produit());
             ps.setInt(4, p.getQuantite_produit());
              ps.setInt(5, p.getID_categorie());
               ps.setInt(6, p.getEtat());
                 ps.setString(7, p.getDescription());
            
            
            ps.execute();
   

        } catch (Exception e) {
            System.out.println(e);
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
                list.add(new Produit(rs.getInt("ID"),rs.getString("nom_produit"),rs.getInt("prix_produit"),rs.getString("image_produit"),rs.getInt("quantite_produit"),rs.getInt("ID_categorie"),rs.getInt("etat"),rs.getString("description")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    
    }
    
    public List<Produit> Recherche(String nom) throws SQLException {

        return Affichertout().stream().filter(a -> a.getNom_produit().equals(nom)).collect(Collectors.toList());

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
          produits.add(new Produit(rs.getInt("ID"),rs.getString("nom_produit"),rs.getInt("prix_produit"),rs.getString("image_produit"),rs.getInt("quantite_produit"),rs.getInt("ID_categorie"),rs.getInt("etat"),rs.getString("description")));
          
          }
            System.out.println("affichage produit par categorie");
        } catch (Exception e) {
            System.out.println(e);
        } 
        return produits;
     
    }
    
}

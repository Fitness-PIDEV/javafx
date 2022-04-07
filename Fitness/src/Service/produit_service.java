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
    public Produit GetProdbyid(int b) throws SQLException {

        //-------------------- Update ----------//
        Produit pr = new Produit();

        String query = "select * from produit where id = ? ";
        PreparedStatement ps;
        try {
              ps = MyConnexion.getInsCon().getcnx().prepareCall(query);
            ps.setInt(1, b);
            ResultSet rest = ps.executeQuery();

            while (rest.next()) {
                pr.setID(rest.getInt("id"));
                pr.setNom_produit(rest.getString("nom"));
                pr.setPrix_produit(rest.getInt("prix"));
                pr.setImage_produit(rest.getString("photo"));
                pr.setDescription(rest.getString("description"));
                pr.setID_categorie(rest.getInt("Categorie"));
                 pr.setEtat(rest.getInt("etat"));
               
                

            }

        } catch (SQLException ex) {
            Logger.getLogger(produit_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pr;

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
        String query = "UPDATE produit SET `nom_produit`=?,`prix_produit`=?,`image_produit`=?,`quantite_produit`=?,`ID_categorie`=?,`etat`=?,`description`=? WHERE `ID`="+id;
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
    public long Recherche2() throws SQLException {

  List<Produit> p = Affichertout();
        return p.stream().filter(b -> b.getQuantite_produit()> 1).filter(b -> b.getQuantite_produit()< 10 ).count();
    }
     public long Recherche3() throws SQLException {

  List<Produit> p = Affichertout();
        return p.stream().filter(b -> b.getQuantite_produit()> 10).filter(b -> b.getQuantite_produit()< 20 ).count();
    }
      public long Recherche4() throws SQLException {

  List<Produit> p = Affichertout();
        return p.stream().filter(b -> b.getQuantite_produit()> 20).filter(b -> b.getQuantite_produit()< 30).count();
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

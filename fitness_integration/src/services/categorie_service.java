/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Alert.AlertDialog;
import IService.IService;
import utils.MyDB;
import entities.Categorie;
import entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author USER
 */
public class categorie_service implements IService<Categorie>{

    private Connection c = MyDB.getInstance().getCnx();
    @Override
    public void Ajouter(Categorie u) throws SQLException {
       PreparedStatement ps;
        
        
        String query = "INSERT INTO `categorie`( `nom_categorie`,`etat`) VALUES (?,?)";
        try {
            ps = c.prepareStatement(query);

             ps.setString(1, u.getNom_categorie());
              ps.setInt(2, u.getEtat());
          
            ps.execute();    
            System.out.println(u);
            AlertDialog.showNotification("ajout","avec succee", AlertDialog.image_checked);
        } catch (Exception e) { 
            System.out.println(e);
        }  



    }
 public void Supprimer(Categorie ca,int id) throws SQLException {
        PreparedStatement ps;

        String query = "UPDATE categorie SET etat=0 WHERE id ='"+id+"'";
  
        try {
            ps = c.prepareStatement(query);

//            ps.setInt(1,p.getEtat());
            //ps.setInt(2,r.getId());
            ps.execute();

            System.out.println("suppression de  categorie");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
   /* @Override
    public void Supprimer(int t) throws SQLException {
     PreparedStatement ps;

        String query = "UPDATE `categorie` SET `etat`=?  ";
  
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1,0);

            ps.execute();

            System.out.println("suppression de categorie");
        } catch (Exception e) {
            System.out.println(e);
        } 
    
    
    }*/

    @Override
    public void Modifier(Categorie u, int id) throws SQLException {
 
      PreparedStatement ps;
        String query = "UPDATE categorie SET `nom_categorie`=?,`etat`=? WHERE `ID`="+id;
        try {
            
            ps = c.prepareStatement(query);
         
            ps.setString(1, u.getNom_categorie());
            ps.setInt(2, u.getEtat());
          
            
            
            ps.execute();
   

        } catch (Exception e) {
            System.out.println(e);
        }
    
    
    }

    @Override
    public ObservableList<Categorie> Affichertout() throws SQLException {
    ObservableList<Categorie> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `categorie` WHERE etat=1";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Categorie(rs.getInt("ID"),rs.getString("nom_categorie"),rs.getInt("etat")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    
    }
    
     
      public List<Categorie> Recherche(String nom) throws SQLException {

        return Affichertout().stream().filter(a -> a.getNom_categorie().equals(nom)).collect(Collectors.toList());

    }

    
    
}

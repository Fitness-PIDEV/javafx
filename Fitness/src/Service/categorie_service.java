/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import IService.IService;
import Utils.MyConnexion;
import entites.Categorie;
import entites.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author USER
 */
public class categorie_service implements IService<Categorie>{

    private Connection c = MyConnexion.getInsCon().getcnx();
    @Override
    public void Ajouter(Categorie u) throws SQLException {
       PreparedStatement ps;
        
        
        String query = "INSERT INTO `categorie`( `nom_categorie`) VALUES (?)";
        try {
            ps = c.prepareStatement(query);

             ps.setString(1, u.getNom_categorie());
          
            ps.execute();    
            System.out.println(u);
        } catch (Exception e) { 
            System.out.println(e);
        }  



    }

    @Override
    public void Supprimer(int t) throws SQLException {
     PreparedStatement ps;

        String query = "DELETE FROM `categorie` WHERE `id`=?  ";
  
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1, t);

            ps.execute();

            System.out.println("suppression de categorie");
        } catch (Exception e) {
            System.out.println(e);
        } 
    
    
    }

    @Override
    public void Modifier(Categorie u, int id) throws SQLException {
 
      PreparedStatement ps;
        String query = "UPDATE `categorie` SET `nom_categorie`=? WHERE `ID`=?";
        try {
            
            ps = c.prepareStatement(query);
         
            ps.setString(1, u.getNom_categorie());
          
       
            ps.setInt(2, id);
            ps.execute();
   

        } catch (Exception e) {
        }
    
    
    }

    @Override
    public ObservableList<Categorie> Affichertout() throws SQLException {
    ObservableList<Categorie> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `categorie`";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Categorie(rs.getInt("ID"),rs.getString("nom_categorie")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    
    }
    
     public ObservableList<Categorie> serach(String cas) throws SQLException {
        ObservableList<Categorie> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `categorie` where ( nom_categorie LIKE '%" + cas + "%' )";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
      list.add(new Categorie(rs.getInt("ID"),rs.getString("nom_categorie")));

        }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    
    
}

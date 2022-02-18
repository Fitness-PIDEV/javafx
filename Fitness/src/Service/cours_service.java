/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import IService.IService;
import Utils.MyConnexion;
import entites.Cours;
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
public class cours_service implements IService<Cours>{

    private Connection c = MyConnexion.getInsCon().getcnx();
    @Override
    public void Ajouter(Cours u) throws SQLException {
       PreparedStatement ps;
        
        
        String query = "INSERT INTO `cours`( `nom_cours`, `duree_cours`, `salle`, `nom_coach`) VALUES ('"+u.getNom_cours()+"','"+u.getDuree_cours()+"','"+u.getSalle()+"','"+u.getNom_coach()+"')";
        try {
            ps = c.prepareStatement(query);

            ps.execute();    
            System.out.println(u);
        } catch (Exception e) { 
            System.out.println(e);
        }  



    }

    @Override
    public void Supprimer(int t) throws SQLException {
     PreparedStatement ps;

        String query = "DELETE FROM `cours` WHERE `id`=?  ";
  
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1, t);

            ps.execute();

            System.out.println("suppression de produit");
        } catch (Exception e) {
            System.out.println(e);
        } 
    
    
    }

    @Override
    public void Modifier(Cours u, int id) throws SQLException {
 
      PreparedStatement ps;
        String query = "UPDATE `cours` SET `nom_cours`=? WHERE `ID`=?";
        try {
            
            ps = c.prepareStatement(query);
         
            ps.setString(1, u.getNom_cours());
          
       
            ps.setInt(2, id);
            ps.execute();
   

        } catch (Exception e) {
        }
    
    
    }

    @Override
    public ObservableList<Cours> Affichertout() throws SQLException {
    ObservableList<Cours> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `cours`";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Cours(rs.getInt("ID"),rs.getString("nom_cours"),rs.getString("duree_cours"),rs.getString("salle"),rs.getString("nom_coach")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    
    }
    
     public ObservableList<Cours> serach(String cas) throws SQLException {
        ObservableList<Cours> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `cours` where ( nom_cours LIKE '%" + cas + "%' )";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
      list.add(new Cours(rs.getInt("ID"),rs.getString("nom_cours"),rs.getString("duree_cours"),rs.getString("salle"),rs.getString("nom_coach")));

        }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    
    
}

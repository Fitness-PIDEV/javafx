/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import IService.IService;
import Utils.MyConnexion;
import entites.Reservation;
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
public class reservation_service implements IService<Reservation>{

    private Connection c = MyConnexion.getInsCon().getcnx();
    @Override
    public void Ajouter(Reservation u) throws SQLException {
       PreparedStatement ps;
        
        
        String query = "INSERT INTO `reservation`(`dispo`,`nom_cours`,`date`,`nbr_participant`) VALUES (?,?,?,?)";
        try {
            ps = c.prepareStatement(query);

             ps.setInt(1, u.getDispo());
             ps.setString(2, u.getNom_cours());
             ps.setString(3, u.getDate());
             ps.setInt(4, u.getNbr_participant());
          
            ps.execute();    
            System.out.println(u);
        } catch (Exception e) { 
            System.out.println(e);
        }  



    }

    @Override
    public void Supprimer(int t) throws SQLException {
     PreparedStatement ps;

        String query = "DELETE FROM `reservation` WHERE `id`=?  ";
  
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1, t);

            ps.execute();

            System.out.println("suppression de reservation");
        } catch (Exception e) {
            System.out.println(e);
        } 
    
    
    }

    @Override
    public void Modifier(Reservation r, int id) throws SQLException {
 
      PreparedStatement ps;
        String query = "UPDATE `categorie` SET `nom_cours`=? , `dispo`=?, `date`=?, `nbr_participant`=? WHERE `ID`=?";
        try {
            
            ps = c.prepareStatement(query);
            ps.setInt(2,r.getId());
            ps.setString(1, r.getNom_cours());
            ps.setString(3, r.getDate());
            ps.setInt(4, r.getDispo());
            ps.setInt(5, r.getNbr_participant());
            
            ps.execute();
   

        } catch (Exception e) {
        }
    
    
    }

    @Override
    public ObservableList<Reservation> Affichertout() throws SQLException {
    ObservableList<Reservation> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `reservation`";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Reservation(rs.getInt("ID"),rs.getInt("dispo"),rs.getString("nom_cours"),rs.getString("date"),rs.getInt("nbr_participant")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    
    }
    
     public ObservableList<Reservation> serach(String cas) throws SQLException {
        ObservableList<Reservation> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `reservation` where ( nom_cours LIKE '%" + cas + "%' )";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
      list.add(new Reservation(rs.getInt("ID"),rs.getInt("dispo"),rs.getString("nom_categorie"),rs.getString("date"),rs.getInt("nbr_participant")));

        }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

   
}

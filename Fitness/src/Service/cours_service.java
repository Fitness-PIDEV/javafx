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
import java.util.stream.Collectors;
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
        
        
        String query = "INSERT INTO `cours`( `nom_cours`, `duree_cours`, `salle`, `nom_coach`, `etat`) VALUES ('"+u.getNom_cours()+"','"+u.getDuree_cours()+"','"+u.getSalle()+"','"+u.getNom_coach()+"','"+u.getEtat()+"')";
        try {
            ps = c.prepareStatement(query);

            ps.execute();    
            System.out.println(u);
        } catch (Exception e) { 
            System.out.println(e);
        }  



    }
    @Override
 public void Supprimer(Cours co,int id) throws SQLException {
        PreparedStatement ps;

        String query = "UPDATE cours SET etat=0 WHERE id='"+id+"'";
  
        try {
            ps = c.prepareStatement(query);

//            ps.setInt(1,0);
            //ps.setInt(2,r.getId());
            ps.execute();

            System.out.println("suppression de produit");
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
   /*@Override
    public void Supprimer(int t) throws SQLException {
     PreparedStatement ps;

        String query = "UPDATE `cours` SET `etat`=?  ";
  
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1,0);

            ps.execute();

            System.out.println("suppression de produit");
        } catch (Exception e) {
            System.out.println(e);
        } 
    
    
    }*/

    @Override
     public void Modifier(Cours p, int id) {
       PreparedStatement ps;
        String query = "UPDATE `cours` SET `nom_cours`=?,`duree_cours`=?,`salle`=?,`nom_coach`=?,`etat`=? WHERE `ID`="+id;
         try {
            
            ps = c.prepareStatement(query);
         
            ps.setString(1, p.getNom_cours());
            ps.setString(2, p.getDuree_cours());
            ps.setString(3, p.getSalle());
             ps.setString(4, p.getNom_coach());
              ps.setInt(5, p.getEtat());
             
               
            
            
            ps.execute();
   

        } catch (Exception e) {
            System.out.println(e);
        }
    
    }

    @Override
    public ObservableList<Cours> Affichertout() throws SQLException {
    ObservableList<Cours> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `cours` WHERE etat=1";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Cours(rs.getInt("ID"),rs.getString("nom_cours"),rs.getString("duree_cours"),rs.getString("salle"),rs.getString("nom_coach"),rs.getInt("etat")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    
    }
    
      public List<Cours> Recherche(String nom) throws SQLException {

        return Affichertout().stream().filter(a -> a.getNom_cours().equals(nom)).collect(Collectors.toList());

    }

    
    
}

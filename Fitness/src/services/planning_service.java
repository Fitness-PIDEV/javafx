/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import IService.IService;
import entities.planning;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author USER
 */
public class planning_service implements IService<planning>{

    private Connection c = MyDB.getInstance().getCnx();
    @Override
    public void Ajouter(planning u) {
       PreparedStatement ps;
        
//        `INSERT INTO `planning`(`id_cours`, `duree`, `nom_cours`, `nom_coach`, `salle`, `date`, `state`) VALUES (1,1,'aze','azer','a','2022-03-23',1)
            //String query = "SELECT * FROM cours LEFT JOIN planning ON cours.nom_cours = planning.nom_cours";
            String query ="INSERT INTO `planning`(`id_cours`,`duree`, `nom_cours`, `nom_coach`, `salle`, `date`, `state`) "
              + "VALUES (?,?,?,?,?,?,'1')";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, u.getId_cours());    
            ps.setInt(2, u.getDuree());
            ps.setString(3, u.getNom_cours());
            ps.setString(4, u.getNom_coach());
            ps.setString(5, u.getSalle());
            ps.setDate(6, new java.sql.Date(u.getDate().getTime()));
            //ps.setInt(7, 1);
            ps.execute();    
            System.out.println(u);
        } catch (Exception e) { 
            System.out.println(e);
        }  



    }
//    public List<String> AfficherCoach(){
//    List<String> list1 = new ArrayList<>();
//        String requete = "SELECT `nom_coach` FROM `planning`";
//        try {
//            PreparedStatement ps = c.prepareStatement(requete);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                list1.add(rs.getString("nom_coach"));
//                
//            }    
//            
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return list1;
//    }

    @Override
    public void Modifier(planning p, int id) throws SQLException {
 
        try {
            
                    String query = "UPDATE `planning` SET `id_cours`='"+p.getId_cours()+"' ,`duree`='"+p.getDuree()+"' ,`nom_cours`='"+p.getNom_cours()+"' , `nom_coach`='"+p.getNom_coach()+"', `salle`='"+p.getSalle()+"', `date`='"+p.getDate()+"' WHERE `id`='"+id+"'";
             Statement ps = c.createStatement();
            ps.executeUpdate(query);
            System.out.println(p);
            System.out.println(id);
            System.out.println("modifcation de reservation");

        } catch (Exception e) {
        }
    
    
    }
   @Override 
    public List<planning> Affichertout(){
    List<planning> list = new ArrayList<>();
        String requete = "SELECT * FROM `planning` WHERE `state` = 1";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new planning(rs.getInt("id"),rs.getInt("id_cours"),rs.getInt("duree"),rs.getString("nom_cours"),rs.getString("nom_coach"),rs.getString("salle"),rs.getDate("date"),rs.getInt("state")));
            }    
         
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    
    }
 
    public List<planning> RechercheNomCours(String nom) {

        return Affichertout().stream().filter(a -> a.getNom_cours().equals(nom)).collect(Collectors.toList());

    }
    public List<planning> RechercheNomCoachs(String nom) {

        return Affichertout().stream().filter(a -> a.getNom_coach().equals(nom)).collect(Collectors.toList());

    }
    public List<planning> RechercheSalles(String nom) {

        return Affichertout().stream().filter(a -> a.getSalle().equals(nom)).collect(Collectors.toList());

    }
    
     public void TrierPlanningParId(){
    
     //Créé par Comparator
    Comparator<planning> comparator = Comparator.comparing(planning::getId);
    //Traitement du tri
    Affichertout().stream().sorted(comparator).forEach(a ->System.out.println(a.getId() + " " + a.getDate() + " " + a.getDuree()));
    }
     public void TrierPlanningParIdEtDuree(){
    
     //Créé par Comparator
    Comparator<planning> comparator =
    Comparator.comparing(planning::getId).thenComparing(planning::getDuree).reversed();
    //Traitement du tri
    Affichertout().stream().sorted(comparator).forEach(a ->System.out.println(a.getId() + " " + a.getDate() + " " + a.getDuree()));
    }

    @Override
    public void Supprimer(planning p,int id) throws SQLException {
        PreparedStatement ps;

        String query = "UPDATE planning SET `state`=? WHERE `id`='"+id+"'" ;
  
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1,0);
            //ps.setInt(2,p.getId_cours());
            ps.execute();

            System.out.println("suppression de planning");
        } catch (Exception e) {
            System.out.println(e);
        } 
    }

}

     
     
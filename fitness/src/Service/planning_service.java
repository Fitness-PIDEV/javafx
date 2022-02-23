/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import IService.IService;
import Utils.MyConnexion;
import entites.planning;
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
public class planning_service implements IService<planning>{

    private Connection c = MyConnexion.getInsCon().getcnx();
    @Override
    public void Ajouter(planning u) throws SQLException {
       PreparedStatement ps;
        
        
        String query = "INSERT INTO `planning`(`duree`,`nom_cours`,`nom_coach`,`salle`,`date`) VALUES (?,?,?,?,?)";
        try {
            ps = c.prepareStatement(query);

             ps.setInt(1, u.getDuree());
             ps.setString(2, u.getNom_cours());
             ps.setString(3, u.getNom_coach());
             ps.setString(4, u.getSalle());
             ps.setString(5, u.getDate());
          
            ps.execute();    
            System.out.println(u);
        } catch (Exception e) { 
            System.out.println(e);
        }  



    }

    @Override
    public void Supprimer(int t) throws SQLException {
     PreparedStatement ps;

        String query = "DELETE FROM `planning` WHERE `id_cours`=?  ";
  
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1, t);

            ps.execute();

            System.out.println("suppression de planning");
        } catch (Exception e) {
            System.out.println(e);
        } 
    
    
    }

    @Override
    public void Modifier(planning r, int id) throws SQLException {
 
      PreparedStatement ps;
        String query = "UPDATE `planning` SET `duree`=? ,`nom_cours`=? , `nom_coach`=?, `salle`=?, `date`=? WHERE `id_cours`='"+id+"'";
        try {
            
            
            ps = c.prepareStatement(query);
            
            ps.setInt(1, r.getDuree());
            //ps.setInt(2,r.getId_cours());
            ps.setString(2, r.getNom_cours());
            ps.setString(3, r.getNom_coach());
            ps.setString(4, r.getSalle());
            ps.setString(5, r.getDate());
            
            ps.executeUpdate();
   

        } catch (Exception e) {
        }
    
    
    }
   @Override 
    public List<planning> Affichertout(){
    List<planning> list = new ArrayList<>();
        String requete = "SELECT * FROM `planning`";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new planning(rs.getInt("id_cours"),rs.getInt("duree"),rs.getString("nom_cours"),rs.getString("nom_coach"),rs.getString("salle"),rs.getString("date")));
            }    
         
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    
    }
    public void AfficherPlanningParId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("taper id");
        int id = sc.nextInt();
        Affichertout().stream().filter(e-> e.getId_cours()==id).forEach(e->System.err.println(e));
       
    }
    public void AfficherPlanningParDuree() {
        Scanner sc = new Scanner(System.in);
        System.out.println("taper duree");
        int duree = sc.nextInt();
        Affichertout().stream().filter(e-> e.getDuree()==duree).forEach(e->System.err.println(e));
       
    }
    public void AfficherPlanningParNom_cours() {
        Scanner sc = new Scanner(System.in);
        System.out.println("taper nom cours");
        String nom_cours = sc.nextLine();
        Affichertout().stream().filter(e-> e.getNom_cours()==nom_cours).forEach(e->System.err.println(e));
       
    }
     public void AfficherPlanningParNom_coach() {
        Scanner sc = new Scanner(System.in);
        System.out.println("taper nom coach");
        String nom_coach = sc.nextLine();
        Affichertout().stream().filter(e-> e.getNom_coach()==nom_coach).forEach(e->System.err.println(e));
       
    }
     public void AfficherPlanningParSalle() {
        Scanner sc = new Scanner(System.in);
        System.out.println("taper salle");
        String salle = sc.nextLine();
        Affichertout().stream().filter(e-> e.getSalle()==salle).forEach(e->System.err.println(e));
       
    }
     public void AfficherPlanningParSDate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("taper date");
        String date = sc.nextLine();
        Affichertout().stream().filter(e-> e.getDate()==date).forEach(e->System.err.println(e));
       
    }
     public void TrierPlanningParId_cours(){
    
     //Créé par Comparator
    Comparator<planning> comparator = Comparator.comparing(planning::getId_cours);
    //Traitement du tri
    Affichertout().stream().sorted(comparator).forEach(a ->System.out.println(a.getId_cours() + " " + a.getDate() + " " + a.getDuree()));
    }
     public void TrierPlanningParId_coursEtDuree(){
    
     //Créé par Comparator
    Comparator<planning> comparator =
    Comparator.comparing(planning::getId_cours).thenComparing(planning::getDuree).reversed();
    //Traitement du tri
    Affichertout().stream().sorted(comparator).forEach(a ->System.out.println(a.getId_cours() + " " + a.getDate() + " " + a.getDuree()));
    }

}     
     
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Date;
import IService.IService;
import entities.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import utils.MyDB;


/**
 *
 * @author USER
 */
public class reservation_service implements IService<Reservation>{

    private Connection c =MyDB.getInstance().getCnx();
    @Override
    public void Ajouter(Reservation u) throws SQLException {
       PreparedStatement ps;
        
//        java.util.Date date=new java.util.Date();
//        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
        String query = "INSERT INTO `reservation`(`dispo`,`nom_cours`,`date`,`nbr_participant`,`state`) VALUES (?,?,?,?,?)";
        try {
            ps = c.prepareStatement(query);

             ps.setInt(1, u.getDispo());
             ps.setString(2, u.getNom_cours());
             ps.setDate(3, (java.sql.Date) u.getDate());
             ps.setInt(4, u.getNbr_participant());
             ps.setInt(5,1);
            ps.execute();    
            System.out.println(u);
        } catch (Exception e) { 
            System.out.println(e);
        }  

    }
    
//     @Override
//    public void Supprimer(int t) throws SQLException {
//    PreparedStatement ps;
//
//        String query = "DELETE FROM `reservation` WHERE `id`=?,`state`=0  ";
//  
//        try {
//            ps = c.prepareStatement(query);
//
//            ps.setInt(1, t);
//            ps.setInt(2,t);
//            ps.execute();
//
//            System.out.println("suppression de reservation ");
//        } catch (Exception e) {
//            System.out.println(e);
//        }    }
 
    @Override
    public void Modifier(Reservation r, int id) throws SQLException {
 
      PreparedStatement ps;
        String query = "UPDATE `reservation` SET `dispo`=? ,`nom_cours`=? , `date`=?, `nbr_participant`=? WHERE `id`='"+id+"'";
        try {
            
            
            ps = c.prepareStatement(query);
            
            ps.setInt(1, r.getDispo());
            ps.setString(2, r.getNom_cours());
            ps.setDate(3, (java.sql.Date) r.getDate());
            ps.setInt(4, r.getNbr_participant());
            
            
            ps.executeUpdate();
               System.out.println("suppression de reservation");


        } catch (Exception e) {
        }
    
    
    }
    
    
    @Override 
    public List<Reservation> Affichertout(){
    List<Reservation> list = new ArrayList<>();
        String requete = "SELECT * FROM `reservation` WHERE `state` = 1";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            list.add(new Reservation(rs.getInt("id"),rs.getInt("dispo"),rs.getString("nom_cours"),rs.getDate("date"),rs.getInt("nbr_participant"),rs.getInt("state")));            
            }   
        
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    
    }
    public List<Reservation> Recherche(String nom) {

        return Affichertout().stream().filter(a -> a.getNom_cours().equals(nom)).collect(Collectors.toList());

    }
    public void TrierReservationParId(){
    
     //Créé par Comparator
    Comparator<Reservation> comparator = Comparator.comparing(Reservation::getId);
    //Traitement du tri
    Affichertout().stream().sorted(comparator).forEach(a ->System.out.println(a.getId() + " " + a.getDispo() + " " + a.getNom_cours()));
    }
     public void TrierReservationParIdEtDispo(){
    
     //Créé par Comparator
    Comparator<Reservation> comparator =
    Comparator.comparing(Reservation::getId).thenComparing(Reservation::getDispo).reversed();
    //Traitement du tri
    Affichertout().stream().sorted(comparator).forEach(a ->System.out.println(a.getId() + " " + a.getDispo() + " " + a.getNom_cours()));
    }

    @Override
    public void Supprimer(Reservation r,int id) throws SQLException {
        PreparedStatement ps;

        String query = "UPDATE reservation SET `state`=? WHERE `id`='"+id+"'";
  
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1,0);
            ps.execute();

            System.out.println("suppression de reservation");
        } catch (Exception e) {
            System.out.println(e);
        } 
    }

   

   

   
}

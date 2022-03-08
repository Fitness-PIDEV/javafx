/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author Ahmed
 */
public class ServiceReclamation implements Iservice<Reclamation> {
private Connection cnx ;

public ServiceReclamation(){
      cnx =MyDB.getInstance().getCnx();
         
}
    @Override
    public void ajouter(Reclamation u) {
        PreparedStatement ps;
        String query = "INSERT INTO `reclamation`(`titre`, `message`,`iduser`) VALUES ('"+u.getTitre()+"','"+u.getMessage()+"','"+u.getIduser()+"')";
        try {
            ps = cnx.prepareStatement(query);
            ps.execute();    
           // System.out.println(u);
        } catch (Exception e) { 
            System.out.println(e);
        }  


    }

    @Override
    public List<Reclamation> afficher() {
          List<Reclamation> Reclamation = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `reclamation`";
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Reclamation r = new Reclamation();
            r.setId(rs.getInt(1));
            r.setTitre(rs.getString(2));
            r.setMessage(rs.getString(3));
            r.setIduser(rs.getInt(4));
            
            Reclamation.add(r);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  Reclamation;
    }

    @Override
    public void modifer(Reclamation u, int id) {
       PreparedStatement ps;
        String query = "UPDATE `reclamation` SET `titre`=?,`message`=? WHERE `ID`="+id;
        try {
            
            ps = cnx.prepareStatement(query);
         
            ps.setString(1, u.getTitre());
            ps.setString(2, u.getMessage());
            
            ps.execute();
   

        } catch (Exception e) {
            System.out.println(e);
        }
    
    }

    @Override
    public void supprimer(int t) {
        PreparedStatement ps;
        String query = "DELETE FROM `reclamation` WHERE `id`=? ";
 
        try {
            ps = cnx.prepareStatement(query);
            ps.setInt(1, t);
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
    
}

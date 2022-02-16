/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.users;
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
public class ServiceUsers implements Iservice<users>{
private Connection cnx ;

public ServiceUsers(){
      cnx =MyDB.getInstance().getCnx();
         
}
    @Override
    public void ajouter(users u) {
        try {
             String querry="INSERT INTO `users`( `nom`, `prenom`, `email`, `num`, `mdp`, `role`, `photo`) VALUES ('"+u.getNom()+"','"+u.getPrenom()+"','"+u.getEmail()+"','"+u.getNum()+"','"+u.getMdp()+"','"+u.getRole()+"','"+u.getPhoto()+"')";
            Statement stm =cnx.createStatement();
        
        stm.executeUpdate(querry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    }

    @Override
    public List<users> afficher() {
        List<users> users = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `users`";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            users u = new users();
            u.setId(rs.getInt(1));
            u.setNom(rs.getString(2));
            u.setPrenom(rs.getString(3));
            u.setEmail(rs.getString(4));
            u.setNum(rs.getInt(5));
            u.setMdp(rs.getString(6));
            u.setRole(rs.getString(7));
            u.setPhoto(rs.getString(8));
            
            
            users.add(u);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  users;
   
    }

    @Override
    public void modifer(users u ,int id) {
        PreparedStatement ps;
        String query = "UPDATE `users` SET `nom`=?,`prenom`=?,`email`=?,`num`=?,`mdp`=?,`role`=?,`photo`=? WHERE `ID`="+id;
        try {
            
            ps = cnx.prepareStatement(query);
         
            
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setString(3, u.getEmail());
            ps.setInt(4, u.getNum());
            ps.setString(5, u.getMdp());
            ps.setString(6, u.getRole());
            ps.setString(7, u.getPhoto());
       
            
            ps.execute();
   

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int t) {
        PreparedStatement ps;

        String query = "DELETE FROM `users` WHERE `id`=?  ";
  
        try {
            ps = cnx.prepareStatement(query);

            ps.setInt(1, t);

            ps.execute();

            System.out.println("suppression de user");
        } catch (Exception e) {
            System.out.println(e);
        } 
        
    }
    
}

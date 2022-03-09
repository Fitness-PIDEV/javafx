/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Admin;
import entities.Coach;
import entities.Livreur;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import utils.MyDB;

/**
 *
 * @author Ahmed
 */
public class ServiceUsers implements Iservice<User>{
private Connection cnx ;

public ServiceUsers(){
      cnx =MyDB.getInstance().getCnx();
         
}
    @Override
    public void ajouter(User u) {
        
        try {
            String querry;
            if (u instanceof Admin){
                 querry="INSERT INTO `user`(`num`, `nom`, `prenom`, "
                     + "`email`, `mdp`, `image`, `isDeleted`, `role`) VALUES"
                     + " ('"+u.getNum()+"','"+u.getNom()+"',"
                     + "'"+u.getPrenom()+"','"+u.getEmail()+"','"+u.getMdp()+"',"
                     + "'"+u.getPhoto()+"','"+0 +"','"+"ADMIN"+"')";
                
            }
            else if(u instanceof Coach){
                 querry="INSERT INTO `user`(`num`, `nom`, `prenom`, "
                     + "`email`, `mdp`, `image`, `isDeleted`, `role`,`certificatURL`) VALUES"
                     + " ('"+u.getNum()+"','"+u.getNom()+"',"
                     + "'"+u.getPrenom()+"','"+u.getEmail()+"','"+u.getMdp()+"',"
                     + "'"+u.getPhoto()+"','"+0 +"','"+"COACH"+"','"+((Coach) u).getCertificateURL()+"')";
            }
            else if(u instanceof Livreur){
                int x=((Livreur) u).isDispo()?1:0;
                 querry="INSERT INTO `user`(`num`, `nom`, `prenom`, "
                     + "`email`, `mdp`, `image`, `isDeleted`, `role`,`dispo`) VALUES"
                     + " ('"+u.getNum()+"','"+u.getNom()+"',"
                     + "'"+u.getPrenom()+"','"+u.getEmail()+"','"+u.getMdp()+"',"
                     + "'"+u.getPhoto()+"','"+0 +"','"+"LIVREUR"+"','"+x+"')";
            }
            else{
                 querry="INSERT INTO `user`(`num`, `nom`, `prenom`, "
                     + "`email`, `mdp`, `image`, `isDeleted`, `role`) VALUES"
                     + " ('"+u.getNum()+"','"+u.getNom()+"',"
                     + "'"+u.getPrenom()+"','"+u.getEmail()+"','"+u.getMdp()+"',"
                     + "'"+u.getPhoto()+"','"+0 +"','"+"USER"+"')";
            }
            
            Statement stm =cnx.createStatement();
            stm.executeUpdate(querry);
        
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    }

    @Override
    public List<User> afficher() {
        List<User> users = new ArrayList();
        
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `user`";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            String role;
            Admin a=new Admin();
            Coach c=new Coach();
            Livreur l=new Livreur();
            User u = new User();
            role=rs.getString(9);
                if(role.equals("ADMIN")){
                    
                    a.setId(rs.getInt(1));
                    a.setNum(rs.getInt(2));
                    a.setNom(rs.getString(3));
                    a.setPrenom(rs.getString(4));
                    a.setEmail(rs.getString(5));

                    a.setMdp(rs.getString(6));

                    a.setPhoto(rs.getString(7));
                    a.setIsDeleted(rs.getBoolean(8));
                    if(a.isIsDeleted()==false){
                        users.add(a);
                    }
                }
                else if(role.equals("COACH"))
                {
                    
                    c.setId(rs.getInt(1));
                    c.setNum(rs.getInt(2));
                    c.setNom(rs.getString(3));
                    c.setPrenom(rs.getString(4));
                    c.setEmail(rs.getString(5));

                    c.setMdp(rs.getString(6));

                    c.setPhoto(rs.getString(7));
                    c.setIsDeleted(rs.getBoolean(8));
                    c.setCertificateURL(rs.getString(11));
                    if(c.isIsDeleted()==false){
                        users.add(c);
                    }
                }
                else if(role.equals("LIVREUR")){
                    
                    l.setId(rs.getInt(1));
                    l.setNum(rs.getInt(2));
                    l.setNom(rs.getString(3));
                    l.setPrenom(rs.getString(4));
                    l.setEmail(rs.getString(5));

                    l.setMdp(rs.getString(6));

                    l.setPhoto(rs.getString(7));
                    l.setIsDeleted(rs.getBoolean(8));
                    l.setDispo(rs.getBoolean(10));
                    if(l.isIsDeleted()==false){
                        users.add(l);
                    }
                }
                else{
                    u.setId(rs.getInt(1));
                    u.setNum(rs.getInt(2));
                    u.setNom(rs.getString(3));
                    u.setPrenom(rs.getString(4));
                    u.setEmail(rs.getString(5));

                    u.setMdp(rs.getString(6));

                    u.setPhoto(rs.getString(7));
                    u.setIsDeleted(rs.getBoolean(8));
                    if(u.isIsDeleted()==false){
                        users.add(u);
                    }
                }
            
            
            
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  users;
   
    }
    

    @Override
    public void modifer(User u ,int id) {
        PreparedStatement ps;
        String query;
        if(u instanceof Admin){
             query = "UPDATE `user` SET "
                    + "`num`='"+u.getNum()+"',"
                    + "`nom`='"+u.getNom()+"',"
                    + "`prenom`='"+u.getPrenom()+"',"
                    + "`email`='"+u.getEmail()+"',"
                    + "`mdp`='"+u.getMdp()+"',"
                    + "`image`='"+u.getPhoto()+"',"
                    + "`isDeleted`='"+0+"',"
                    + "`role`='"+"ADMIN"+"' WHERE id="+id;
        }
        else if (u instanceof Coach){
             query = "UPDATE `user` SET "
                    + "`num`='"+u.getNum()+"',"
                    + "`nom`='"+u.getNom()+"',"
                    + "`prenom`='"+u.getPrenom()+"',"
                    + "`email`='"+u.getEmail()+"',"
                    + "`mdp`='"+u.getMdp()+"',"
                    + "`image`='"+u.getPhoto()+"',"
                    + "`isDeleted`='"+0+"',"
                    + "`role`='"+"COACH"+"',"
                     + "`certificatURL`='"+((Coach) u).getCertificateURL()+"' WHERE id="+id;
        }
        else if(u instanceof Livreur){
            int x=((Livreur) u).isDispo()?1:0;
             query = "UPDATE `user` SET "
                    + "`num`='"+u.getNum()+"',"
                    + "`nom`='"+u.getNom()+"',"
                    + "`prenom`='"+u.getPrenom()+"',"
                    + "`email`='"+u.getEmail()+"',"
                    + "`mdp`='"+u.getMdp()+"',"
                    + "`image`='"+u.getPhoto()+"',"
                    + "`isDeleted`='"+0+"',"
                    + "`role`='"+"LIVREUR"+"',"
                     + "`dispo`='"+x+"' WHERE id="+id;
        }
        else{
            query = "UPDATE `user` SET "
                    + "`num`='"+u.getNum()+"',"
                    + "`nom`='"+u.getNom()+"',"
                    + "`prenom`='"+u.getPrenom()+"',"
                    + "`email`='"+u.getEmail()+"',"
                    + "`mdp`='"+u.getMdp()+"',"
                    + "`image`='"+u.getPhoto()+"',"
                    + "`isDeleted`='"+0+"',"
                    + "`role`='"+"USER"+"' WHERE id="+id;
        }
        try {
            
            Statement stm =cnx.createStatement();
            stm.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
    }

    @Override
    public void supprimer(int id) {
        PreparedStatement ps;
        String query;
        
        if(findById(id) instanceof Admin){
            Admin u=(Admin) findById(id);
             query = "UPDATE `user` SET "
                    + "`num`='"+u.getNum()+"',"
                    + "`nom`='"+u.getNom()+"',"
                    + "`prenom`='"+u.getPrenom()+"',"
                    + "`email`='"+u.getEmail()+"',"
                    + "`mdp`='"+u.getMdp()+"',"
                    + "`image`='"+u.getPhoto()+"',"
                    + "`isDeleted`='"+1+"',"
                    + "`role`='"+"ADMIN"+"' WHERE id="+id;
        }
        else if (findById(id) instanceof Coach){
            Coach u =(Coach) findById(id) ;
             query = "UPDATE `user` SET "
                    + "`num`='"+u.getNum()+"',"
                    + "`nom`='"+u.getNom()+"',"
                    + "`prenom`='"+u.getPrenom()+"',"
                    + "`email`='"+u.getEmail()+"',"
                    + "`mdp`='"+u.getMdp()+"',"
                    + "`image`='"+u.getPhoto()+"',"
                    + "`isDeleted`='"+1+"',"
                    + "`role`='"+"COACH"+"',"
                     + "`certificatURL`='"+u.getCertificateURL()+"' WHERE id="+id;
        }
        else if(findById(id)  instanceof Livreur){
            
            Livreur u=(Livreur) findById(id) ;
            int x=u.isDispo()?1:0;
             query = "UPDATE `user` SET "
                    + "`num`='"+u.getNum()+"',"
                    + "`nom`='"+u.getNom()+"',"
                    + "`prenom`='"+u.getPrenom()+"',"
                    + "`email`='"+u.getEmail()+"',"
                    + "`mdp`='"+u.getMdp()+"',"
                    + "`image`='"+u.getPhoto()+"',"
                    + "`isDeleted`='"+1+"',"
                    + "`role`='"+"LIVREUR"+"',"
                     + "`dispo`='"+x+"' WHERE id="+id;
        }
        else{
            User u=findById(id) ;
            query = "UPDATE `user` SET "
                    + "`num`='"+u.getNum()+"',"
                    + "`nom`='"+u.getNom()+"',"
                    + "`prenom`='"+u.getPrenom()+"',"
                    + "`email`='"+u.getEmail()+"',"
                    + "`mdp`='"+u.getMdp()+"',"
                    + "`image`='"+u.getPhoto()+"',"
                    + "`isDeleted`='"+1+"',"
                    + "`role`='"+"USER"+"' WHERE id="+id;
        }
        try {
            
            Statement stm =cnx.createStatement();
            stm.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
        
    }
    public User findById(int id){
        User u=new User();
        Admin a=new Admin();
        Coach c=new Coach();
        Livreur l=new Livreur();
        String role="";
        try {
            Statement stm =cnx.createStatement();
            String querry ="SELECT * FROM `user` where id="+id;
            
            ResultSet rs= stm.executeQuery(querry);

            rs.next();
                role=rs.getString(9);
                if(role.equals("ADMIN")){
                    
                    a.setId(rs.getInt(1));
                    a.setNum(rs.getInt(2));
                    a.setNom(rs.getString(3));
                    a.setPrenom(rs.getString(4));
                    a.setEmail(rs.getString(5));

                    a.setMdp(rs.getString(6));

                    a.setPhoto(rs.getString(7));
                    a.setIsDeleted(rs.getBoolean(8));
                    return a;
                }
                else if(role.equals("COACH"))
                {
                    
                    c.setId(rs.getInt(1));
                    c.setNum(rs.getInt(2));
                    c.setNom(rs.getString(3));
                    c.setPrenom(rs.getString(4));
                    c.setEmail(rs.getString(5));

                    c.setMdp(rs.getString(6));

                    c.setPhoto(rs.getString(7));
                    c.setIsDeleted(rs.getBoolean(8));
                    c.setCertificateURL(rs.getString(11));
                    return c;
                }
                else if(role.equals("LIVREUR")){
                   
                    l.setId(rs.getInt(1));
                    l.setNum(rs.getInt(2));
                    l.setNom(rs.getString(3));
                    l.setPrenom(rs.getString(4));
                    l.setEmail(rs.getString(5));

                    l.setMdp(rs.getString(6));

                    l.setPhoto(rs.getString(7));
                    l.setIsDeleted(rs.getBoolean(8));
                    l.setDispo(rs.getBoolean(10));
                    return l;
                }
                u.setId(rs.getInt(1));
                    u.setNum(rs.getInt(2));
                    u.setNom(rs.getString(3));
                    u.setPrenom(rs.getString(4));
                    u.setEmail(rs.getString(5));

                    u.setMdp(rs.getString(6));

                    u.setPhoto(rs.getString(7));
                    u.setIsDeleted(rs.getBoolean(8));
                    return u;

        } catch (SQLException ex) {
                System.out.println(ex.getMessage()); 

        }
        
        return (User)u;
    }
    public List<User> findByName(String name){
        List<User> users=afficher();
        List<User> resultat=users.stream().filter(u->u.getNom().equals(name)).collect(Collectors.toList());
        
        return resultat;
    }
    public List<User> sortByNum(){
        List<User> users=afficher();
        List<User> resultat=users.stream().sorted(Comparator.comparing(User::getNum)).collect(Collectors.toList());
        return resultat;
    }
    public boolean checklogin(String email,String password){
        try {
            String query="select * from user where email='"+email+"' AND mdp='"+password+"'AND isDeleted=0";
            Statement stm =cnx.createStatement();
            ResultSet rs= stm.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public String findRoleByEmail(String email){
        try {
            String role="";
            Statement stm =cnx.createStatement();
            String querry ="SELECT * FROM `user` where email='"+email+"'";
            ResultSet rs= stm.executeQuery(querry);
            if(rs.next()){
                return rs.getString(9);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public int findIdByEmail(String email){
        try {
            String role="";
            Statement stm =cnx.createStatement();
            String querry ="SELECT * FROM `user` where email='"+email+"'";
            ResultSet rs= stm.executeQuery(querry);
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
  
}

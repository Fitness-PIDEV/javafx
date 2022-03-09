/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.achat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author khalil
 */
public class ServiceAchat implements Iservice<achat> {

    private Connection cnx;

    public ServiceAchat() {
        cnx = MyDB.getInstance().getCnx();

    }

    @Override
    public void ajouter(achat u) {
       
        try {
            String querry = "INSERT INTO `achat` (`ref_produit`, `prix`) VALUES (?, ?) ";
            PreparedStatement preparedStmt = cnx.prepareStatement(querry);

            preparedStmt.setInt(1, u.getRef_produit());
            preparedStmt.setFloat(2, u.getPrix());

            preparedStmt.execute();
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<achat> afficher() {
        List<achat> achat = new ArrayList();

        try {
            Statement stm = cnx.createStatement();
            String querry = "SELECT * FROM `achat`";

            ResultSet rs = stm.executeQuery(querry);

            while (rs.next()) {
                achat u = new achat();
                u.setId_produit(rs.getInt(1));
                u.setRef_produit(rs.getInt(2));
                u.setPrix(rs.getFloat(3));

                achat.add(u);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return achat;

    }

    @Override
    public void modifer(achat u, int id_produit) {
        PreparedStatement ps;
        String query = "UPDATE `achat` SET `ref_produit`=?,`prix`=? WHERE `id_produit`="+id_produit;
        try {

            ps = cnx.prepareStatement(query);

            ps.setInt(1, u.getRef_produit());
            ps.setFloat(2, u.getPrix());

            ps.execute();

        } catch (Exception e) {
        }
    }

    @Override
    public void supprimer(int id_produit) {
        PreparedStatement ps;

        String query = "DELETE FROM `achat` WHERE `id_produit`=?  ";

        try {
            ps = cnx.prepareStatement(query);

            ps.setInt(1, id_produit);

            ps.execute();

            System.out.println("suppression de achat");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

   public int nombreachat() {
int i=0;
        try {
            Statement st = cnx.createStatement();
            
            String query = "select * from `achat` ";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
               i=i+1;
            }

        } catch (SQLException ex) {
            System.out.println("erreur");
            System.out.println(ex);
        }
            return i ;
    }
    
    
    
    

  
    
    
}

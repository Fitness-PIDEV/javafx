/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.livraison;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;
import java.util.*;
import java.util.stream.*;

/**
 *
 * @author khalil
 */
public class ServiceLivraison implements Iservice<livraison> {

    private Connection cnx;

    public ServiceLivraison() {
        cnx = MyDB.getInstance().getCnx();

    }

    @Override
    public void ajouter(livraison u) {
        try {
            String querry = "INSERT INTO `livraison`( `id_livraison`, `id_user`, `ref_commande`, `donnees_user`, `frais_livraison`, `localisation`) VALUES ('" + u.getId_livraison() + "','" + u.getId_user() + "','" + u.getRef_commande() + "','" + u.getDonnees_user() + "','" + u.getFrais_livraison() + "','" + u.getLocalisation() + "')";
            Statement stm = cnx.createStatement();

            stm.executeUpdate(querry);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<livraison> afficher() {
        List<livraison> livraison = new ArrayList();

        try {
            Statement stm = cnx.createStatement();
            String querry = "SELECT * FROM `livraison`";

            ResultSet rs = stm.executeQuery(querry);

            while (rs.next()) {
                livraison u = new livraison();
                u.setId_livraison(rs.getInt(1));
                u.setId_user(rs.getInt(2));
                u.setRef_commande(rs.getInt(3));
                u.setDonnees_user(rs.getString(4));
                u.setFrais_livraison(rs.getFloat(5));
                u.setLocalisation(rs.getString(6));

                livraison.add(u);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return livraison;

    }

    @Override
    public void modifer(livraison u, int id) {
        PreparedStatement ps;
        String query = "UPDATE `livraison` SET `id_user`=?,`ref_commande`=?,`donnees_user`=?,`frais_livraison`=?,`localisation`=? WHERE `id_livraison`="+id;
        try {

            ps = cnx.prepareStatement(query);

            ps.setInt(1, u.getId_user());
            ps.setInt(2, u.getRef_commande());
            ps.setString(3, u.getDonnees_user());
            ps.setFloat(4, u.getFrais_livraison());
            ps.setString(5, u.getLocalisation());

            ps.execute();

        } catch (Exception e) {
        }
    }

    @Override
    public void supprimer(int t) {
        PreparedStatement ps;

        String query = "DELETE FROM `livraison` WHERE `id_livraison`=?  ";

        try {
            ps = cnx.prepareStatement(query);

            ps.setInt(1, t);

            ps.execute();

            System.out.println("suppression de livraison");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public ObservableList<livraison> recherche_par_prix(float p) {
        List<livraison> list = afficher();
        List<livraison> list2 = list.stream().filter(c -> c.getFrais_livraison() > p).collect(Collectors.toList());
        return FXCollections.observableArrayList(list2);
    }

    public List<livraison> tri(float p) {
        Comparator<livraison> comparator = (livraison o1, livraison o2) -> (int) (o1.getFrais_livraison()-o1.getFrais_livraison());
                
        List<livraison> list = afficher();
        return list.stream().sorted(comparator).collect(Collectors.toList());

    }

    public int nombrelivraison() {
int i=0;
        try {
            Statement st = cnx.createStatement();
            
            String query = "select * from `livraison` ";
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

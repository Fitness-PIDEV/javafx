/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.PreparedStatement;

/**
 *
 * @author khalil
 */
public class achat {

   
    private int id_produit,ref_produit;
    private float prix;

    public achat() {
    }

    public achat(int id_produit , int ref_produit , float prix) {
        this.id_produit = id_produit;
        this.ref_produit = ref_produit;
        this.prix = prix;
       
    }

    public achat(int ref_produit, float prix) {
        this.ref_produit = ref_produit;
        this.prix = prix;
    }
    
   

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getRef_produit() {
        return ref_produit;
    }

    public void setRef_produit(int ref_produit) {
        this.ref_produit = ref_produit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "reference=" + ref_produit + ", prix=" + prix ;
    }

    public PreparedStatement prepareStatement(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}

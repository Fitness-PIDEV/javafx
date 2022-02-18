/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author USER
 */
public class Produit {
    private int ID;
    private String nom_produit;
    private int prix_produit;
    private int quantite_produit;
    private int ID_categorie;
    private String image_produit;

    public Produit(int ID, String nom_produit,int prix_produit,String image_produit, int quantite_produit, int ID_categorie) {
        this.ID = ID;
        this.nom_produit = nom_produit;
        this.prix_produit = prix_produit;
        this.quantite_produit = quantite_produit;
        this.ID_categorie = ID_categorie;
        this.image_produit= image_produit;
    }

    public Produit(String nom_produit, int prix_produit,String image_produit, int quantite_produit, int ID_categorie) {
        this.nom_produit = nom_produit;
        this.prix_produit = prix_produit;
        this.quantite_produit = quantite_produit;
        this.ID_categorie = ID_categorie;
        this.image_produit= image_produit;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public int getPrix_produit() {
        return prix_produit;
    }

    public void setPrix_produit(int prix_produit) {
        this.prix_produit = prix_produit;
    }

    public int getQuantite_produit() {
        return quantite_produit;
    }

    public void setQuantite_produit(int quantite_produit) {
        this.quantite_produit = quantite_produit;
    }

    public int getID_categorie() {
        return ID_categorie;
    }

    public void setID_categorie(int ID_categorie) {
        this.ID_categorie = ID_categorie;
    }

    public String getImage_produit() {
        return image_produit;
    }

    public void setImage_produit(String image_produit) {
        this.image_produit = image_produit;
    }

    @Override
    public String toString() {
        return "Produit{" + "ID=" + ID + ", nom_produit=" + nom_produit + ", prix_produit=" + prix_produit + ", quantite_produit=" + quantite_produit + ", ID_categorie=" + ID_categorie + ", image_produit=" + image_produit + '}';
    }

   

    }
    
    
    
    
    


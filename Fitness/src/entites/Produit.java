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
    private String description;
     private int etat;

private static int id_courant;
    public Produit(int ID, String nom_produit,int prix_produit,String image_produit, int quantite_produit, int ID_categorie, int etat, String description) {
        this.ID = ID;
        this.nom_produit = nom_produit;
        this.prix_produit = prix_produit;
        this.quantite_produit = quantite_produit;
        this.ID_categorie = ID_categorie;
        this.image_produit= image_produit;
        this.description= description;
        this.etat= etat;
    }
     public Produit(int ID) {
        this.ID = ID;
       
    }
    

    public Produit(String nom_produit, int prix_produit,String image_produit, int quantite_produit, int ID_categorie, int etat,String description) {
        this.nom_produit = nom_produit;
        this.prix_produit = prix_produit;
        this.quantite_produit = quantite_produit;
        this.ID_categorie = ID_categorie;
        this.description = description;
        this.image_produit= image_produit;
         this.etat= etat;
    }

    public Produit() {
       
    }

    public static int getId_courant() {
        return id_courant;
    }

    public static void setId_courant(int id_courant) {
        Produit.id_courant = id_courant;
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

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Produit{" + "ID=" + ID + ", nom_produit=" + nom_produit + ", prix_produit=" + prix_produit + ", quantite_produit=" + quantite_produit + ", ID_categorie=" + ID_categorie + ", image_produit=" + image_produit + ", description=" + description + ", etat=" + etat + '}';
    }

   
    

    

   

    }
    
    
    
    
    


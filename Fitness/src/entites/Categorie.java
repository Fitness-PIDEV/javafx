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
public class Categorie {
    private int ID;
    private String nom_categorie;
    private int etat;

    public Categorie(String nom_categorie, int etat) {
        this.nom_categorie = nom_categorie;
          this.etat = etat;
    }

    public Categorie(int ID, String nom_categorie, int etat) {
        this.ID = ID;
        this.nom_categorie = nom_categorie;
                  this.etat = etat;

    }

    public Categorie() {
       
    }

  

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Categorie{" + "ID=" + ID + ", nom_categorie=" + nom_categorie + ", etat=" + etat + '}';
    }

   
    

       
      
    
}

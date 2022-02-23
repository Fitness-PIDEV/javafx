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
public class Cours {
    int ID;
    String nom_cours;
    String duree_cours;
    String salle;
    String nom_coach;

    public Cours(int ID, String nom_cours, String duree_cours, String salle, String nom_coach) {
        this.ID = ID;
        this.nom_cours = nom_cours;
        this.duree_cours = duree_cours;
        this.salle = salle;
        this.nom_coach = nom_coach;
    }

    public Cours(String nom_cours, String duree_cours, String salle, String nom_coach) {
        this.nom_cours = nom_cours;
        this.duree_cours = duree_cours;
        this.salle = salle;
        this.nom_coach = nom_coach;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom_cours() {
        return nom_cours;
    }

    public void setNom_cours(String nom_cours) {
        this.nom_cours = nom_cours;
    }

    public String getDuree_cours() {
        return duree_cours;
    }

    public void setDuree_cours(String duree_cours) {
        this.duree_cours = duree_cours;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getNom_coach() {
        return nom_coach;
    }

    public void setNom_coach(String nom_coach) {
        this.nom_coach = nom_coach;
    }

    @Override
    public String toString() {
        return "Cours{" + "ID_cours=" + ID + ", nom_cours=" + nom_cours + ", duree_cours=" + duree_cours + ", salle=" + salle + ", nom_coach=" + nom_coach + '}';
    }

    
    
}
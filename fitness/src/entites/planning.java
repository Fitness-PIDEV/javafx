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
public class planning {
 private int id_cours;
 private int duree;
 private String nom_cours;
 private String nom_coach;
 private String salle;
 private String date;

    public planning(int id_cours, int duree, String nom_cours, String nom_coach, String salle, String date) {
        this.id_cours = id_cours;
        this.duree = duree;
        this.nom_cours = nom_cours;
        this.nom_coach = nom_coach;
        this.salle = salle;
        this.date = date;
    }

    public planning(int duree, String nom_cours, String nom_coach, String salle, String date) {
        this.duree = duree;
        this.nom_cours = nom_cours;
        this.nom_coach = nom_coach;
        this.salle = salle;
        this.date = date;
    }

    public int getId_cours() {
        return id_cours;
    }

    public int getDuree() {
        return duree;
    }

    public String getNom_cours() {
        return nom_cours;
    }

    public String getNom_coach() {
        return nom_coach;
    }

    public String getSalle() {
        return salle;
    }

    public String getDate() {
        return date;
    }

    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setNom_cours(String nom_cours) {
        this.nom_cours = nom_cours;
    }

    public void setNom_coach(String nom_coach) {
        this.nom_coach = nom_coach;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "planning{" + "id_cours=" + id_cours + ", duree=" + duree + ", nom_cours=" + nom_cours + ", nom_coach=" + nom_coach + ", salle=" + salle + ", date=" + date + '}';
    }

   
}




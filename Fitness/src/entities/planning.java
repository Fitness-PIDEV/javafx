/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author USER
 */
public class planning {
 private int id;
 private int id_cours;
 private int duree;
 private String nom_cours;
 private String nom_coach;
 private String salle;
 private Date date;
 private int state;
    public planning (int id){
        this.id = id ;
    }
    public planning (String nom_coach){
    this.nom_coach = nom_coach;
    }
    public planning(int id,int id_cours, int duree, String nom_cours, String nom_coach, String salle, Date date,int state) {
        this.id=id;
        this.id_cours = id_cours;
        this.duree = duree;
        this.nom_cours = nom_cours;
        this.nom_coach = nom_coach;
        this.salle = salle;
        this.date = date;
        this.state = state;
    }

    public planning(int id_cours,int duree, String nom_cours, String nom_coach, String salle, Date date,int state) {
        this.id_cours = id_cours;
        this.duree = duree;
        this.nom_cours = nom_cours;
        this.nom_coach = nom_coach;
        this.salle = salle;
        this.date = date;
        this.state = state;

    }
    public planning(int id_cours,int duree, String nom_cours, String nom_coach, String salle, Date date) {
        this.id_cours = id_cours;
        this.duree = duree;
        this.nom_cours = nom_cours;
        this.nom_coach = nom_coach;
        this.salle = salle;
        this.date = date;

    }
    public planning() {
    }

    public int getId() {
        return id;
    }
            
    public int getState() {
        return state;
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

    public Date getDate() {
        return date;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setState(int state) {
        this.state = state;
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

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "duree=" + duree + ", nom_cours=" + nom_cours + ", nom_coach=" + nom_coach + ", salle=" + salle + ", date=" + date + ", state=" + state ;
    }  
}




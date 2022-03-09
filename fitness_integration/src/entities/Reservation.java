/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.time.LocalDate;
import java.util.Date;
/**
 *
 * @author USER
 */
public class Reservation {

    
 private int id;
 private int dispo;
 private String nom_cours;
 private Date date;
 private int nbr_participant;
 private int state;
    

    public Reservation(int id, int dispo, String nom_cours, Date date, int nbr_participant, int state) {
        this.id = id;
        this.dispo = dispo;
        this.nom_cours = nom_cours;
        this.date = date;
        this.nbr_participant = nbr_participant;
        this.state = state;
    }

    public Reservation(int dispo, String nom_cours, Date date, int nbr_participant, int state) {
        this.dispo = dispo;
        this.nom_cours = nom_cours;
        this.date = date;
        this.nbr_participant = nbr_participant;
        this.state = state;
    }
    public Reservation(int dispo, String nom_cours, Date date, int nbr_participant) {
        this.dispo = dispo;
        this.nom_cours = nom_cours;
        this.date = date;
        this.nbr_participant = nbr_participant;
    }
    public Reservation(){
   }

    public Reservation(int s1, int n0, LocalDate d1, int nbr1, int s10) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public int getDispo() {
        return dispo;
    }

    public String getNom_cours() {
        return nom_cours;
    }

    public Date getDate() {
        return date;
    }

    public int getNbr_participant() {
        return nbr_participant;
    }
    public int getState() {
        return state;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setDispo(int dispo) {
        this.dispo = dispo;
    }

    public void setNom_cours(String nom_cours) {
        this.nom_cours = nom_cours;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNbr_participant(int nbr_participant) {
        this.nbr_participant = nbr_participant;
    }
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return " dispo=" + dispo + ", nom_cours=" + nom_cours + ", date=" + date + ", nbr_participant=" + nbr_participant ;
    }
    

}




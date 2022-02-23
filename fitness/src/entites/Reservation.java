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
public class Reservation {

    public static void add(Reservation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 private int id;
 private int dispo;
 private String nom_cours;
 private String date;
 private int nbr_participant;

    public Reservation(int id, int dispo, String nom_cours, String date, int nbr_participant) {
        this.id = id;
        this.dispo = dispo;
        this.nom_cours = nom_cours;
        this.date = date;
        this.nbr_participant = nbr_participant;
    }

    public Reservation(int dispo, String nom_cours, String date, int nbr_participant) {
        this.dispo = dispo;
        this.nom_cours = nom_cours;
        this.date = date;
        this.nbr_participant = nbr_participant;
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

    public String getDate() {
        return date;
    }

    public int getNbr_participant() {
        return nbr_participant;
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

    public void setDate(String date) {
        this.date = date;
    }

    public void setNbr_participant(int nbr_participant) {
        this.nbr_participant = nbr_participant;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", dispo=" + dispo + ", nom_cours=" + nom_cours + ", date=" + date + ", nbr_participant=" + nbr_participant + '}';
    }

}




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

public class Livreur extends User{
    private boolean dispo;
    public Livreur() {
    }

    public Livreur(boolean dispo) {
        this.dispo = dispo;
    }

    

    public Livreur(boolean dispo, int num, String nom, String prenom, String email, String mdp, String photo, boolean isDeleted) {
        super(num, nom, prenom, email, mdp, photo, isDeleted);
        this.dispo = dispo;
    }

    public Livreur(boolean dispo, int id, int num, String nom, String prenom, String email, String mdp, String photo, boolean isDeleted) {
        super(id, num, nom, prenom, email, mdp, photo, isDeleted);
        this.dispo = dispo;
    }
    

    public boolean isDispo() {
        return dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    @Override
    public String toString() {
        return super.toString()+ ", Role=LIVREUR, dispo=" + dispo +", certificatURL=null"+"\n";
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author khalil
 */
public class livraison {

    private int id_livraison,ref_commande,id_user;
    private float frais_livraison;
    private String donnees_user,localisation;

    public livraison() {
    }

    public livraison(int id_livraison , int ref_commande , int id_user , float frais_livraison , String donnees_user , String localisation) {
        this.id_livraison = id_livraison;
        this.ref_commande = ref_commande;
        this.frais_livraison = frais_livraison;
        this.id_user = id_user;
        this.donnees_user = donnees_user;
        this.localisation = localisation;
       
    }
    
    public livraison(int i, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_livraison() {
        return id_livraison;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public int getRef_commande() {
        return ref_commande;
    }

    public void setRef_commande(int ref_commande) {
        this.ref_commande = ref_commande;
    }
     
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public float getFrais_livraison() {
        return frais_livraison;
    }

    public void setFrais_livraison(float frais_livraison) {
        this.frais_livraison = frais_livraison;
    }
    
     public String getDonnees_user() {
        return donnees_user;
    }

    public void setDonnees_user(String donnees_user) {
        this.donnees_user = donnees_user;
    }
    
     public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    @Override
    public String toString() {
        return "livraison{" + "id_livraison=" + id_livraison + ", ref_commande=" + ref_commande + ", id_user=" + id_user + ", frais_livraison=" + frais_livraison + ", donnees_user=" + donnees_user + ", localisation=" + localisation + '}';
    }

  
    
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_livraison_achat;

import entities.livraison;
import services.ServiceLivraison;

/**
 *
 * @author khalil
 */
public class Fitness_livraison {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServiceLivraison sp = new ServiceLivraison();
    
   //sp.supprimer(5);
   
       // System.out.println( sp.afficher().toString());
       // System.out.println(sp.recherche_par_prix(1));
        System.out.println(sp.tri(5));
        
    }
    }
    


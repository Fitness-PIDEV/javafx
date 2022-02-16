/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_user;

import entities.users;
import services.ServiceUsers;

/**
 *
 * @author Ahmed
 */
public class Fitness_user {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServiceUsers sp = new ServiceUsers();
    //sp.supprimer(6);
    //sp.supprimer(4);
    //sp.ajouter(new users(15425,"sffe","gregerg","zegzg","ggregrg","adminnnn","eeeee"));
    //sp.ajouter(new users(1452,"nasri","rihab","ifbzif","jfnjfn","coach","jffjrf"));
    //sp.supprimer(8);
    //sp.modifer(new users(26420116,"Tlili","ahmed","ahmed.tlili@esprit.tn","aaa","coach","aaaaaaaaa"), 1);
   System.out.println( sp.afficher().toString());
    }
    }
    


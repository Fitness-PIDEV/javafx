/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_user;

import entities.Admin;
import entities.Coach;
import entities.Livreur;
import entities.Reclamation;
import entities.User;
import services.ServiceReclamation;
import services.ServiceUsers;
import utils.Mailapi;
import utils.MyDB;


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
        
        //Coach u =new Coach("ee",8,"ee","kk","ff","ccc","kkk",false);
        //User r=new User(11,"pp","lmm","pp","dd","ee",false);
        //sp.ajouter(r);
        //sp.modifer(u, 1);
        //System.out.println((sp.afficher()));
        //System.out.println(sp.checklogin("dsfs", "dfdd"));
        //sp.supprimer(4);
        
        ServiceReclamation sr =new ServiceReclamation();
        //Reclamation r=new Reclamation("aaaaa","ccccccc");
       // sr.ajouter(r);
       //sr.modifer(r,1 );
       //sr.supprimer(2);
        System.out.println(sp.findRoleByEmail("erferf"));
        //Mailapi.send("testapimail63@gmail.com", "TESTapimail2022", "ahmed.tlili@esprit.tn", "Mail api", "testm mail api");
        //Smsapi.sendSMS("+21626420116", "test sms api");
    //sp.supprimer(6);
    //sp.supprimer(4);
    //sp.ajouter(new users(15425,"sffe","gregerg","zegzg","ggregrg","adminnnn","eeeee"));
    //sp.ajouter(new users(1452,"nasri","rihab","ifbzif","jfnjfn","coach","jffjrf"));
    //sp.supprimer(8);
    //sp.modifer(new users(26420116,"Tlili","ahmed","ahmed.tlili@esprit.tn","aaa","coach","aaaaaaaaa"), 1);
        //System.out.println( sp.afficher().toString());
    }
 }
    


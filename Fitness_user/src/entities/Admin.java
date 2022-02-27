/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Mortadha
 */
public class Admin extends User{

    public Admin() {
    }

    public Admin(int num, String nom, String prenom, String email, String mdp, String photo, boolean isDeleted) {
        super(num, nom, prenom, email, mdp, photo, isDeleted);
    }

    public Admin(int id, int num, String nom, String prenom, String email, String mdp, String photo, boolean isDeleted) {
        super(id, num, nom, prenom, email, mdp, photo, isDeleted);
    }

    @Override
    public String toString() {
        return super.toString()+", Role=ADMIN,"+" dispo=null, certificatURL=null" + '}'+"\n";
    }
    
}

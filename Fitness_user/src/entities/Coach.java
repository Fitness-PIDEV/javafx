/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

public class Coach extends User{
    private String certificatURL;

    public Coach() {
    }
    

    public Coach(String certificatURL) {
        this.certificatURL = certificatURL;
    }

    public Coach(String certificatURL, int num, String nom, String prenom, String email, String mdp, String photo, boolean isDeleted) {
        super(num, nom, prenom, email, mdp, photo, isDeleted);
        this.certificatURL = certificatURL;
    }

    public Coach(String certificatURL, int id, int num, String nom, String prenom, String email, String mdp, String photo, boolean isDeleted) {
        super(id, num, nom, prenom, email, mdp, photo, isDeleted);
        this.certificatURL = certificatURL;
    }
    

    public String getCertificateURL() {
        return certificatURL;
    }

    public void setCertificateURL(String certificatURL) {
        this.certificatURL = certificatURL;
    }

    @Override
    public String toString() {
        return super.toString() + ", Role=COACH, dipo=null, certificatURL=" + certificatURL + '}'+"\n";
    }
    
    
    
}

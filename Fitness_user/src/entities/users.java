/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Ahmed
 */
public class users {

    private int id,num;
    private String nom,prenom,email,mdp,role,photo;

    public users() {
    }

    public users(int num, String nom, String prenom, String email, String mdp, String role, String photo) {
        this.num = num;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
        this.photo = photo;
    }
    

    public users(int id, int num, String nom, String prenom, String email, String mdp, String role, String photo) {
        this.id = id;
        this.num = num;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
        this.photo = photo;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "users{" + "id=" + id + ", num=" + num + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", mdp=" + mdp + ", role=" + role + ", photo=" + photo + '}';
    }
    
    
    
    
    
}

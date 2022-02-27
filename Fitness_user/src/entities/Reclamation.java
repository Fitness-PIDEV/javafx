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
public class Reclamation {
    private int id;
    private String titre,message;

    public Reclamation(int id, String titre, String message) {
        this.id = id;
        this.titre = titre;
        this.message = message;
    }

    public Reclamation(String titre, String message) {
        this.titre = titre;
        this.message = message;
    }

    public Reclamation() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", titre=" + titre + ", message=" + message + '}';
    }
    

}

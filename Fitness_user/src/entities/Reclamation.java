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
    private int id,iduser;
    private String titre,message;

    public Reclamation() {
    }

    public Reclamation(int iduser, String titre, String message) {
        this.iduser = iduser;
        this.titre = titre;
        this.message = message;
    }

    public Reclamation(int id, int iduser, String titre, String message) {
        this.id = id;
        this.iduser = iduser;
        this.titre = titre;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
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
        return "Reclamation{" + "id=" + id + ", iduser=" + iduser + ", titre=" + titre + ", message=" + message + '}'+"\n";
    }

    

}

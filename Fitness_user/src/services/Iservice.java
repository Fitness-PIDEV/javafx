/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

public interface Iservice<T> {

    public void ajouter(T u);
    public List<T> afficher();
    public void modifer(T u, int id);
    public void supprimer(int t);
    //to do 
    
}
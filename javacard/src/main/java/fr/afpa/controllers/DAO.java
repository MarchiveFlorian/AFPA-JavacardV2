package fr.afpa.controllers;

import java.sql.Connection;
import java.util.List;

import fr.afpa.tools.ConnectionPostgreSQL;

public abstract class DAO<T> {
    public Connection connect = ConnectionPostgreSQL.getInstance();
    
    /**
     * Permet de récupérer la totalité des objets
     * @param
     * @return List<T>
     */
    public abstract List<T> getAll();

    /**
     * Permet de récupérer un objet via son ID
     * @param id
     * @return
     */
    public abstract T find(int id);

     /**
     * Permet la suppression d'un objet selectionné
     * @param id
     */
    public abstract boolean delete(int id);

    /**
     * Permet de créer une entrée dans la base de données
     * par rapport à un objet
     * @param obj
     */
    public abstract void insert(T obj);
    
    /**
     * Permet de mettre à jour les données d'une entrée dans la base 
     * @param obj
     */
    public abstract T update(T obj);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author juand
 */
public class PersistenceManager {
    public static final boolean DEBUG = true;
    private static final PersistenceManager SINGLETON = new PersistenceManager();
    protected EntityManagerFactory emf;
    
    public static PersistenceManager getInstance(){
        return SINGLETON;
    } 
    
    public PersistenceManager(){    
    }
    
    public EntityManagerFactory getEntityManagerFactory(){
        if (emf == null) {
            createEntityManagerFactory();
        }
        return emf;
    }

    private void createEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("competitorPU", System.getProperties());
        if(DEBUG){
            System.out.println("Persistencia iniciada en " + new Date());
        }
    }
    
    public void closeEntityManagerFactory(){
        if(emf == null){
            return;
        }
        emf.close();
        emf = null;
        if(DEBUG){
            System.out.println("Persistencia finalizada en " + new Date());
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.database;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author habtamu
 */
@Stateless
public class TitlerankFacade extends AbstractFacade<Titlerank> {

    @PersistenceContext(unitName = "AttorneyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TitlerankFacade() {
        super(Titlerank.class);
    }
    
}

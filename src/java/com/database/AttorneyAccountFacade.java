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
public class AttorneyAccountFacade extends AbstractFacade<AttorneyAccount> {

    @PersistenceContext(unitName = "AttorneyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AttorneyAccountFacade() {
        super(AttorneyAccount.class);
    }
     public AttorneyAccount findLogedUser(String email, String pwdd) {
        
            AttorneyAccount attorneyAccount=null;
         
            String lowerusername=email.toLowerCase();
            try{
                 attorneyAccount= (AttorneyAccount) em.createQuery("SELECT u FROM AttorneyAccount u WHERE lower(u.email) = :email and u.password = :password"  ).setParameter("email",lowerusername).setParameter("password",pwdd).getSingleResult();
        
            }
            catch(Exception e){
            attorneyAccount=null;
              
            }
        
       return attorneyAccount;
    }
}

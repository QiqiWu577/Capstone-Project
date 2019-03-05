/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author john
 */
public class DBoperations {
    
    public String getItemsInCart() {
        String result="";
        
        try {
            String jpql="SELECT l FROM Listitems l WHERE l.listitemincart = true";
            
            EntityManager em = DBUtil.getEmFactory().createEntityManager();
            
            TypedQuery<Listitems> listitems = em.createQuery(jpql, Listitems.class);
            
            
            List<Listitems> listitemsList = null;
        
            try{
                listitemsList = listitems.getResultList();
            
                for(Listitems l: listitemsList){
                result = result + l.getListitemid()+"," + l.getListitemdesc() + "," +l.getListitemincart() + ";";
                }
            }finally{
                em.close();
            }
            
            
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        
        return result;
    }
    
    public String getItemsNotInCart() {
        String result="";
        
        try {
            String jpql="SELECT l FROM Listitems l WHERE l.listitemincart = false";
            
            EntityManager em = DBUtil.getEmFactory().createEntityManager();
            
            TypedQuery<Listitems> listitems = em.createQuery(jpql, Listitems.class);
            
            
            List<Listitems> listitemsList = null;
        
            try{
                listitemsList = listitems.getResultList();
            
                for(Listitems l: listitemsList){
                result = result + l.getListitemid()+"," + l.getListitemdesc() + "," + l.getListitemincart() + ";";
                }
            }finally{
                em.close();
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        
        return result;
    }
    
    public boolean addListItem(String listItem) {
        boolean result=false;
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.persist(new Listitems(listItem));
            
            trans.commit();
            
            result = true;
        }
        catch (Exception ex) {
            trans.rollback();
            ex.printStackTrace();
        }finally{
            em.close();
        }
        
        return result;
    }
    
    public boolean deleteListItem(int itemID) {
        boolean result=false;
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            
            //find the user and cast it into the Users object
            Listitems listitem = (Listitems)em.createNamedQuery("Listitems.findByListitemid")
                            .setParameter("listitemid", itemID).getSingleResult();
            
            em.remove(listitem);
            
            trans.commit();
            
            result = true;
        }catch(Exception ex){
            trans.rollback();
            ex.printStackTrace();
        }finally{
            em.close();
        }
        
        return result;
    }
    
    public boolean toggleInCartStatus(int itemID) {
        boolean result=false;
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        EntityTransaction trans = em.getTransaction();
        
        try {
           
            trans.begin();
            
            Listitems listitem = (Listitems)em.createNamedQuery("Listitems.findByListitemid")
                            .setParameter("listitemid", itemID).getSingleResult();
            
            boolean incartStatus = listitem.getListitemincart();
            
            if(incartStatus == true){
                incartStatus = false;
            }else{
                incartStatus = true;
            }
            listitem.setListitemincart(incartStatus);
            
            trans.commit();
            
            result = true;
        }catch(Exception ex){
            trans.rollback();
            ex.printStackTrace();
        }finally{
            em.close();
        }
        
        return result;
    }
    
}

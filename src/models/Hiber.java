/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author WSR
 */
public class Hiber {

    private static final SessionFactory sessionFactory;
    public static Session session;
    private static Transaction transaction;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void Start(){
        session = sessionFactory.openSession();
    }
    
    public static void Exit(){
        session.close();
        sessionFactory.close();
    }
    
    public static void Save(Object obj){
        transaction = session.beginTransaction();
        try {
            session.save(obj);
            transaction.commit();
            System.out.println("Succsess");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Not succsess");
        }
    }
    
    public static void Delete(Object obj){
        transaction = session.beginTransaction();
        try {
            session.delete(obj);
            transaction.commit();
            System.out.println("Succsess");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Not succsess");
        }
    }
}

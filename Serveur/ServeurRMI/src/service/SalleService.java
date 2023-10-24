/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.IDao;
import entities.Salle;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Boutissante
 */
public class SalleService extends UnicastRemoteObject implements IDao<Salle>{
 
    public SalleService() throws RemoteException {
        super();
    }

    @Override
    public boolean create(Salle o) throws RemoteException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(o);
            transaction.commit();
            return true;
        } catch (HibernateException ex) {
            if(transaction != null)
                transaction.rollback();
        }finally {
            if(session != null)
                session.close();
        }
        return false;
    }

    @Override
    public boolean update(Salle o) throws RemoteException {
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(o);
            transaction.commit();
            return 
                    true;
            
        }catch(HibernateException ex){
            if (transaction != null){
                transaction.rollback();
            }
        }finally{
            if (session!= null){
                session.close();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Salle o) throws RemoteException {
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(o);
            transaction.commit();
            return true;
            
        }catch(HibernateException ex){
            if (transaction != null){
                transaction.rollback();
            }
        }finally{
            if (session != null){
                session.close();
            }
        }
        return  false;
    }

    @Override
    public List<Salle> findAll() throws RemoteException {
        Session session = null;
        Transaction tx = null;
        List<Salle> salles = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
             salles  = session.createQuery("from Salle").list();
        tx.commit();
        } catch (Exception ex) {
            if (tx != null)
                tx.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return salles;
    }
    @Override
    public Salle findById(int id) throws RemoteException {
  Session session = null;
        Transaction tx = null;
        Salle salle = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            salle  = (Salle) session.get(Salle.class, id);
            tx.commit();
        } catch (HibernateException ex) {
            if(tx != null)
                tx.rollback();
        }finally {
            if(session != null)
                session.close();
        }
        return salle;    }
    
    
}
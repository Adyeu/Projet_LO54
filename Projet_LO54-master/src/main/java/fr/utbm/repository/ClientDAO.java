package fr.utbm.repository;

import core.util.HibernateUtil;
import fr.utbm.entity.Client;
import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ClientDAO implements Serializable {
    
    public void save(Client c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist(c);
            session.getTransaction().commit();
        }
        catch (HibernateException he) {
            he.printStackTrace();
            if(session.getTransaction() != null) {
                try {
                    session.getTransaction().rollback();
                }catch(HibernateException he2) {he2.printStackTrace(); }
            }
        }
        finally {
            if(session != null) {
                try { session.close();
                }catch(HibernateException he2) {he2.printStackTrace(); }
            }
        }
    }
    
}

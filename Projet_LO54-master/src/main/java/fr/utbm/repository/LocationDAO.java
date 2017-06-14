package fr.utbm.repository;

import core.util.HibernateUtil;
import fr.utbm.entity.Location;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class LocationDAO {
    
    public List<Location> loadAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Location");
        List<Location> locationList = query.list();
        session.close();
        return locationList;
    }
    
}

package fr.utbm.repository;

import core.util.HibernateUtil;
import fr.utbm.entity.CourseSession;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class CourseSessionDAO {
    
    public List<CourseSession> loadAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from CourseSession");
        List<CourseSession> courseSessionList = query.list();
        session.close();
        return courseSessionList;
    }
    
}

package fr.utbm.repository;

import core.util.HibernateUtil;
import fr.utbm.entity.Course;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class CourseDAO {
    
    public List<Course> loadAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Course");
        List<Course> courseList = query.list();
        session.close();
        return courseList;
    }
}

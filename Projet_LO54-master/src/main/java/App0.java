
import core.util.HibernateUtil;
import java.io.Serializable;
import org.hibernate.Session;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author agueye
 */
public class App0 implements Serializable{
    
    public static void main(String[] args)  {
    
    Session session = HibernateUtil.getSessionFactory().openSession();
    
}
}

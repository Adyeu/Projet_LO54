package fr.utbm.bean;

import fr.utbm.entity.CourseSession;
import fr.utbm.entity.Location;
import fr.utbm.repository.CourseSessionDAO;
import fr.utbm.repository.LocationDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import org.icefaces.ace.event.SelectEvent;

@ManagedBean(name="myWindowScopedBean")
@CustomScoped(value = "#{window}")
public class MyWindowScopedBean implements Serializable {
    
    private String firstName, lastName, address, phone, email;
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String FirstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public void registerUser() {
        if(firstName != null && lastName != null && address != null && phone != null && email != null) {
            System.out.println("HO");
            /*Client client = new Client();
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setAddress(address);
            client.setPhone(phone);
            client.setEmail(email);
            client.setCourseSession(selectedCourseSession);

            ClientDAO clientDAO = new ClientDAO();
            clientDAO.save(client);*/
        }
        System.out.println("HELLO");
    }
}

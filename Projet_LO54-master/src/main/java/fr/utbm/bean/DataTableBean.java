package fr.utbm.bean;

import fr.utbm.entity.Client;
import fr.utbm.entity.CourseSession;
import fr.utbm.entity.Location;
import fr.utbm.repository.ClientDAO;
import fr.utbm.repository.CourseSessionDAO;
import fr.utbm.repository.LocationDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.icefaces.ace.event.SelectEvent;
import org.icefaces.ace.event.UnselectEvent;

@ManagedBean(name="dataTableBean")
@CustomScoped(value = "#{window}")
public class DataTableBean {
 
	private List<CourseSession> courseSessionsList;
        private List<SelectItem> cityOptions;
        private CourseSession selectedCourseSession = null;
        private Boolean disableButton = true;
        private String dialogHeader = "";
        private String type;
        private String firstName, lastName, address, phone, email;
        
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        
        private Object minDate;
        public Object getMinDate() { return minDate; }
        public void setMinDate(Object minDate) { this.minDate = minDate; }

        private Object maxDate;
        public Object getMaxDate() { return maxDate; }
        public void setMaxDate(Object maxDate) { this.maxDate = maxDate; }

        public List<CourseSession> getCourseSessionsList() {
            return courseSessionsList;
        }

        public void setCourseSessionsList(List<CourseSession> courseSessionsList) {
            this.courseSessionsList = courseSessionsList;
        }

        public List<SelectItem> getCityOptions() {
            return cityOptions;
        }

        public void setCityOptions(List<SelectItem> cityOptions) {
            this.cityOptions = cityOptions;
        }

        public CourseSession getSelectedCourseSession() {
            return selectedCourseSession;
        }

        public void setSelectedCourseSession(CourseSession selectedCourseSession) {
            this.selectedCourseSession = selectedCourseSession;
        }

        public Boolean getDisableButton() {
            return disableButton;
        }

        public void setDisableButton(Boolean disableButton) {
            this.disableButton = disableButton;
        }

        public String getDialogHeader() {
            return dialogHeader;
        }

        public void setDialogHeader(String dialogHeader) {
            this.dialogHeader = dialogHeader;
        }
        
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }

        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }

        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
 
	public DataTableBean () {
                CourseSessionDAO courseSessionDAO = new CourseSessionDAO();
                courseSessionsList = courseSessionDAO.loadAll();
                
                LocationDAO locationDAO = new LocationDAO();
                List<Location> locationsList = locationDAO.loadAll();
                cityOptions = new ArrayList<SelectItem>() {{
                    add(new SelectItem(""));
                    for(Location loc: locationsList)
                        add(new SelectItem(loc.getCity()));
                }};
	}
        
        public void rowSelectListener(SelectEvent event) {
            selectedCourseSession = (CourseSession) event.getObject();
            disableButton = false;
            dialogHeader = "Registration for " + selectedCourseSession.getCourse().getTitle();
        }
        
        public void rowUnselectListener(UnselectEvent event) {
            disableButton = true;
        }

        public void registerUser() {
            if(firstName != null && !firstName.trim().isEmpty()
                    && lastName != null && !lastName.trim().isEmpty()
                    && address != null && !address.trim().isEmpty()
                    && phone != null && !phone.trim().isEmpty()
                    && email != null && !email.trim().isEmpty()) {
                Client client = new Client();
                client.setFirstName(firstName);
                client.setLastName(lastName);
                client.setAddress(address);
                client.setPhone(phone);
                client.setEmail(email);
                client.setCourseSession(selectedCourseSession);
                firstName = "";
                lastName = "";
                address = "";
                phone = "";
                email = "";

                ClientDAO clientDAO = new ClientDAO();
                clientDAO.save(client);
                
                String message = "User Successfully Created";
                FacesMessage facesMessage = new FacesMessage((FacesMessage.Severity) FacesMessage.VALUES.get(0), message, message);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            } else {
                String message = "Fields cannot be empty";
                FacesMessage facesMessage = new FacesMessage((FacesMessage.Severity) FacesMessage.VALUES.get(2), message, message);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            }
        }
}

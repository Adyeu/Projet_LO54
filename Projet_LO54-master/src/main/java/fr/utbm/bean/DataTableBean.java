/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.bean;

import fr.utbm.entity.Client;
import fr.utbm.entity.CourseSession;
import fr.utbm.entity.Location;
import fr.utbm.repository.ClientDAO;
import fr.utbm.repository.CourseSessionDAO;
import fr.utbm.repository.LocationDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import org.icefaces.ace.event.SelectEvent;

@ManagedBean(name="dataTableBean")
@ViewScoped
public class DataTableBean {
 
	private List<CourseSession> courseSessionsList;
        private List<SelectItem> cityOptions;
        private CourseSession selectedCourseSession = null;
        private Boolean disableButton = true;
        private String dialogHeader = "";
        
        private String type;
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
        
        /*private static HashMap<String, Integer> severityMap = new HashMap<String, Integer>() {{
            put("First Name", 1);
            put("Last Name", 1);
            put("Address", 1);
            put("Phone", 1);
            put("Email", 1);
        }};
        private static String[] severityNames = {"Info", "Warn", "Error", "Fatal"};
        
        public void blurListener(AjaxBehaviorEvent event) {
            if(event != null) {
                TextEntry textEntry = (TextEntry) event.getComponent();
                String value = textEntry.getValue().toString().trim();
                String label = textEntry.getLabel();
                if (value.equals("") || value.equalsIgnoreCase(label)) {
                    int index = severityMap.get(label);
                    String message;
                    if (value.equals("")) {
                        message = severityNames[index] + ": " + label + " missing.";
                    } else {
                        message = severityNames[index] + ": Value cannot be \"" + value + "\"";
                    }
                    FacesMessage facesMessage = new FacesMessage((FacesMessage.Severity) FacesMessage.VALUES.get(index), message, message);
                    FacesContext.getCurrentInstance().addMessage(textEntry.getClientId(), facesMessage);
                }
            }
        }*/
 
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.bean;

import fr.utbm.entity.CourseSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.icefaces.ace.event.SelectEvent;

/**
 *
 * @author fskaff
 */
@ManagedBean(name="dataTableBean")
@ViewScoped
public class DataTableBean {
 
	private List<CourseSession> courseSessionsList;
 
	public List<CourseSession> getCourseSessionsList() {
		return courseSessionsList;
	}
 
 
	public DataTableBean () {
		courseSessionsList = new ArrayList<CourseSession>();
		courseSessionsList.add(new CourseSession());
		courseSessionsList.add(new CourseSession());
		courseSessionsList.add(new CourseSession());
		courseSessionsList.add(new CourseSession());
	}
        
        public void rowSelectListener(SelectEvent event) {
            CourseSession courseSession = (CourseSession) event.getObject();
        }
}

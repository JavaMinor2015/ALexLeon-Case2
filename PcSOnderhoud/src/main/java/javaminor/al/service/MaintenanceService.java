package javaminor.al.service;

import javaminor.al.beans.MaintenanceBean;
import javaminor.al.business.MaintenanceStatus;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javaminor.al.entities.concrete.MaintenanceWork;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Stoux on 22/11/2015.
 */
@Stateful
@Getter
@Setter
public class MaintenanceService implements Serializable {
    private static final long serialVersionUID = -5793623445113696575L;

    private List<MaintenanceAssignment> assignments;

    @EJB
    private MaintenanceBean maintenanceBean;

    /**
     * Get all assignments that haven't been finished yet
     * @return the list
     */
    public List<MaintenanceAssignment> getUnfinishedAssignments(){
        return maintenanceBean.getAssignmentsWithStatus(MaintenanceStatus.NEW, MaintenanceStatus.IN_PROGRESS);
    }

}

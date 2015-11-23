package javaminor.al.service;

import java.io.Serializable;
import java.util.List;
import javaminor.al.beans.MaintenanceBean;
import javaminor.al.business.MaintenanceStatus;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import lombok.Getter;
import lombok.Setter;

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

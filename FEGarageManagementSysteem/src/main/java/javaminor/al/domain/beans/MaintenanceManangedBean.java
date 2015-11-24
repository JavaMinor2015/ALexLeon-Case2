package javaminor.al.domain.beans;

import java.io.Serializable;
import java.util.List;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javaminor.al.service.MaintenanceProcess;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Stoux on 23/11/2015.
 */
@Named("maintenance")
@RequestScoped
@Getter
@Setter
public class MaintenanceManangedBean implements Serializable {
    private static final long serialVersionUID = -6345558917227891127L;

    private List<MaintenanceAssignment> assignments;

    @EJB
    private MaintenanceProcess maintenanceProcess;

    @EJB
    private CarBean carBean;

    /**
     * Get all assignments that haven't been finished yet.
     *
     * @return the list
     */
    public List<MaintenanceAssignment> getUnfinishedAssignments() {
        return maintenanceProcess.getUnfinishedAssignments();
    }

}

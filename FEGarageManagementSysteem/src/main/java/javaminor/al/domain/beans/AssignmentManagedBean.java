package javaminor.al.domain.beans;

import java.io.Serializable;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javaminor.al.service.MaintenanceProcess;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Leon Stam on 23-11-2015.
 */
@Named("assignmentBean")
@SessionScoped
@Getter
@Setter
public class AssignmentManagedBean implements Serializable {
    private static final long serialVersionUID = -4328773083415169251L;

    @EJB
    private MaintenanceProcess process;

    private MaintenanceAssignment assignment;


    /**
     * Finish the assignment.
     *
     * @return the next page
     */
    public String finish() {
        //TODO: Add inspection check
        process.markAssignmentFinished(assignment);
        return "maintenanceOverview.xhtml";
    }

    /**
     * Move the assignment to IN_PROGRESS.
     *
     * @return the next page
     */
    public String inProgress() {
        process.markAssignmentInProgress(assignment);
        return "maintenanceOverview.xhtml";
    }

    /**
     * View an assignment.
     *
     * @param assignment The assignment
     * @return the page
     */
    public String viewAssignment(MaintenanceAssignment assignment) {
        setAssignment(assignment);
        return "viewAssignment.xhtml";
    }

}

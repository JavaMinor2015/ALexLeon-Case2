package javaminor.al.beans;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;
import javax.inject.Inject;

import javaminor.al.business.MaintenanceStatus;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javaminor.al.repository.MaintenanceRepository;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by alex on 11/19/15.
 */
@Stateful
@Getter
@Setter
public class MaintenanceBean implements Serializable {
    private static final long serialVersionUID = -3551969903952911887L;

    @Inject
    private MaintenanceRepository maintenanceRepository;

    /**
     * Get all assignments
     * @return the assignments
     */
    public List<MaintenanceAssignment> getAssignments() {
        return maintenanceRepository.getAll();
    }

    /**
     * Get all Assignments with a certain status
     * @param status The status
     * @return the assignments
     */
    public List<MaintenanceAssignment> getAssignmentsWithStatus(MaintenanceStatus... status) {
        return maintenanceRepository.getAllWithStatus(status);
    }

}

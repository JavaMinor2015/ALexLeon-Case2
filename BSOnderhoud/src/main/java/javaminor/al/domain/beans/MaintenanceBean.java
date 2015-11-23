package javaminor.al.domain.beans;

import java.io.Serializable;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javaminor.al.repository.MaintenanceRepository;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by alex on 11/19/15.
 */
@Stateful
@Getter
@Setter
@NoArgsConstructor
public class MaintenanceBean implements Serializable {

    private static final long serialVersionUID = -3551969903952911887L;

    @EJB
    private MaintenanceRepository maintenanceRepository;


    /**
     * Add a maintenance assignment to the repository and persist.
     *
     * @param assignment the assignment to create
     */
    public void createMaintenanceAssignment(final MaintenanceAssignment assignment) {
        maintenanceRepository.add(assignment);
        maintenanceRepository.save();
    }

    /**
     * Call to ensure fresh data.
     */
    public void refresh() {
        maintenanceRepository.save();
    }
}

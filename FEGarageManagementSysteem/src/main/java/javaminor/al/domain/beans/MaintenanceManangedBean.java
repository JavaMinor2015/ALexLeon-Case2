package javaminor.al.domain.beans;

import java.io.Serializable;
import java.util.List;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javaminor.al.service.MaintenanceService;
import javax.annotation.PostConstruct;
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
    private MaintenanceService maintenanceService;

    @EJB
    private CarBean carBean;

    /**
     * Initializes the service after bean injection.
     */
    @PostConstruct
    public void init() {
        if (assignments == null) {
            assignments = maintenanceService.getUnfinishedAssignments();
        }
    }

}

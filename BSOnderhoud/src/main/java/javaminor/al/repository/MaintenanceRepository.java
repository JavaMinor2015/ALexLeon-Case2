package javaminor.al.repository;

import java.io.Serializable;
import java.util.List;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javaminor.al.repository.abs.Repository;
import javax.ejb.Stateful;

/**
 * Created by alex on 11/19/15.
 */
@Stateful
public class MaintenanceRepository extends Repository<MaintenanceAssignment> implements Serializable {
    private static final long serialVersionUID = 3685460892410875026L;

    @Override
    public List<MaintenanceAssignment> getAll() {
        return super.getAll(MaintenanceAssignment.class);
    }
}

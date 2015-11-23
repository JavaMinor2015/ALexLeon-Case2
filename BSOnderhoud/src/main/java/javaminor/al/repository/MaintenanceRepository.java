package javaminor.al.repository;

import java.io.Serializable;
import java.util.List;
import javaminor.al.business.MaintenanceStatus;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javaminor.al.repository.abs.Repository;
import javax.ejb.Stateful;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

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

    /**
     * Get all MaintenanceAssignments with a certain status
     * @param status One or more status
     * @return The list
     */
    public List<MaintenanceAssignment> getAllWithStatus(MaintenanceStatus... status) {
        List<MaintenanceAssignment> assignments;
        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<MaintenanceAssignment> query = cb.createQuery(MaintenanceAssignment.class);
        Root<MaintenanceAssignment> root = query.from(MaintenanceAssignment.class);

        if (status.length == 0) {
            return getAll();
        }

        Expression<String> exp = root.get("status");
        query.where(exp.in((Object[]) status));
        return getEm().createQuery(query).getResultList();
    }
}

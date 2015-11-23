package javaminor.al.repository;

import javaminor.al.business.MaintenanceStatus;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javaminor.al.repository.abs.Repository;

import javax.ejb.Stateful;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 11/19/15.
 */
@Stateful
public class MaintenanceRepository extends Repository<MaintenanceAssignment> implements Serializable {

    @Override
    public List<MaintenanceAssignment> getAll() {
        return getAll(MaintenanceAssignment.class);
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

        List<Predicate> predicates = new ArrayList<>();
        for (MaintenanceStatus singleStatus : status) {
            predicates.add(cb.equal(root.get("status"), status));
        }

        query.where(predicates.toArray(new Predicate[predicates.size()]));
        return getEm().createQuery(query).getResultList();
    }
}

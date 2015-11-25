package javaminor.al.repository;

import java.io.Serializable;
import java.util.List;
import javaminor.al.entities.concrete.Driver;
import javaminor.al.repository.abs.Repository;
import javax.ejb.Stateful;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by alex on 11/10/15.
 */
@Stateful
public class DriverRepository extends Repository<Driver> implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(DriverRepository.class.getName());

    private static final long serialVersionUID = 7498092713381000282L;

    @Override
    public List<Driver> getAll() {
        return getAll(Driver.class);
    }

    /**
     * Retrieve a driver by its id.
     *
     * @param id the id to find
     * @return the corresponding driver or null
     */
    public Driver getById(final long id) {
        return super.findById(Driver.class, id);
    }

    /**
     * Find a customer by its first and last name.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the corresponding customer
     */
    public Driver findByName(final String firstName, final String lastName) {
        Driver response;
        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<Driver> cq = cb.createQuery(Driver.class);
        Root<Driver> root = cq.from(Driver.class);
        Predicate firstPred = cb.equal(root.get("firstName"), firstName);
        Predicate lastPred = cb.equal(root.get("lastName"), lastName);
        Predicate bothPred = cb.and(firstPred, lastPred);
        cq.where(bothPred);
        TypedQuery<Driver> q = getEm().createQuery(cq);
        try {
            response = q.getSingleResult();
        } catch (NonUniqueResultException e) {
            LOGGER.warn(e.getMessage(), e);
            List<Driver> responses = q.getResultList();
            if (responses != null) {
                return responses.get(0);
            }
            return null;
        } catch (NoResultException e) {
            LOGGER.info(e.getMessage(), e);
            return null;
        }
        return response;
    }
}

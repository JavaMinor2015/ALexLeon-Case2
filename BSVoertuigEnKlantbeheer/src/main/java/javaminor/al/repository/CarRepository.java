package javaminor.al.repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javaminor.al.entities.concrete.Car;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javaminor.al.repository.abs.Repository;
import javax.ejb.Stateful;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by alex on 11/10/15.
 */
@Stateful
public class CarRepository extends Repository<Car> implements Serializable {
    private static final Logger LOGGER = LogManager.getLogger(CarRepository.class.getName());
    private static final long serialVersionUID = 3877501955564103089L;

    @Override
    public List<Car> getAll() {
        return getAll(Car.class);
    }

    /**
     * Retrieve a car by its id.
     *
     * @param id the id to find
     * @return the corresponding car or null
     */
    public Car getById(final long id) {
        return super.findById(Car.class, id);
    }

    /**
     * Retrieve a car by its license plate.
     * <p>
     * If multiple results are found it will return the first result only.
     *
     * @param plateNumber the plate to find
     * @return the corresponding car or null
     */
    public Car findByPlate(final String plateNumber) {
        Car response;
        CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> root = criteriaQuery.from(Car.class);
        criteriaQuery.where(
                criteriaBuilder.equal(root.get("numberPlate"), plateNumber)
        );
        TypedQuery<Car> q = getEm().createQuery(criteriaQuery);
        try {
            response = q.getSingleResult();
        } catch (NonUniqueResultException e) {
            LOGGER.warn(e.getMessage(), e);
            response = q.getResultList().get(0);
        }
        getItemList().clear();
        getItemList().add(response);
        return response;
    }

    /**
     * Find all cars by assignments.
     *
     * @param assignments The assignments
     * @return ID of MaintenanceAssignment => Car
     */
    public Map<Long, Car> findByAssignments(List<MaintenanceAssignment> assignments) {
        //TODO: Use actual JPQL
        Map<Long, Car> cars = new HashMap<>();
        for (Car car : getAll()) {
            find(assignments, cars, car, false);
        }
        return cars;
    }

    // eww
    private void find(final List<MaintenanceAssignment> assignments, final Map<Long, Car> cars, final Car car, boolean added) {
        boolean b = added;
        for (MaintenanceAssignment maintenanceAssignment : car.getAssignments()) {
            for (MaintenanceAssignment assignment : assignments) {
                if (assignment.getId().equals(maintenanceAssignment.getId())) {
                    cars.put(assignment.getId(), car);
                    b = true;
                    break;
                }
            }
            if (b) {
                break;
            }
        }
    }


}

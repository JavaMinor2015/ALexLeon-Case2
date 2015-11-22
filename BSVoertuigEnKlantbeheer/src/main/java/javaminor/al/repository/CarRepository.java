package javaminor.al.repository;

import java.io.Serializable;
import java.util.List;
import javaminor.al.entities.concrete.Car;
import javaminor.al.repository.abs.Repository;
import javax.ejb.Stateful;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by alex on 11/10/15.
 */
@Stateful
public class CarRepository extends Repository<Car> implements Serializable {

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
     *
     * @param plateNumber the plate to find
     * @return the corresponding car or null
     */
    public Car findByPlate(final String plateNumber) {
        // TODO better naming
        Car response;
        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        Root<Car> root = cq.from(Car.class);
        cq.where(
                cb.equal(root.get("numberPlate"), plateNumber)
        );
        TypedQuery<Car> q = getEm().createQuery(cq);
        response = q.getSingleResult();
        return response;
    }
}

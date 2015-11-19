package repository;

import domain.entities.concrete.Car;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;
import repository.abs.Repository;

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

}

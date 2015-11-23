package javaminor.al.repository;

import java.io.Serializable;
import java.util.List;
import javaminor.al.entities.concrete.Car;
import javaminor.al.repository.abs.Repository;
import javax.ejb.Stateful;

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

package repository;

import domain.entities.concrete.Car;
import repository.abs.Repository;

import javax.ejb.Stateful;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

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

}

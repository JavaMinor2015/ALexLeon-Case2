package repository;

import domain.entities.concrete.Driver;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;
import repository.abs.Repository;

/**
 * Created by alex on 11/10/15.
 */
@Stateful
public class DriverRepository extends Repository<Driver> implements Serializable {

    private static final long serialVersionUID = 7498092713381000282L;

    @Override
    public List<Driver> getAll() {
        return getAll(Driver.class);
    }

}

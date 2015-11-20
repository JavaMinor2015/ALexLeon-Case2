package javaminor.al.domain.beans;

import java.io.Serializable;
import javaminor.al.entities.abs.Customer;
import javaminor.al.entities.concrete.Driver;
import javaminor.al.repository.DriverRepository;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by alex on 11/19/15.
 */
@Stateful
@Getter
@Setter
public class CustomerBean implements Serializable {

    private static final long serialVersionUID = 473528075546619935L;

    @EJB
    private DriverRepository driverRepository;

    public void createCustomer(final Customer customer) {
        if (customer instanceof Driver) {
            driverRepository.add((Driver) customer);
            driverRepository.save();
        }
    }
}

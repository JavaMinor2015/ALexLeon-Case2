package javaminor.al.domain.beans;

import java.io.Serializable;
import javaminor.al.repository.DriverRepository;
import javax.ejb.Stateful;
import javax.inject.Inject;
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

    @Inject
    private DriverRepository driverRepository;
}

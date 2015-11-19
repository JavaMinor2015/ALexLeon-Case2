package javaminor.al.domain.beans;

import java.io.Serializable;
import javaminor.al.repository.CarRepository;
import javaminor.al.repository.DriverRepository;
import javax.ejb.Stateful;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;


/**
 * Created by Leon Stam on 19-11-2015.
 */
@Stateful
@Getter
@Setter
public class GmsBean implements Serializable {
    private static final long serialVersionUID = 6942856895905488563L;

    @Inject
    private CarRepository carRepository;

    @Inject
    private DriverRepository driverRepository;


}

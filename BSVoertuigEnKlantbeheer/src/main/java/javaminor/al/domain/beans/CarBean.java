package javaminor.al.domain.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javaminor.al.entities.concrete.Car;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javaminor.al.repository.CarRepository;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import lombok.Getter;
import lombok.Setter;


/**
 * Created by Leon Stam on 19-11-2015.
 */
@Stateful
@Getter
@Setter
public class CarBean implements Serializable {
    private static final long serialVersionUID = 6942856895905488563L;

    @EJB
    private CarRepository carRepository;

    /**
     * Find a car by its license plate.
     *
     * @param plateNumber the plate number
     * @return the corresponding car or null if not found
     */
    public Car getByPlate(final String plateNumber) {
        return carRepository.findByPlate(plateNumber);
    }

    /**
     * Call to ensure fresh data.
     */
    public void refresh() {
        carRepository.update();
    }

    /**
     * Find all cars by assignments.
     *
     * @param assignments The assignments
     * @return ID of MaintenanceAssignment => Car
     */
    public Map<Long, Car> findByAssignments(List<MaintenanceAssignment> assignments) {
        return carRepository.findByAssignments(assignments);
    }
}

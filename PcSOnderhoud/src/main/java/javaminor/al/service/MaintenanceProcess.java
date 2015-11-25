package javaminor.al.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javaminor.al.business.MaintenanceStatus;
import javaminor.al.domain.beans.CarBean;
import javaminor.al.domain.beans.CustomerBean;
import javaminor.al.domain.beans.MaintenanceBean;
import javaminor.al.entities.abs.Customer;
import javaminor.al.entities.concrete.Car;
import javaminor.al.entities.concrete.Driver;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javaminor.al.error.MaintenanceException;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by alex on 11/24/15.
 */
@Stateful
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceProcess implements Serializable {
    private static final long serialVersionUID = 2045083431809608720L;

    @EJB
    private CarBean carBean;
    @EJB
    private CustomerBean customerBean;
    @EJB
    private MaintenanceBean maintenanceBean;

    @EJB
    private InspectionService inspectionService;

    /**
     * Checks whether a customer exists.
     *
     * @param firstName the first name of the customer
     * @param lastName  the last name of the customer
     * @return true if the customer exists, false otherwise
     */
    public boolean customerExists(final String firstName, final String lastName) {
        final Customer customerFound = customerBean.getCustomer(firstName, lastName);
        return customerFound != null;
    }

    /**
     * Add a customer to the application.
     *
     * @param driver the customer to add.
     */
    public void createCustomer(final Driver driver) {
        if (!customerExists(driver.getFirstName(), driver.getLastName())) {
            // only add if it doesn't exist
            customerBean.createCustomer(driver);
        }
        // otherwise silently ignore
    }

    /**
     * Check whether a car exists.
     *
     * @param licensePlate the license plate of the car.
     * @return true if the car exists, false otherwise
     */
    public boolean carExists(final String licensePlate) {
        final Car carFound = getCar(licensePlate);
        return carFound != null;
    }

    /**
     * Adds a car to the application.
     *
     * @param car    the car to add
     * @param driver the driver to add it to
     */
    public void addCar(final Car car, final Driver driver) {
        if (!carExists(car.getNumberPlate())) {
            // only add if it doesn't exist
            if (!driver.hasCar(car)) {
                // only persist if the customer does not have the car
                driver.addCar(car);
            }
            car.setDriver(driver);
            carBean.refresh(car);
        }
        // otherwise silently ignore
    }

    /**
     * Adds an assignment to the application.
     *
     * @param assignment the assignment to add.
     * @param car        the car to add it to.
     * @throws MaintenanceException if the assignment is incorrect
     */
    public void addAssignment(final MaintenanceAssignment assignment, final Car car) {
        if (assignment.getStatus() != MaintenanceStatus.NEW) {
            throw new MaintenanceException("This assignment is not new", assignment);
        }
        if (!car.hasAssignment(assignment)) {
            // only persist if the car does not have the assignment
            car.addAssignment(assignment);
        }
        assignment.setCar(car);
        carBean.refresh(car);
        maintenanceBean.refresh(assignment);
    }

    /**
     * Find a MaintenanceAssignment by it's ID.
     *
     * @param id The ID
     * @return The MaintenanceAssignment if found
     */
    public Optional<MaintenanceAssignment> findById(long id) {
        return maintenanceBean.findById(id);
    }

    /**
     * Get all assignments that haven't been finished yet.
     *
     * @return the list
     */
    public List<MaintenanceAssignment> getUnfinishedAssignments() {
        return maintenanceBean.getAssignmentsWithStatus(MaintenanceStatus.NEW, MaintenanceStatus.IN_PROGRESS);
    }

    /**
     * Mark an assignment in progress.
     *
     * @param assignment The assignment
     * @throws MaintenanceException if the assignment is not new
     */
    public void markAssignmentInProgress(final MaintenanceAssignment assignment) {
        if (assignment.getStatus() != MaintenanceStatus.NEW) {
            throw new MaintenanceException("This assignment is already in progress or finished", assignment);
        }
        assignment.setStatus(MaintenanceStatus.IN_PROGRESS);
        updateAssignment(assignment);
    }

    /**
     * Mark an assignment with an inspection as done.
     *
     * @param assignment the assignment.
     * @throws MaintenanceException if the assignment is incorrect
     */
    public void markInspectionDone(final MaintenanceAssignment assignment) {
        if (assignment.getStatus() != MaintenanceStatus.IN_PROGRESS) {
            throw new MaintenanceException("This assignment has a status which does not allow modification", assignment);
        }
        if (!assignment.isApk()) {
            throw new MaintenanceException("This assignment is not marked for inspection.", assignment);
        }

        boolean ohnoez = inspectionService.steekproef(assignment.getCar().getNumberPlate());
        assignment.setApk(false);
        assignment.setSpotCheck(ohnoez);
        if (!ohnoez) {
            assignment.setStatus(MaintenanceStatus.FINISHED);
        }
        updateAssignment(assignment);
    }

    /**
     * Mark an assignment finished.
     *
     * @param assignment the assignment to mark finished.
     * @throws MaintenanceException if the assignment is incorrect
     */
    public void markAssignmentFinished(final MaintenanceAssignment assignment) {
        if (assignment.getStatus() != MaintenanceStatus.IN_PROGRESS) {
            throw new MaintenanceException("This assignment is not in progress", assignment);
        }
        assignment.setStatus(MaintenanceStatus.FINISHED);
        updateAssignment(assignment);
    }

    private void updateAssignment(MaintenanceAssignment assignment) {
        maintenanceBean.refresh(assignment);
    }

    /**
     * Retrieve a car by its license plate.
     *
     * @param licensePlate the license plate
     * @return the corresponding car, or null
     */
    public Car getCar(final String licensePlate) {
        return carBean.getByPlate(licensePlate);
    }

    /**
     * Marks an assignment spotcheck complete.
     *
     * @param assignment the assignment to mark complete
     */
    public void markSpotCheckDone(final MaintenanceAssignment assignment) {
        assignment.setSpotCheck(false);
        assignment.setStatus(MaintenanceStatus.FINISHED);
        updateAssignment(assignment);
    }
}

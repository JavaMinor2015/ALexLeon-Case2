package javaminor.al.service;

import java.util.List;
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

/**
 * Created by alex on 11/24/15.
 */
@Stateful
public class MaintenanceProcess {

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
        final Car carFound = carBean.getByPlate(licensePlate);
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
            carBean.refresh();
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
        carBean.refresh();
        maintenanceBean.refresh();
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
     * Mark an assignment with an inspection as done.
     *
     * @param assignment the assignment.
     * @return true if an audit is requested, false otherwise.
     * @throws MaintenanceException if the assignment is incorrect
     */
    public boolean markInspectionDone(final MaintenanceAssignment assignment) {
        if (assignment.getStatus() == MaintenanceStatus.NEW || assignment.getStatus() == MaintenanceStatus.FINISHED) {
            throw new MaintenanceException("This assignment has a status which does not allow modification", assignment);
        }
        if (!assignment.isApk()) {
            throw new MaintenanceException("This assignment is not marked for inspection.", assignment);
        }
        return inspectionService.requiresInspection(assignment.getCar().getNumberPlate());
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
        maintenanceBean.refresh();
    }
}

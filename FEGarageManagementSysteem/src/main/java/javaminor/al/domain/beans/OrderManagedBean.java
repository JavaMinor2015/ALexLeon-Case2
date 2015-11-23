package javaminor.al.domain.beans;

import java.io.Serializable;
import java.util.ArrayList;
import javaminor.al.business.MaintenanceStatus;
import javaminor.al.entities.concrete.Car;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javaminor.al.entities.concrete.MaintenanceWork;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by alex on 11/22/15.
 */
@Named("order")
@SessionScoped
@Getter
@Setter
public class OrderManagedBean implements Serializable {
    private static final long serialVersionUID = -4720067158883460744L;
    private static final Logger LOGGER = LogManager.getLogger(OrderManagedBean.class.getName());

    @EJB
    private MaintenanceBean maintenanceBean;

    @EJB
    private CarBean carBean;

    private Car car;

    private MaintenanceAssignment maintenanceAssignment;

    /**
     * Initializes the service after bean injection.
     */
    @PostConstruct
    public void init() {
        maintenanceAssignment = new MaintenanceAssignment();
        maintenanceAssignment.setStatus(MaintenanceStatus.NEW);
        maintenanceAssignment.setExecutedWork(new ArrayList<>());
    }

    /**
     * Persist the order and go to the next step.
     *
     * @param numberPlate the license plate of the car this order is for
     * @return the next page in the process.
     */
    public String addOrder(final String numberPlate) {
        // TODO move this stuff to the OnderhoudProces module
        car = carBean.getByPlate(numberPlate);
        if (car == null) {
            FacesContext.getCurrentInstance().addMessage("addOrder:orderCreateBtn", new
                    FacesMessage("Invalid license plate"));
            return "addOrder";
        }

        if (maintenanceAssignment == null) {
            FacesContext.getCurrentInstance().addMessage("addOrder:orderCreateBtn", new
                    FacesMessage("Invalid maintenance work"));
            return "addOrder";
        }

        if (car.getAssignments() == null) {
            car.setAssignments(new ArrayList<>());
        }

        car.getAssignments().add(maintenanceAssignment);
        maintenanceAssignment.setCar(car);
        carBean.refresh();
        return "maintenanceOverview";
    }

    public void addWork() {
        maintenanceAssignment.getExecutedWork().add(MaintenanceWork.builder().build());
        car = carBean.update(car);
    }

    public boolean hasEmptyWorkOrders() {
        if (maintenanceAssignment.getExecutedWork().isEmpty()) {
            return false;
        }
        for (MaintenanceWork maintenanceWork : maintenanceAssignment.getExecutedWork()) {
            if (maintenanceWork.getComments() == null) {
                return true;
            }
        }
        return false;
    }

    public void saveWork() {
        car = carBean.update(car);
    }

    public void markAssignmentTaken() {
        maintenanceAssignment.setStatus(MaintenanceStatus.IN_PROGRESS);
        carBean.refresh();
    }

    public void markAssignmentFinished() {
        maintenanceAssignment.setStatus(MaintenanceStatus.FINISHED);
        carBean.refresh();
    }

    public boolean assignmentInProgress() {
        if (maintenanceAssignment.getStatus().equals(MaintenanceStatus.IN_PROGRESS)) {
            return true;
        }
        return false;
    }
}

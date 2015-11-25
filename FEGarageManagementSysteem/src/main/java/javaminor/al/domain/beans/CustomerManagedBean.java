package javaminor.al.domain.beans;

import java.io.Serializable;
import java.util.ArrayList;
import javaminor.al.entities.concrete.Car;
import javaminor.al.entities.concrete.Driver;
import javaminor.al.service.MaintenanceProcess;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by alex on 11/19/15.
 */
@Named("customer")
@SessionScoped
@Getter
@Setter
public class CustomerManagedBean implements Serializable {
    private static final long serialVersionUID = 8020406095868256398L;
    private static final Logger LOGGER = LogManager.getLogger(CustomerManagedBean.class.getName());
    private static final String ADD_CUSTOMER = "addCustomer";

    //TODO refactor to proper Customer with Driver/LeaseCompany option
    private Driver driver;

    private Car car;

    @EJB
    private CustomerBean bean;

    @EJB
    private CarBean carBean;

    @EJB
    private MaintenanceProcess maintenanceProcess;

    /**
     * Initializes the service after bean injection.
     */
    @PostConstruct
    public void init() {
        driver = new Driver();
        driver.setCars(new ArrayList<>());
    }

    /**
     * Create a new blank car.
     */
    public void initCar() {
        car = new Car();
    }

    /**
     * Check if a customer exists.
     *
     * @return the next page in the process.
     */
    public String checkCustomer() {

        // if proper info is not present, return to page
        if (driver.getFirstName() == null || driver.getLastName() == null) {
            return "index";
        }

        // if exists, go to customer page
        if (maintenanceProcess.customerExists(driver.getFirstName(), driver.getLastName())) {
            // FIXME: 11/24/15 will break when leasecompany arrives
            driver = (Driver) bean.getCustomer(driver.getFirstName(), driver.getLastName());
            return "viewCustomer";
        }

        // if not exists create
        return ADD_CUSTOMER;
    }

    /**
     * Persist the customer and go to the next step.
     *
     * @return the next page in the process.
     */
    public String createCustomer() {
        try {
            bean.createCustomer(driver);
        } catch (ConstraintViolationException e) {
            // TODO does this even work?
            FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(e.getConstraintViolations().toString()));
            LOGGER.warn(e.getMessage(), e);
            return ADD_CUSTOMER;
        }

        FacesContext.getCurrentInstance().addMessage("addCustomer:customerCreateBtn", new
                FacesMessage("Added customer: " + driver.getFirstName()));

        // then go to customer page
        return "viewCustomer";
    }

    /**
     * Set the car.
     *
     * @param licensePlate the car's plate
     * @return the next page in the process
     */
    public String setTheCar(final String licensePlate) {
        LOGGER.error(licensePlate);
        this.car = maintenanceProcess.getCar(licensePlate);

        return "addOrder";
    }

    /**
     * Add a car to the current customer and go to the next step.
     *
     * @return the next page in the process.
     */
    @Transactional
    public String addCar() {
        if (driver.getFirstName() == null) {
            FacesContext.getCurrentInstance().addMessage("addCustomer:customerCreateBtn", new
                    FacesMessage("Create a customer first"));
            return ADD_CUSTOMER;
        }
        driver.getCars().add(car);
        car.setDriver(driver);

        carBean.addCar(car);
        bean.refresh(driver);

        FacesContext.getCurrentInstance().addMessage("addCar:carCreateBtn", new
                FacesMessage("Added car: " + car.getModel()));
        return "addOrder";
    }
}

package javaminor.al.beans;

import java.io.Serializable;
import java.util.ArrayList;
import javaminor.al.domain.beans.CustomerBean;
import javaminor.al.entities.concrete.Car;
import javaminor.al.entities.concrete.Driver;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
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

    //TODO refactor to proper Customer with Driver/LeaseCompany option
    private Driver driver;

    private Car car;

    @EJB
    private CustomerBean bean;

    /**
     * Initializes the service after bean injection.
     */
    @PostConstruct
    public void init() {
        driver = new Driver();
        driver.setCars(new ArrayList<>());
        car = new Car();
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
            return "addCustomer";
        }

        FacesContext.getCurrentInstance().addMessage("addCustomer:customerCreateBtn", new
                FacesMessage("Added customer: " + driver.getFirstName()));
        return "addCar";
    }

    /**
     * Add a car to the current customer and go to the next step.
     *
     * @return the next page in the process.
     */
    public String addCar() {
        if (driver.getFirstName() == null) {
            FacesContext.getCurrentInstance().addMessage("addCustomer:customerCreateBtn", new
                    FacesMessage("Create a customer first"));
            return "addCustomer";
        }
        driver.getCars().add(car);
        bean.refresh();
        FacesContext.getCurrentInstance().addMessage("addCar:carCreateBtn", new
                FacesMessage("Added car: " + car.getModel()));
        return "index";
    }
}

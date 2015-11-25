package javaminor.al.domain.beans;

import java.io.Serializable;
import javaminor.al.entities.concrete.Driver;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Leon Stam on 25-11-2015.
 */
@Named("cView")
@SessionScoped
@Getter
@Setter
public class CustomerViewManagedBean implements Serializable {
    private static final long serialVersionUID = 1756969611443591160L;
    private Driver driver;

    /**
     * Set the customer for the info page.
     *
     * @param customer The customer
     * @return the page to go to
     */
    public String toCustomer(Driver customer) {
        this.driver = customer;
        return "viewCustomerInfo.xhtml";
    }

}

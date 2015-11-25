package javaminor.al.domain.beans;

import java.io.Serializable;
import java.util.List;
import javaminor.al.entities.concrete.Driver;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Leon Stam on 25-11-2015.
 */
@Named("custOverview")
@RequestScoped
@Getter
@Setter
public class CustomerOverviewManagedBean implements Serializable {
    private static final long serialVersionUID = -2442312167760594508L;

    @EJB
    private CustomerBean customerBean;

    private List<Driver> drivers;

    /** Initializes the service after bean injection. */
    @PostConstruct
    public void init() {
        if (drivers == null) {
            drivers = customerBean.getAll();
        }
    }


}

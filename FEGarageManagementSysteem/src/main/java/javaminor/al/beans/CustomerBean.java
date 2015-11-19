package javaminor.al.beans;

import java.io.Serializable;
import javaminor.al.entities.concrete.Driver;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by alex on 11/19/15.
 */
@Named("customer")
@Getter
@Setter
public class CustomerBean implements Serializable {
    private static final long serialVersionUID = 8020406095868256398L;

    private Driver driver;

    /**
     * Initializes the service after bean injection.
     */
    @PostConstruct
    public void init() {
        driver = new Driver();
    }
}

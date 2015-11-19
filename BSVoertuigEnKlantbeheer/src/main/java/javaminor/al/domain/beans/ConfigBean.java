package javaminor.al.domain.beans;

import java.security.SecureRandom;
import javaminor.al.entities.concrete.Driver;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by alex on 11/19/15.
 */
@Singleton
@Startup
public class ConfigBean {
    @PersistenceContext(unitName = "GMSDerbyPersist")
    private EntityManager em;

    private static final int AMOUNT = 100;
    private final SecureRandom r = new SecureRandom();

    /**
     * Initialize dummy data.
     */
    @PostConstruct
    public void init() {

        generateDummyData();
    }

    private void generateDummyData() {
        Driver driver = new Driver();
        driver.setFirstName("John");
        driver.setInsertion("of");
        driver.setLastName("Doe");
        driver.setPhone("01234567897");
        driver.setNumber("5A");
        driver.setStreetName("Lanestreet");
        driver.setZipCode("4040AA");
        driver.setCity("Dordrecht");
        driver.setEmail("me@mycompany.com");
    }
}

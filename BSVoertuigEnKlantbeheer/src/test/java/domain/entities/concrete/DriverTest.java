package domain.entities.concrete;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.eclipse.persistence.jpa.jpql.Assert.fail;

/**
 * Created by alex on 11/19/15.
 */
public class DriverTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testDriver() {
        Driver driver = new Driver();
        driver.setFirstName("John");
        driver.setInsertion("of");
        driver.setLastName("Doe");
        // no validation exceptions should be thrown
    }

    @Test
    public void testDriverFirstNameInvalid() {
        String[] invalids = new String[]{null, "",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"};
        for (String invalid : invalids) {
            try {
                new Driver().setPhone(invalid);
                fail("Exception expected for string: " + invalid + " in testPhoneInvalid()");
            } catch (Exception e) {
                // success
            }
        }
    }
}
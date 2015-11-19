package javaminor.al.entities.concrete;

import javaminor.al.business.Rules;
import javaminor.al.util.StrUtil;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alex on 11/19/15.
 */
public class DriverTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();
    }

    @Test
    public void testDriver() {
        Driver driver = new Driver();
        driver.setFirstName("John");
        driver.setInsertion("of");
        driver.setLastName("Doe");
        // no validation exceptions should be thrown

        assertThat(driver.getFirstName(), is("John"));
        assertThat(driver.getInsertion(), is("of"));
        assertThat(driver.getLastName(), is("Doe"));
    }

    @Test
    public void testDriverFirstNameInvalid() {
        String[] invalids = new String[]{null, "", StrUtil.getRandomString(Rules.FIRST_NAME_MAX_LENGTH + 1)};
        for (String invalid : invalids) {
            Driver driver = new Driver();
            driver.setFirstName(invalid);
            assertViolation(driver);
        }
    }

    @Test
    public void testDriverLastNameInvalid() {
        String[] invalids = new String[]{null, "", StrUtil.getRandomString(Rules.LAST_NAME_MAX_LENGTH + 1)};
        for (String invalid : invalids) {
            Driver driver = new Driver();
            driver.setLastName(invalid);
            assertViolation(driver);
        }
    }

    @Test
    public void testDriverInsertionInvalid() {
        String[] invalids = new String[]{null, "", StrUtil.getRandomString(Rules.INSERTION_MAX_LENGTH + 1)};
        for (String invalid : invalids) {
            Driver driver = new Driver();
            driver.setInsertion(invalid);
            assertViolation(driver);
        }
    }

    private void assertViolation(final Driver driver) {
        thrown.expect(ValidationException.class);
        this.validator.validate(driver);
    }
}
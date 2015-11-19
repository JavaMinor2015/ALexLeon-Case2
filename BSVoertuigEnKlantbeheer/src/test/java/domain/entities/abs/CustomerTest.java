package domain.entities.abs;

import java.util.Set;
import javax.validation.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.eclipse.persistence.jpa.jpql.Assert.fail;
import static org.junit.Assert.assertTrue;

/**
 * Created by alex on 11/19/15.
 */
public class CustomerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Customer customer;
    private Validator validator;

    @Before
    public void setUp() throws Exception {
        customer = new Customer();
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();
    }

    @Test
    public void testCustomerValid() {
        Customer validCustomer = Customer.builder()
                .city("Dordrecht")
                .email("me@company.com")
                .number("4A")
                .phone("01234567891")
                .streetName("Streetlane")
                .zipCode("4942DM")
                .build();
        Set<ConstraintViolation<Customer>> violations = this.validator.validate(validCustomer);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testStreetInvalid() {
        Customer invalidStreet = Customer.vb
    }

    @Test
    public void testNumberInvalid() {
        String[] invalids = new String[]{null, "", "asdasda"};
        for (String invalid : invalids) {
            try {
                customer.setNumber(invalid);
                fail("Exception expected in testNumberInvalid()");
            } catch (Exception e) {
                // success
            }
        }
    }

    @Test
    public void testZipCodeInvalid() {
        // TODO use validationfactory?
        customer.setZipCode(null);
    }


    @Test
    public void testCityInvalid() {
        String[] invalids = new String[]{null, "",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"};
        for (String invalid : invalids) {
            try {
                customer.setCity(invalid);
                fail("Exception expected in testCityInvalid()");
            } catch (Exception e) {
                // success
            }
        }
    }


    @Test
    public void testPhoneInvalid() {
        String[] invalids = new String[]{null, "", "12345678",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"};
        for (String invalid : invalids) {
            try {
                customer.setPhone(invalid);
                fail("Exception expected for string: " + invalid + " in testPhoneInvalid()");
            } catch (Exception e) {
                // success
            }
        }
    }

    @Test
    public void testEmailValid() {
        customer.setEmail("me@company.com");
    }

    @Test
    public void testEmailInvalid() {
        // TODO use validationfactory?
        customer.setEmail("invalid");
    }

    @Test
    public void testBuilder() throws Exception {
        Customer customer = Customer.builder()
                .city("Dordrecht")
                .email("me@companu.com")
                .number("5A")
                .phone("01234567890")
                .streetName("My Street")
                .zipCode("4942AM")
                .build();
        // no validation exceptions should be thrown
    }

    private void assertHasOneViolation(final Customer customer) {
        Set<ConstraintViolation<Customer>> violations = this.validator.validate(customer);
        assertTrue(violations.size() == 1);
    }
}
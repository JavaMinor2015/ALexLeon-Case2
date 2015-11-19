package domain.entities.abs;

import business.Rules;
import java.util.Set;
import javax.validation.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import util.StrUtil;

import static org.junit.Assert.assertTrue;

/**
 * Created by alex on 11/19/15.
 */
public class CustomerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Validator validator;

    @Before
    public void setUp() throws Exception {
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
        Customer invalidStreet = Customer.builder()
                .streetName("")
                .build();
        assertViolation(invalidStreet);

        invalidStreet = Customer.builder()
                .streetName(null)
                .build();
        assertViolation(invalidStreet);

        invalidStreet = Customer.builder()
                .streetName(StrUtil.getRandomString(Rules.STREET_NAME_MAX_LENGTH + 1))
                .build();
        assertViolation(invalidStreet);
    }

    @Test
    public void testNumberInvalid() {
        String[] invalids = new String[]{null, "", StrUtil.getRandomString(Rules.STREET_NUMBER_MAX_LENGTH + 1)};
        for (String invalid : invalids) {
            Customer invalidCustomer = Customer.builder().number(invalid).build();
            assertViolation(invalidCustomer);
        }
    }

    @Test
    public void testZipCodeInvalid() {
        String[] invalids = new String[]{null, "", "564a6d4a9s8d49a8s4d"};
        for (String invalid : invalids) {
            Customer invalidCustomer = Customer.builder().zipCode(invalid).build();
            assertViolation(invalidCustomer);
        }
    }

    @Test
    public void testCityInvalid() {
        String[] invalids = new String[]{null, "", StrUtil.getRandomString(Rules.CITY_MAX_LENGTH + 1)};
        for (String invalid : invalids) {
            Customer invalidCustomer = Customer.builder().city(invalid).build();
            assertViolation(invalidCustomer);
        }
    }


    @Test
    public void testPhoneInvalid() {
        String[] invalids = new String[]{null, "", StrUtil.getRandomString(Rules.PHONE_NUMBER_MAX_LENGTH + 1)};
        for (String invalid : invalids) {
            Customer invalidCustomer = Customer.builder().phone(invalid).build();
            assertViolation(invalidCustomer);
        }
    }

    @Test
    public void testEmailInvalid() {
        String[] invalids = new String[]{null, "", "me@my"};
        for (String invalid : invalids) {
            Customer invalidCustomer = Customer.builder().email(invalid).build();
            assertViolation(invalidCustomer);
        }
    }

    @Test
    public void testBuilder() throws Exception {
        Customer.builder()
                .city("Dordrecht")
                .email("me@companu.com")
                .number("5A")
                .phone("01234567890")
                .streetName("My Street")
                .zipCode("4942AM")
                .build();
        // no validation exceptions should be thrown
    }

    private void assertViolation(final Customer customer) {
        thrown.expect(ValidationException.class);
        this.validator.validate(customer);
    }
}
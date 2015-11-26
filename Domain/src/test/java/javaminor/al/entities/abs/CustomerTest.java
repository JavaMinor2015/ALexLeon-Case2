package javaminor.al.entities.abs;

import java.util.Set;
import javaminor.al.business.Rules;
import javaminor.al.util.StrUtil;
import javax.validation.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
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
    public void testId() {
        // ensure persistability
        Customer customer = new Customer();
        customer.setId(5L);
        assertThat(customer.getId(), is(5L));
    }

    @Test
    public void testCustomerValid() {
        // builder
        Customer validCustomer = Customer.builder()
                .city("Dordrecht")
                .email("me@company.com")
                .streetNumber("4A")
                .phone("01234567891")
                .streetName("Streetlane")
                .zipCode("4942DM")
                .build();
        Set<ConstraintViolation<Customer>> violations = this.validator
                .validate(validCustomer);
        assertTrue(violations.isEmpty());

        // setter
        Customer customer = new Customer();
        customer.setCity("Dordrecht");
        customer.setEmail("me@company.com");
        customer.setStreetNumber("4A");
        customer.setPhone("01234567891");
        customer.setStreetName("Streetlane");
        customer.setZipCode("4942DM");

        violations = this.validator
                .validate(customer);
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
                .streetName(StrUtil.getRandomString(Rules
                        .STREET_NAME_MAX_LENGTH + 1))
                .build();
        assertViolation(invalidStreet);
    }

    @Test
    public void testStreetsInvalid() {
        String[] invalids = new String[]{null, "", StrUtil.getRandomString
                (Rules.STREET_NAME_MAX_LENGTH + 1)};
        for (String invalid : invalids) {
            // through builder
            Customer invalidCustomer = Customer.builder().streetName(invalid)
                    .build();
            assertViolation(invalidCustomer);

            // through setter
            invalidCustomer.setStreetName(invalid);
            assertViolation(invalidCustomer);

            // ensure that the value is still present despite violation
            assertThat(invalidCustomer.getStreetName(), is(invalid));
        }
    }

    @Test
    public void testNumberInvalid() {
        String[] invalids = new String[]{null, "", StrUtil.getRandomString
                (Rules.STREET_NUMBER_MAX_LENGTH + 1)};
        for (String invalid : invalids) {
            // through builder
            Customer invalidCustomer = Customer.builder().streetNumber(invalid)
                    .build();
            assertViolation(invalidCustomer);

            // through setter
            invalidCustomer.setStreetNumber(invalid);
            assertViolation(invalidCustomer);

            // ensure that the value is still present despite violation
            assertThat(invalidCustomer.getStreetNumber(), is(invalid));
        }
    }

    @Test
    public void testZipCodeInvalid() {
        String[] invalids = new String[]{null, "", "564a6d4a9s8d49a8s4d"};
        for (String invalid : invalids) {
            // through builder
            Customer invalidCustomer = Customer.builder().zipCode(invalid)
                    .build();
            assertViolation(invalidCustomer);

            // through setter
            invalidCustomer.setZipCode(invalid);
            assertViolation(invalidCustomer);

            // ensure that the value is still present despite violation
            assertThat(invalidCustomer.getZipCode(), is(invalid));
        }
    }

    @Test
    public void testCityInvalid() {
        String[] invalids = new String[]{null, "", StrUtil.getRandomString
                (Rules.CITY_MAX_LENGTH + 1)};
        for (String invalid : invalids) {
            // through builder
            Customer invalidCustomer = Customer.builder().city(invalid).build();
            assertViolation(invalidCustomer);

            // through setter
            invalidCustomer.setCity(invalid);
            assertViolation(invalidCustomer);

            // ensure that the value is still present despite violation
            assertThat(invalidCustomer.getCity(), is(invalid));
        }
    }


    @Test
    public void testPhoneInvalid() {
        String[] invalids = new String[]{null, "", StrUtil.getRandomString
                (Rules.PHONE_NUMBER_MAX_LENGTH + 1)};
        for (String invalid : invalids) {
            // through builder
            Customer invalidCustomer = Customer.builder().phone(invalid)
                    .build();
            assertViolation(invalidCustomer);

            // through setter
            invalidCustomer.setPhone(invalid);
            assertViolation(invalidCustomer);

            // ensure that the value is still present despite violation
            assertThat(invalidCustomer.getPhone(), is(invalid));
        }
    }

    @Test
    public void testEmailInvalid() {
        String[] invalids = new String[]{null, "", "me@my"};
        for (String invalid : invalids) {
            // through builder
            Customer invalidCustomer = Customer.builder().email(invalid)
                    .build();
            assertViolation(invalidCustomer);

            // through setter
            invalidCustomer.setEmail(invalid);
            assertViolation(invalidCustomer);

            // ensure that the value is still present despite violation
            assertThat(invalidCustomer.getEmail(), is(invalid));
        }
    }

    @Test
    public void testBuilder() throws Exception {
        Customer.builder()
                .city("Dordrecht")
                .email("me@companu.com")
                .streetNumber("5A")
                .phone("01234567890")
                .streetName("My Street")
                .zipCode("4942AM")
                .build();
        // no validation exceptions should be thrown
    }

    private void assertViolation(final Customer customer) {
        // use try catch so we can continue after a check
        try {
            this.validator.validate(customer);
        } catch (ValidationException e) {
            // all good
        }
    }
}
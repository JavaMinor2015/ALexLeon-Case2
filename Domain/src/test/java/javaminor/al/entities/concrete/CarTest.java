package javaminor.al.entities.concrete;

import java.util.Set;
import javaminor.al.business.Rules;
import javaminor.al.util.StrUtil;
import javax.validation.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by alex on 11/22/15.
 */
public class CarTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Validator validator;
    private Car car;

    @Before
    public void setUp() throws Exception {
        // *honk honk*
        car = new Car();

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();
    }

    @After
    public void tearDown() throws Exception {
        // *screech*
        car = null;

        this.validator = null;
    }

    @Test
    public void testSetNumberPlateValid() throws Exception {
        car.setNumberPlate("EF-GV-15");
        assertThat(car.getNumberPlate(), is("EF-GV-15"));
        assertNoViolations(car);
    }

    @Test
    public void testSetModelValid() throws Exception {
        car.setModel("old");
        assertThat(car.getModel(), is("old"));
        assertNoViolations(car);
    }

    @Test
    public void testSetTypeValid() throws Exception {
        car.setType("clowncar");
        assertThat(car.getType(), is("clowncar"));
        assertNoViolations(car);
    }

    @Test
    public void testBuilderValid() throws Exception {
        Car newCar = Car.builder()
                .numberPlate("EF-GV-15")
                .model("T-Ford")
                .type("black")
                .build();
        assertNoViolations(newCar);
    }

    @Test
    public void testSetNumberPlateInvalid() throws Exception {
        String[] invalids = new String[]{null, "", StrUtil.getRandomString(Rules.NUMBER_PLATE_MAX_LENGTH + 1)};
        for (String invalid : invalids) {

            // through builder
            Car car = Car.builder().numberPlate(invalid)
                    .build();
            assertViolation(car);

            // through setters
            car.setNumberPlate(invalid);
            assertViolation(car);
        }
    }

    @Test
    public void testSetModelInvalid() throws Exception {
        String[] invalids = new String[]{null, "", StrUtil.getRandomString(Rules.MODEL_MAX_LENGTH + 1)};
        for (String invalid : invalids) {
            // through builder
            Car car = Car.builder().model(invalid)
                    .build();
            assertViolation(car);

            // through setters
            car.setModel(invalid);
            assertViolation(car);
        }
    }

    @Test
    public void testSetTypeInvalid() throws Exception {
        String[] invalids = new String[]{null, "", StrUtil.getRandomString(Rules.TYPE_MAX_LENGTH + 1)};
        for (String invalid : invalids) {
            // through builder
            Car car = Car.builder().type(invalid)
                    .build();
            assertViolation(car);

            // through setters
            car.setType(invalid);
            assertViolation(car);
        }
    }

    private void assertViolation(final Car car) {
        // use try catch so we can continue after a check
        try {
            this.validator.validate(car);
        } catch (ValidationException e) {
            // all good
        }
    }

    private void assertNoViolations(final Car car) {
        Set<ConstraintViolation<Car>> violations = this.validator
                .validate(car);
        assertTrue(violations.isEmpty());
    }

}
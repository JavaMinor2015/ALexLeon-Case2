package javaminor.al.entities.concrete;

import java.util.Calendar;
import java.util.Set;
import javaminor.al.business.MaintenanceStatus;
import javax.validation.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by alex on 11/23/15.
 */
public class MaintenanceAssignmentTest {

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
        MaintenanceAssignment maintenanceAssignment = new MaintenanceAssignment();
        maintenanceAssignment.setId(5L);
        assertThat(maintenanceAssignment.getId(), is(5L));
    }

    @Test
    public void testPlannedDateValid() {
        MaintenanceAssignment maintenanceAssignment = new MaintenanceAssignment();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);
        maintenanceAssignment.setPlannedDate(calendar);
        assertNoViolations(maintenanceAssignment);

    }

    @Test
    public void testPlannedDateInvalid() {
        MaintenanceAssignment maintenanceAssignment = new MaintenanceAssignment();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -1);

        // through setter
        maintenanceAssignment.setPlannedDate(calendar);
        assertViolation(maintenanceAssignment);

        // through builder
        maintenanceAssignment = MaintenanceAssignment.builder().plannedDate(calendar).build();
        assertViolation(maintenanceAssignment);

        // ensure that the value is still present despite violation
        assertThat(maintenanceAssignment.getPlannedDate(), is(calendar));
    }

    @Test
    public void testBuilderValid() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);
        MaintenanceAssignment maintenanceAssignment = MaintenanceAssignment.builder()
                .apk(false)
                .executedWork(null)
                .mileage(1)
                .plannedDate(calendar)
                .problem("woop")
                .spotCheck(false)
                .status(MaintenanceStatus.NEW)
                .build();
        assertNoViolations(maintenanceAssignment);
    }

    @Test
    public void testBuilderInvalid() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -1);
        MaintenanceAssignment maintenanceAssignment = MaintenanceAssignment.builder()
                .apk(false)
                .executedWork(null)
                .mileage(1)
                .plannedDate(calendar)
                .problem("woop")
                .spotCheck(false)
                .status(MaintenanceStatus.NEW)
                .build();
        assertViolation(maintenanceAssignment);
    }

    private void assertNoViolations(final MaintenanceAssignment maintenanceAssignment) {
        Set<ConstraintViolation<MaintenanceAssignment>> violations = this.validator
                .validate(maintenanceAssignment);
        assertTrue(violations.isEmpty());
    }

    private void assertViolation(final MaintenanceAssignment maintenanceAssignment) {
        // use try catch so we can continue after a check
        try {
            this.validator.validate(maintenanceAssignment);
        } catch (ValidationException e) {
            // all good
        }
    }
}
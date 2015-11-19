package javaminor.al.entities.validation;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alex on 11/19/15.
 */
public class ZipCodeValidatorTest {

    @Test
    public void testIsValid() throws Exception {
        ZipCodeValidator validator = new ZipCodeValidator();
        validator.initialize(null);
        assertThat(validator.isValid("4942DM", null), is(true));
    }

    @Test
    public void testIsInvalid() throws Exception {
        ZipCodeValidator validator = new ZipCodeValidator();
        validator.initialize(null);
        assertThat(validator.isValid("ABCDEFG12", null), is(false));
    }
}
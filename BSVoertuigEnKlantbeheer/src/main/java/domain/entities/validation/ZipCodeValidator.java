package domain.entities.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by alex on 11/19/15.
 */
public class ZipCodeValidator implements ConstraintValidator<ValidZipCode, String> {

    private List<String> validPatterns;

    @Override
    public void initialize(final ValidZipCode validZipCode) {
        validPatterns = new ArrayList<>();

        // UK pattern
        validPatterns.add("(GIR 0AA)|((([A-Z-[QVX]][0-9][0-9]?)|("
                + "([A-Z-[QVX]][A-Z-[IJZ]][0-9][0-9]?)|(([A-Z-[QVX]][0-9][A-HJKPSTUW])|"
                + "([A-Z-[QVX]][A-Z-[IJZ]][0-9][ABEHMNPRVWXY])))) [0-9][A-Z-[CIKMOV]]{2})");

        // NL pattern
        validPatterns.add("/^[1-9][0-9]{3} ?(?!sa|sd|ss)[a-z]{2}$/");
    }

    @Override
    public boolean isValid(final String s, final ConstraintValidatorContext constraintValidatorContext) {
        for (String validPattern : validPatterns) {
            if (s.matches(validPattern)) {
                return true;
            }
        }
        return false;
    }
}

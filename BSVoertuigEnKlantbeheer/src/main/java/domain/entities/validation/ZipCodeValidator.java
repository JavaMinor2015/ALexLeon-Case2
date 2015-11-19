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
        validPatterns.add("^[1-9][0-9]{3}\\s?[a-zA-Z]{2}$");
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

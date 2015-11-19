package domain.entities.validation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Created by alex on 11/19/15.
 */
@Constraint(validatedBy = {ZipCodeValidator.class})
@Documented
@Target({ElementType.METHOD,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidZipCode {
    // TODO i18n

    /**
     * The message to return in case of failure.
     *
     * @return the error message.
     */
    String message() default "Invalid Zip Code";

    /**
     * Groups?.
     *
     * @return list of classes
     */
    Class<?>[] groups() default {};

    /**
     * Payload?.
     *
     * @return list of Payload classes
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * A list?.
     */
    @Target({
            ElementType.METHOD,
            ElementType.FIELD,
            ElementType.ANNOTATION_TYPE,
            ElementType.CONSTRUCTOR,
            ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        /**
         * List of annotations.
         *
         * @return a list of ValidZipCode annotations.
         */
        ValidZipCode[] value();
    }
}

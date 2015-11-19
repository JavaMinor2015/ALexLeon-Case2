package domain.entities.abs;

import business.Rules;
import domain.entities.validation.ValidZipCode;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by alex on 11/19/15.
 */
public class Customer extends PersistentEntity {

    @Size(min = Rules.STREET_NAME_MIN_LENGTH, max = Rules.STREET_NAME_MAX_LENGTH)
    private String streetName;

    @Size(min = Rules.STREET_NUMBER_MIN_LENGTH, max = Rules.STREET_NUMBER_MAX_LENGTH)
    private String number;

    @ValidZipCode
    private String zipCode;

    @Size(min = Rules.CITY_MIN_LENGTH, max = Rules.CITY_MAX_LENGTH)
    private String city;

    @Size(min = Rules.PHONE_NUMBER_MIN_LENGTH, max = Rules.PHONE_NUMBER_MAX_LENGTH)
    private String phone;

    @Pattern(regexp = Rules.EMAIL_PATTERN)
    private String email;
}

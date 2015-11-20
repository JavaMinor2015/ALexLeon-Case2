package javaminor.al.entities.abs;

import javaminor.al.business.Rules;
import javaminor.al.entities.validation.ValidZipCode;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.*;

/**
 * Created by alex on 11/19/15.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MappedSuperclass
public class Customer extends PersistentEntity {

    @Size(min = Rules.STREET_NAME_MIN_LENGTH, max = Rules.STREET_NAME_MAX_LENGTH)
    @NotNull
    private String streetName;

    @Size(min = Rules.STREET_NUMBER_MIN_LENGTH, max = Rules.STREET_NUMBER_MAX_LENGTH)
    @NotNull
    private String number;

    @ValidZipCode(message = "Zip code not in correct format!")
    @NotNull
    private String zipCode;

    @Size(min = Rules.CITY_MIN_LENGTH, max = Rules.CITY_MAX_LENGTH)
    @NotNull
    private String city;

    @Size(min = Rules.PHONE_NUMBER_MIN_LENGTH, max = Rules.PHONE_NUMBER_MAX_LENGTH)
    @NotNull
    private String phone;

    @Pattern(regexp = Rules.EMAIL_PATTERN)
    @NotNull
    private String email;
}

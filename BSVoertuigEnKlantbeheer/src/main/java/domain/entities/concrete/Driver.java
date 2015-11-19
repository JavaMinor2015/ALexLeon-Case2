package domain.entities.concrete;

import business.Rules;
import domain.entities.abs.Customer;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import lombok.*;

/**
 * Created by alex on 11/19/15.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Driver extends Customer {

    @Size(min = Rules.FIRST_NAME_MIN_LENGTH, max = Rules.FIRST_NAME_MAX_LENGTH)
    private String firstName;

    @Size(min = Rules.INSERTION_MIN_LENGTH, max = Rules.INSERTION_MAX_LENGTH)
    private String insertion;

    @Size(min = Rules.LAST_NAME_MIN_LENGTH, max = Rules.LAST_NAME_MAX_LENGTH)
    private String lastName;

}

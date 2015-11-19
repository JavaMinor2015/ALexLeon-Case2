package javaminor.al.entities.concrete;

import java.util.List;
import javaminor.al.business.Rules;
import javaminor.al.entities.abs.Customer;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by alex on 11/19/15.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Driver extends Customer {
    private static final long serialVersionUID = 1307990296574264319L;

    @Size(min = Rules.FIRST_NAME_MIN_LENGTH, max = Rules.FIRST_NAME_MAX_LENGTH)
    private String firstName;

    @Size(min = Rules.INSERTION_MIN_LENGTH, max = Rules.INSERTION_MAX_LENGTH)
    private String insertion;

    @Size(min = Rules.LAST_NAME_MIN_LENGTH, max = Rules.LAST_NAME_MAX_LENGTH)
    private String lastName;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Car> cars;

}

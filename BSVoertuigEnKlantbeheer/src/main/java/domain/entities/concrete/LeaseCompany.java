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
public class LeaseCompany extends Customer {
    private static final long serialVersionUID = -2194603534759142127L;

    @Size(min = Rules.COMPANY_NAME_MIN_LENGTH, max = Rules.COMPANY_NAME_MAX_LENGTH)
    private String name;

}

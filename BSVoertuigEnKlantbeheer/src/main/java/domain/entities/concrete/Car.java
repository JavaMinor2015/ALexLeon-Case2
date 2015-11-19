package domain.entities.concrete;

import business.Rules;
import domain.entities.abs.PersistentEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import lombok.*;

/**
 * Created by Leon Stam on 19-11-2015.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car extends PersistentEntity {
    private static final long serialVersionUID = 5102067162211765503L;

    @Size(min = Rules.NUMBER_PLATE_MIN_LENGTH, max = Rules.NUMBER_PLATE_MAX_LENGTH)
    private String numberPlate;

    @Size(min = Rules.MODEL_MIN_LENGTH, max = Rules.MODEL_MAX_LENGTH)
    private String model;

    @Size(min = Rules.TYPE_MIN_LENGTH, max = Rules.TYPE_MAX_LENGTH)
    private String type;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<MaintenanceAssignment> assignments;

}

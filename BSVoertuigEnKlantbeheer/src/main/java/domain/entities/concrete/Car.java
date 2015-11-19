package domain.entities.concrete;

import business.Rules;
import domain.entities.abs.PersistentEntity;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.List;

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

    @Size(min = Rules.NUMBER_PLATE_MIN_LENGTH, max = Rules.NUMBER_PLATE_MAX_LENGTH)
    private String numberPlate;

    @Size(min = Rules.MODEL_MIN_LENGTH, max = Rules.MODEL_MAX_LENGTH)
    private String model;

    @Size(min = Rules.TYPE_MIN_LENGTH, max = Rules.TYPE_MAX_LENGTH)
    private String type;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<MaintenanceAssignment> assignments;

}

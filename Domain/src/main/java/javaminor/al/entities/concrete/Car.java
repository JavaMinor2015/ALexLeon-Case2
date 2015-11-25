package javaminor.al.entities.concrete;

import java.util.ArrayList;
import java.util.List;
import javaminor.al.business.Rules;
import javaminor.al.entities.abs.PersistentEntity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "car")
    private List<MaintenanceAssignment> assignments;

    @ManyToOne
    private Driver driver;


    /**
     * Checks whether this car has this assignment.
     *
     * @param assignment the assignment to check for
     * @return true if this assignment exists for this car, false otherwise
     */
    public boolean hasAssignment(final MaintenanceAssignment assignment) {
        return (assignments != null && assignments.contains(assignment));
    }

    /**
     * Adds an assignment to this car.
     *
     * @param assignment the assignment to add
     */
    public void addAssignment(final MaintenanceAssignment assignment) {
        if (assignments == null) {
            assignments = new ArrayList<>();
        }
        assignments.add(assignment);
    }
}

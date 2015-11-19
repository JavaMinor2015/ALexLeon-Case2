package domain.entities.concrete;

import business.MaintenanceStatus;
import domain.entities.abs.PersistentEntity;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Future;
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
public class MaintenanceAssignment extends PersistentEntity {
    private static final long serialVersionUID = 2243631662528928856L;

    @Future
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar plannedDate;

    private int mileage;

    private boolean apk;
    
    private boolean spotCheck;

    private String problem;

    private MaintenanceStatus status;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<MaintenanceWork> executedWork;

}

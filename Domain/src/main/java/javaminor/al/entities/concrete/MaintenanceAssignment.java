package javaminor.al.entities.concrete;

import java.util.Calendar;
import java.util.List;
import javaminor.al.business.MaintenanceStatus;
import javaminor.al.entities.abs.PersistentEntity;
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Car car;

}

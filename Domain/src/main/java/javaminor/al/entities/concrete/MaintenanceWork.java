package javaminor.al.entities.concrete;

import java.util.Calendar;
import javaminor.al.entities.abs.PersistentEntity;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
public class MaintenanceWork extends PersistentEntity {
    private static final long serialVersionUID = -6671470658970621897L;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar startedOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar finishedOn;

    private double workHours;

    private String comments;
}

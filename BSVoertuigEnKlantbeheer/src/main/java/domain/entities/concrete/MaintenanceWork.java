package domain.entities.concrete;

import domain.entities.abs.PersistentEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

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

    private int workHours;

    private String comments;
}

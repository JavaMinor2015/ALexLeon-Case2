package javaminor.al.entities.abs;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by alex on 11/19/15.
 */
@MappedSuperclass
public abstract class PersistentEntity implements Serializable {

    private static final long serialVersionUID = 2731951438060507474L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
}

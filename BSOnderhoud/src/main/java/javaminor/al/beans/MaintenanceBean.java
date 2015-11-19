package javaminor.al.beans;

import java.io.Serializable;
import javax.ejb.Stateful;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by alex on 11/19/15.
 */
@Stateful
@Getter
@Setter
public class MaintenanceBean implements Serializable {

    private static final long serialVersionUID = -3551969903952911887L;
}

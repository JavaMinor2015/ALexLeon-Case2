package javaminor.al.service;

import java.security.SecureRandom;
import javax.ejb.Stateful;

/**
 * Created by alex on 11/24/15.
 */
@Stateful
public class InspectionService {

    /**
     * Dummy method.
     *
     * @param licensePlate the license plate of the inspected car.
     * @return true if inspection required, false otherwise.
     */
    public boolean requiresInspection(final String licensePlate) {
        return new SecureRandom().nextBoolean();
    }
}

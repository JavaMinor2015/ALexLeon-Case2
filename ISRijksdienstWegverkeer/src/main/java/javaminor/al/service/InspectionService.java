package javaminor.al.service;

import java.io.Serializable;
import java.security.SecureRandom;
import javax.ejb.Stateless;

/**
 * Created by alex on 11/24/15.
 */
@Stateless
public class InspectionService implements Serializable
//, RDWSteekproefWebService
{

    private static final long serialVersionUID = 1321483742815039719L;

    /**
     * Dummy method.
     *
     * @param licensePlate the license plate of the inspected car.
     * @return true if inspection required, false otherwise.
     */
    public Boolean steekproef(final String licensePlate) {
        return new SecureRandom().nextBoolean();
    }
}

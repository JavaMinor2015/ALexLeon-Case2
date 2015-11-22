package javaminor.al.domain.beans;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by alex on 11/19/15.
 */
@Singleton
@Startup
public class ConfigBean {
    @PersistenceContext(unitName = "GMSDerbyPersist")
    private EntityManager em;

    /**
     * Initialize dummy data.
     */
    @PostConstruct
    public void init() {
        // TODO no worky
    }

}

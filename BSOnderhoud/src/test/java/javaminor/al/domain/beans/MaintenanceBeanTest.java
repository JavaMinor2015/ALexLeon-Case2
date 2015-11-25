package javaminor.al.domain.beans;

import javaminor.al.entities.concrete.MaintenanceAssignment;
import javaminor.al.repository.MaintenanceRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

/**
 * Created by alex on 11/23/15.
 */
public class MaintenanceBeanTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private MaintenanceRepository mockMaintenanceRepository;
    private MaintenanceBean maintenanceBean;

    @Before
    public void setUp() throws Exception {
        maintenanceBean = new MaintenanceBean();
        mockMaintenanceRepository = mock(MaintenanceRepository.class);
        maintenanceBean.setMaintenanceRepository(mockMaintenanceRepository);
        assertThat(maintenanceBean.getMaintenanceRepository(), is(mockMaintenanceRepository));
    }

    @Test
    public void testCreateMaintenanceAssignment() throws Exception {
        doNothing().when(mockMaintenanceRepository).save(any(MaintenanceAssignment.class));
        maintenanceBean.createMaintenanceAssignment(new MaintenanceAssignment());
    }

    @Test
    public void testRefresh() throws Exception {
        doNothing().when(mockMaintenanceRepository).save(null);
        // no exceptions
        maintenanceBean.refresh(null);
    }
}
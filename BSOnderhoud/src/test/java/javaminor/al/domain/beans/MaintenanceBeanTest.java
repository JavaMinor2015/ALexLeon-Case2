package javaminor.al.domain.beans;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javaminor.al.business.MaintenanceStatus;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javaminor.al.repository.MaintenanceRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by alex on 11/23/15.
 */
public class MaintenanceBeanTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private MaintenanceRepository mockMaintenanceRepository;
    private MaintenanceBean maintenanceBean;
    private List<MaintenanceAssignment> assignmentList = Arrays.asList(new MaintenanceAssignment()
            , new MaintenanceAssignment());

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

    @Test
    public void testGetAssignments() throws Exception {
        when(maintenanceBean.getAssignments()).thenReturn(assignmentList);
        assertThat(maintenanceBean.getAssignments(), is(assignmentList));
    }

    @Test
    public void testGetAssignmentsWithStatus() throws Exception {
        assignmentList.get(0).setStatus(MaintenanceStatus.FINISHED);
        when(maintenanceBean.getAssignmentsWithStatus(MaintenanceStatus.FINISHED)).thenReturn(assignmentList.subList(0, 1));
        assertThat(maintenanceBean.getAssignmentsWithStatus(MaintenanceStatus.FINISHED).get(0), is(assignmentList.get(0)));
    }

    @Test
    public void testFindById() throws Exception {
        when(mockMaintenanceRepository.findById(1L)).thenReturn(Optional.of(assignmentList.get(0)));
        assertThat(maintenanceBean.findById(1L), is(Optional.of(assignmentList.get(0))));
    }
}
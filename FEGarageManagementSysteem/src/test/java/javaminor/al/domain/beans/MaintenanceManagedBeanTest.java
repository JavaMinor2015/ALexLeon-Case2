package javaminor.al.domain.beans;

import java.util.Arrays;
import java.util.List;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javaminor.al.service.MaintenanceProcess;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by alex on 11/26/15.
 */
public class MaintenanceManagedBeanTest {

    private MaintenanceManagedBean maintenanceManagedBean;
    private List<MaintenanceAssignment> assignmentList = Arrays.asList(new MaintenanceAssignment()
            , new MaintenanceAssignment());
    private MaintenanceProcess mockedMaintenanceProcess;

    @Before
    public void setUp() {
        maintenanceManagedBean = new MaintenanceManagedBean();
        mockedMaintenanceProcess = mock(MaintenanceProcess.class);
        maintenanceManagedBean.setAssignments(assignmentList);
        maintenanceManagedBean.setMaintenanceProcess(mockedMaintenanceProcess);

        assertThat(maintenanceManagedBean.getAssignments(), is(assignmentList));
        assertThat(maintenanceManagedBean.getMaintenanceProcess(), is(mockedMaintenanceProcess));
    }

    @Test
    public void testGetUnfinishedAssignments() throws Exception {
        when(mockedMaintenanceProcess.getUnfinishedAssignments()).thenReturn(assignmentList);
        assertThat(maintenanceManagedBean.getUnfinishedAssignments(), is(assignmentList));
    }
}
package javaminor.al.domain.beans;

import javaminor.al.entities.concrete.MaintenanceAssignment;
import javaminor.al.service.MaintenanceProcess;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

/**
 * Created by alex on 11/26/15.
 */
public class AssignmentManagedBeanTest {
    public static final String MAINTENANCE_OVERVIEW_XHTML = "maintenanceOverview.xhtml";
    public static final String VIEW_ASSIGNMENT_XHTML = "viewAssignment.xhtml";

    private AssignmentManagedBean assignmentManagedBean;
    private MaintenanceProcess mockMaintenanceProcess;
    private MaintenanceAssignment mockMaintenanceAssignment;

    @Before
    public void setup() {
        assignmentManagedBean = new AssignmentManagedBean();
        mockMaintenanceAssignment = mock(MaintenanceAssignment.class);
        mockMaintenanceProcess = mock(MaintenanceProcess.class);

        assignmentManagedBean.setAssignment(mockMaintenanceAssignment);
        assignmentManagedBean.setProcess(mockMaintenanceProcess);
        assertThat(assignmentManagedBean.getAssignment(), is(mockMaintenanceAssignment));
        assertThat(assignmentManagedBean.getProcess(), is(mockMaintenanceProcess));
    }

    @Test
    public void testFinish() {
        doNothing().when(mockMaintenanceProcess).markAssignmentFinished(any(MaintenanceAssignment.class));
        assertThat(assignmentManagedBean.finish(), is(MAINTENANCE_OVERVIEW_XHTML));
    }

    @Test
    public void testInProgress() {
        doNothing().when(mockMaintenanceProcess).markAssignmentInProgress(any(MaintenanceAssignment.class));
        assertThat(assignmentManagedBean.inProgress(), is(MAINTENANCE_OVERVIEW_XHTML));
    }

    @Test
    public void testViewAssignment() {
        assertThat(assignmentManagedBean.viewAssignment(mockMaintenanceAssignment), is(VIEW_ASSIGNMENT_XHTML));
    }

    @Test
    public void testMarkInspectionDone() {
        doNothing().when(mockMaintenanceProcess).markInspectionDone(any(MaintenanceAssignment.class));
        assertThat(assignmentManagedBean.markInspectionDone(mockMaintenanceAssignment), is(MAINTENANCE_OVERVIEW_XHTML));
    }

    @Test
    public void testMarkSpotCheckDone() {
        doNothing().when(mockMaintenanceProcess).markSpotCheckDone(any(MaintenanceAssignment.class));
        assertThat(assignmentManagedBean.markSpotCheckDone(mockMaintenanceAssignment), is(MAINTENANCE_OVERVIEW_XHTML));
    }
}
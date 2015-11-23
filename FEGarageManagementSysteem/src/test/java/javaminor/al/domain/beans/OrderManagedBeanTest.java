package javaminor.al.domain.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javaminor.al.entities.concrete.Car;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by alex on 11/23/15.
 */
public class OrderManagedBeanTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CarBean mockedCarBean;
    private MaintenanceBean mockedMaintenanceBean;
    private MaintenanceAssignment mockedAssignment;
    private List<MaintenanceAssignment> assignmentList;
    private OrderManagedBean orderManagedBean;
    private FacesContext context;
    private Car testCar;

    /**
     * @see MockFacesContext
     */
    @Before
    public void setUp() {
        mockedCarBean = mock(CarBean.class);
        mockedMaintenanceBean = mock(MaintenanceBean.class);
        mockedAssignment = mock(MaintenanceAssignment.class);
        testCar = new Car("AA", "BB", "CC", new ArrayList<>(), null);
        assignmentList = new ArrayList<>();
        assignmentList.add(mockedAssignment);
        orderManagedBean = new OrderManagedBean();
        orderManagedBean.init();
        orderManagedBean.setCarBean(mockedCarBean);
        orderManagedBean.setMaintenanceAssignment(mockedAssignment);
        orderManagedBean.setMaintenanceBean(mockedMaintenanceBean);

        assertThat(orderManagedBean.getMaintenanceAssignment(), is(mockedAssignment));
        assertThat(orderManagedBean.getCarBean(), is(mockedCarBean));
        assertThat(orderManagedBean.getMaintenanceBean(), is(mockedMaintenanceBean));
        // oh yes, we're going there
        context = MockFacesContext.mockFacesContext();
        Map<String, Object> session = new HashMap<>();
        ExternalContext ext = mock(ExternalContext.class);
        when(ext.getSessionMap()).thenReturn(session);
        when(context.getExternalContext()).thenReturn(ext);
        doNothing().when(context).addMessage(anyString(), any(FacesMessage
                .class));
    }

    @After
    public void tearDown() {
        context.release();
    }

    @Test
    public void testAddOrder() throws Exception {
        when(mockedCarBean.getByPlate("AA")).thenReturn(testCar);
        assertThat(orderManagedBean.addOrder("AA"), is("maintenanceOverview"));
        assertThat(testCar.getAssignments().size(), is(1));

        when(mockedCarBean.getByPlate("AA")).thenReturn(null);
        assertThat(orderManagedBean.addOrder("AA"), is("addOrder"));

        when(mockedCarBean.getByPlate("AA")).thenReturn(testCar);
        orderManagedBean.setMaintenanceAssignment(null);
        assertThat(orderManagedBean.addOrder("AA"), is("addOrder"));

        orderManagedBean.setMaintenanceAssignment(mockedAssignment);
        testCar.setAssignments(null);

        assertThat(orderManagedBean.addOrder("AA"), is("maintenanceOverview"));
    }
}
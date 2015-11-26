package javaminor.al.domain.beans;

import java.util.*;
import javaminor.al.entities.abs.Customer;
import javaminor.al.entities.concrete.Car;
import javaminor.al.entities.concrete.Driver;
import javaminor.al.service.MaintenanceProcess;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by alex on 11/22/15.
 */
public class CustomerManagedBeanTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CustomerBean mockedBean;
    private CarBean mockedCarBean;
    private Driver mockedDriver;
    private Car mockedCar;
    private List<Car> carList;
    private CustomerManagedBean customerManagedBean;
    private FacesContext context;
    private MaintenanceProcess mockMaintenanceProcess;

    /**
     * @see MockFacesContext
     */
    @Before
    public void setUp() {
        mockedBean = mock(CustomerBean.class);
        mockedCarBean = mock(CarBean.class);
        mockedDriver = mock(Driver.class);
        mockedCar = mock(Car.class);
        mockMaintenanceProcess = mock(MaintenanceProcess.class);

        carList = new ArrayList<>();
        carList.add(mockedCar);
        customerManagedBean = new CustomerManagedBean();
        customerManagedBean.init();
        customerManagedBean.setBean(mockedBean);
        customerManagedBean.setCarBean(mockedCarBean);
        customerManagedBean.setDriver(mockedDriver);
        customerManagedBean.setCar(mockedCar);
        customerManagedBean.setMaintenanceProcess(mockMaintenanceProcess);

        assertThat(customerManagedBean.getDriver(), is(mockedDriver));
        assertThat(customerManagedBean.getBean(), is(mockedBean));
        assertThat(customerManagedBean.getCar(), is(mockedCar));
        assertThat(customerManagedBean.getCarBean(), is(mockedCarBean));
        assertThat(customerManagedBean.getMaintenanceProcess(), is(mockMaintenanceProcess));

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
    public void testCreateCustomer() throws Exception {
        when(mockedDriver.getFirstName()).thenReturn("John");
        doNothing()
                .doThrow(new ConstraintViolationException("woop", new
                        HashSet<>()))
                .when(mockedBean).createCustomer(any(Customer.class));
        customerManagedBean.createCustomer();

        // exception should be caught
        customerManagedBean.createCustomer();
    }

    @Test
    public void testAddCar() throws Exception {

        doNothing().when(mockedCarBean).addCar(any(Car.class));

        // driver has not been properly created yet
        assertThat(customerManagedBean.addCar(), is("addCustomer"));

        when(mockedDriver.getFirstName()).thenReturn("John");
        assertThat(customerManagedBean.addCar(), is("addOrder"));
    }

    @Test
    public void testInitCar() {
        customerManagedBean.initCar();
        assertThat(customerManagedBean.getCar(), not(mockedCar));
        customerManagedBean.setCar(mockedCar);
    }

    @Test
    public void testSetTheCar() {
        customerManagedBean.setCar(new Car());
        when(mockMaintenanceProcess.getCar(any(String.class))).thenReturn(mockedCar);
        assertThat(customerManagedBean.setTheCar("woop"), is("addOrder"));
        assertThat(customerManagedBean.getCar(), is(mockedCar));
    }

    @Test
    public void testCheckCustomerTrue() {
        assertThat(customerManagedBean.checkCustomer(), is("index"));

        Driver driver = new Driver();
        driver.setFirstName("foo");
        driver.setLastName("bar");
        customerManagedBean.setDriver(driver);
        when(mockMaintenanceProcess.customerExists("foo", "bar")).thenReturn(true);

        assertThat(customerManagedBean.checkCustomer(), is("viewCustomer"));
    }

    @Test
    public void testCheckCustomerFalse() {
        Driver driver = new Driver();
        customerManagedBean.setDriver(driver);


        driver.setFirstName("foo");
        assertThat(customerManagedBean.checkCustomer(), is("index"));

        driver.setLastName("bar");
        when(mockedBean.getCustomer("foo", "bar")).thenReturn(mockedDriver);

        when(mockMaintenanceProcess.customerExists("foo", "bar")).thenReturn(false);
        assertThat(customerManagedBean.checkCustomer(), is("addCustomer"));
    }
}
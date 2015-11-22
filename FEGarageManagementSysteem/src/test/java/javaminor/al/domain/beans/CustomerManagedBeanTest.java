package javaminor.al.domain.beans;

import java.util.*;
import javaminor.al.entities.abs.Customer;
import javaminor.al.entities.concrete.Car;
import javaminor.al.entities.concrete.Driver;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
    private Driver mockedDriver;
    private Car mockedCar;
    private List<Car> carList;
    private CustomerManagedBean customerManagedBean;
    private FacesContext context;

    /**
     * @see MockFacesContext
     */
    @Before
    public void setUp() {
        mockedBean = mock(CustomerBean.class);
        mockedDriver = mock(Driver.class);
        mockedCar = mock(Car.class);
        carList = new ArrayList<>();
        carList.add(mockedCar);
        customerManagedBean = new CustomerManagedBean();
        customerManagedBean.init();
        customerManagedBean.setBean(mockedBean);
        customerManagedBean.setDriver(mockedDriver);
        customerManagedBean.setCar(mockedCar);

        assertThat(customerManagedBean.getDriver(), is(mockedDriver));
        assertThat(customerManagedBean.getBean(), is(mockedBean));
        assertThat(customerManagedBean.getCar(), is(mockedCar));
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
        // driver has not been properly created yet
        assertThat(customerManagedBean.addCar(), is("addCustomer"));

        when(mockedDriver.getFirstName()).thenReturn("John");
        assertThat(customerManagedBean.addCar(), is("index"));
    }
}
package javaminor.al.beans;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javaminor.al.domain.beans.CustomerBean;
import javaminor.al.entities.abs.Customer;
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
    private CustomerManagedBean customerManagedBean;
    private FacesContext context;

    @Before
    public void setUp() {
        mockedBean = mock(CustomerBean.class);
        mockedDriver = mock(Driver.class);
        customerManagedBean = new CustomerManagedBean();
        customerManagedBean.init();
        customerManagedBean.setBean(mockedBean);
        customerManagedBean.setDriver(mockedDriver);

        assertThat(customerManagedBean.getDriver(), is(mockedDriver));
        assertThat(customerManagedBean.getBean(), is(mockedBean));

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
}
package javaminor.al.domain.beans;

import javaminor.al.entities.abs.Customer;
import javaminor.al.entities.concrete.Driver;
import javaminor.al.repository.DriverRepository;
import org.junit.After;
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
 * Created by alex on 11/22/15.
 */
public class CustomerBeanTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private DriverRepository mockDriverRepository;
    private CustomerBean customerBean;

    @Before
    public void setUp() throws Exception {
        customerBean = new CustomerBean();
        mockDriverRepository = mock(DriverRepository.class);
        customerBean.setDriverRepository(mockDriverRepository);
        assertThat(customerBean.getDriverRepository(), is(mockDriverRepository));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateCustomer() throws Exception {
        doNothing().when(mockDriverRepository).add(any(Driver.class));
        doNothing().when(mockDriverRepository).save();
        customerBean.createCustomer(new Driver());
        customerBean.createCustomer(new Customer());
    }
}
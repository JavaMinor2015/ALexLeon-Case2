package javaminor.al.domain.beans;

import java.util.Arrays;
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
import static org.mockito.Mockito.*;


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
        doNothing().when(mockDriverRepository).save(any(Driver.class));
        customerBean.createCustomer(new Driver());
        customerBean.createCustomer(new Customer());
    }

    @Test
    public void testRefresh() throws Exception {
        doNothing().when(mockDriverRepository).save(any(Driver.class));
        // no exceptions
        customerBean.refresh(new Driver());
        customerBean.refresh(null);
    }

    @Test
    public void testFindByName() throws Exception {
        Driver remco = new Driver();
        when(mockDriverRepository.findByName("a", "b")).thenReturn(remco);
        assertThat(customerBean.getCustomer("a", "b"), is(remco));
    }

    @Test
    public void testGetAll() throws Exception {
        when(mockDriverRepository.getAll()).thenReturn(Arrays.asList(new Driver(), new Driver()));
        assertThat(customerBean.getAll().size(), is(2));
    }
}
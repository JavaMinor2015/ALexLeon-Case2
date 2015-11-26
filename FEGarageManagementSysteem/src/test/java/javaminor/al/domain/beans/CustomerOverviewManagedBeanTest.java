package javaminor.al.domain.beans;

import java.util.Arrays;
import java.util.List;
import javaminor.al.entities.concrete.Driver;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by alex on 11/26/15.
 */
public class CustomerOverviewManagedBeanTest {

    private CustomerOverviewManagedBean customerOverviewManagedBean;
    private CustomerBean mockedCustomerBean;
    private List<Driver> driverList = Arrays.asList(new Driver(), new Driver());

    @Before
    public void setUp() {
        customerOverviewManagedBean = new CustomerOverviewManagedBean();
        mockedCustomerBean = mock(CustomerBean.class);
        customerOverviewManagedBean.setCustomerBean(mockedCustomerBean);
        customerOverviewManagedBean.setDrivers(driverList);

        assertThat(customerOverviewManagedBean.getCustomerBean(), is(mockedCustomerBean));
        assertThat(customerOverviewManagedBean.getDrivers(), is(driverList));
    }

    @Test
    public void testInit() throws Exception {
        customerOverviewManagedBean.init();
        assertThat(customerOverviewManagedBean.getDrivers(), is(driverList));
        customerOverviewManagedBean.setDrivers(null);
        assertThat(customerOverviewManagedBean.getDrivers(), is(nullValue()));
        customerOverviewManagedBean.init();
        assertThat(customerOverviewManagedBean.getDrivers().size(), is(0));
    }
}
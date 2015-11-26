package javaminor.al.domain.beans;

import javaminor.al.entities.concrete.Driver;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alex on 11/26/15.
 */
public class CustomerViewManagedBeanTest {

    private CustomerViewManagedBean customerViewManagedBean;
    private Driver testDriver;

    @Before
    public void setUp() {
        customerViewManagedBean = new CustomerViewManagedBean();
        testDriver = new Driver();
        customerViewManagedBean.setDriver(testDriver);
        assertThat(customerViewManagedBean.getDriver(), is(testDriver));
    }

    @Test
    public void testToCustomer() throws Exception {
        assertThat(customerViewManagedBean.toCustomer(testDriver), is("viewCustomerInfo.xhtml"));
    }
}
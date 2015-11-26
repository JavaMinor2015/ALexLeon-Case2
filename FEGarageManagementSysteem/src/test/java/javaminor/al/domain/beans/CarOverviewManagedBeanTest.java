package javaminor.al.domain.beans;

import java.util.Arrays;
import java.util.List;
import javaminor.al.entities.concrete.Car;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by alex on 11/26/15.
 */
public class CarOverviewManagedBeanTest {

    private CarOverviewManagedBean carOverviewManagedBean;
    private CarBean mockedCarBean;
    private List<Car> carList = Arrays.asList(new Car(), new Car());

    @Before
    public void setUp() {
        carOverviewManagedBean = new CarOverviewManagedBean();
        mockedCarBean = mock(CarBean.class);
        carOverviewManagedBean.setCarBean(mockedCarBean);
        carOverviewManagedBean.setCars(carList);

        assertThat(carOverviewManagedBean.getCarBean(), is(mockedCarBean));
        assertThat(carOverviewManagedBean.getCars(), is(carList));
    }

    @Test
    public void testInit() throws Exception {
        carOverviewManagedBean.init();
        assertThat(carOverviewManagedBean.getCars(), is(carList));
        carOverviewManagedBean.setCars(null);
        assertThat(carOverviewManagedBean.getCars(), is(nullValue()));
        carOverviewManagedBean.init();
        assertThat(carOverviewManagedBean.getCars().size(), is(0));
    }
}
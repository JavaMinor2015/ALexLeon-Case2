package javaminor.al.domain.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javaminor.al.entities.concrete.Car;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alex on 11/26/15.
 */
public class CarViewManagedBeanTest {

    private CarViewManagedBean carViewManagedBean;
    private Car testCar;
    private DateFormat dateFormat;

    @Before
    public void setUp() {
        carViewManagedBean = new CarViewManagedBean();
        testCar = new Car();
        carViewManagedBean.setCar(testCar);
        assertThat(carViewManagedBean.getCar(), is(testCar));
        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        carViewManagedBean.setFormat(dateFormat);
        assertThat(carViewManagedBean.getFormat(), is(dateFormat));
    }

    @Test
    public void testToCar() throws Exception {
        assertThat(carViewManagedBean.toCar(testCar), is("viewCarInfo.xhtml"));
    }

    @Test
    public void testToPresentableDate() throws Exception {
        Calendar testCal = Calendar.getInstance();
        String testString = dateFormat.format(testCal.getTime());
        assertThat(carViewManagedBean.toPresentableDate(testCal), is(testString));
    }
}
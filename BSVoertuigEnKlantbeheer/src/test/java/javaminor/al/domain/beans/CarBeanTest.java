package javaminor.al.domain.beans;

import java.util.Arrays;
import javaminor.al.entities.concrete.Car;
import javaminor.al.repository.CarRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by alex on 11/23/15.
 */
public class CarBeanTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CarRepository mockCarRepository;
    private CarBean carBean;

    @Before
    public void setUp() throws Exception {
        carBean = new CarBean();
        mockCarRepository = mock(CarRepository.class);
        carBean.setCarRepository(mockCarRepository);
        assertThat(carBean.getCarRepository(), is(mockCarRepository));
    }

    @Test
    public void testGetByPlate() throws Exception {
        when(mockCarRepository.findByPlate("AA")).thenReturn(new Car("AA", null, null, null, null));
        assertThat(carBean.getByPlate("AA").getNumberPlate(), is("AA"));

    }

    @Test
    public void testRefresh() throws Exception {
        doNothing().when(mockCarRepository).save(null);
        // no exceptions
        carBean.refresh(null);
    }

    @Test
    public void testAddCar() throws Exception {
        doNothing().when(mockCarRepository).save(any(Car.class));
        carBean.addCar(new Car());
    }

    @Test
    public void testGetAll() throws Exception {
        when(mockCarRepository.getAll()).thenReturn(Arrays.asList(new Car(), new Car()));
        assertThat(carBean.getAll().size(), is(2));
    }
}
package javaminor.al.repository;

import java.util.ArrayList;
import java.util.List;
import javaminor.al.entities.concrete.Driver;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Generated.
 *
 * @author Alex
 * @version %I%, %G%
 */
public class DriverRepositoryTest {

    private EntityManager mockManager;

    private Root<Driver> mockRoot;
    private List<Driver> driverList;

    private DriverRepository driverRepository;
    private CriteriaBuilder mockBuilder;
    private CriteriaQuery<Driver> mockCriteriaQuery;
    private TypedQuery mockTypedQuery;

    @Before
    public void setUp() {
        Driver testDriver = new Driver();


        driverList = new ArrayList<>();
        driverList.add(testDriver);

        mockRoot = mock(Root.class);
        mockManager = mock(EntityManager.class);
        mockBuilder = mock(CriteriaBuilder.class);
        mockCriteriaQuery = mock(CriteriaQuery.class);
        mockTypedQuery = mock(TypedQuery.class);
        driverRepository = new DriverRepository();
        driverRepository.setEm(mockManager);

        when(mockManager.getCriteriaBuilder()).thenReturn(mockBuilder);
        when(mockBuilder.createQuery(Driver.class)).thenReturn(mockCriteriaQuery);
        when(mockManager.createQuery(mockCriteriaQuery)).thenReturn(mockTypedQuery);
        when(mockTypedQuery.getResultList()).thenReturn(driverList);
        when(mockCriteriaQuery.from(Driver.class)).thenReturn(mockRoot);
    }

    @Test
    public void testGetAll() throws Exception {
        assertThat(driverRepository.getAll(), is(driverList));
    }

    @Test
    public void testGetById() throws Exception {
        when(mockTypedQuery.getSingleResult()).thenReturn(driverList.get(0));
        assertThat(driverRepository.getById(1), is(driverList.get(0)));
    }
}
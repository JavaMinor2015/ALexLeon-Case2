package javaminor.al.repository;

import java.util.ArrayList;
import java.util.List;
import javaminor.al.entities.concrete.MaintenanceAssignment;
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
 * Created by alex on 11/23/15.
 */
public class MaintenanceRepositoryTest {

    private EntityManager mockManager;

    private Root<MaintenanceAssignment> mockRoot;
    private List<MaintenanceAssignment> maintenanceAssignmentList;

    private MaintenanceRepository maintenanceRepository;
    private CriteriaBuilder mockBuilder;
    private CriteriaQuery<MaintenanceAssignment> mockCriteriaQuery;
    private TypedQuery mockTypedQuery;

    @Before
    public void setUp() {

        MaintenanceAssignment testAssignment = new MaintenanceAssignment();


        maintenanceAssignmentList = new ArrayList<>();
        maintenanceAssignmentList.add(testAssignment);

        mockRoot = mock(Root.class);
        mockManager = mock(EntityManager.class);
        mockBuilder = mock(CriteriaBuilder.class);
        mockCriteriaQuery = mock(CriteriaQuery.class);
        mockTypedQuery = mock(TypedQuery.class);
        maintenanceRepository = new MaintenanceRepository();
        maintenanceRepository.setEm(mockManager);

        when(mockManager.getCriteriaBuilder()).thenReturn(mockBuilder);
        when(mockBuilder.createQuery(MaintenanceAssignment.class)).thenReturn(mockCriteriaQuery);
        when(mockManager.createQuery(mockCriteriaQuery)).thenReturn(mockTypedQuery);
        when(mockTypedQuery.getResultList()).thenReturn(maintenanceAssignmentList);
        when(mockCriteriaQuery.from(MaintenanceAssignment.class)).thenReturn(mockRoot);
    }

    @Test
    public void testGetAll() throws Exception {
        assertThat(maintenanceRepository.getAll(), is(maintenanceAssignmentList));
    }
}
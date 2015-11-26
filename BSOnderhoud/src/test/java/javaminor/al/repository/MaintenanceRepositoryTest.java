package javaminor.al.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javaminor.al.business.MaintenanceStatus;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

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
    private Path mockPath;

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
        mockPath = mock(Path.class);

        when(mockRoot.get(Matchers.any(String.class))).thenReturn(mockPath);
        when(mockManager.getCriteriaBuilder()).thenReturn(mockBuilder);
        when(mockBuilder.createQuery(MaintenanceAssignment.class)).thenReturn(mockCriteriaQuery);
        when(mockManager.createQuery(mockCriteriaQuery)).thenReturn(mockTypedQuery);
        when(mockTypedQuery.getResultList()).thenReturn(maintenanceAssignmentList);
        when(mockCriteriaQuery.from(MaintenanceAssignment.class)).thenReturn(mockRoot);
        when(mockCriteriaQuery.where(Matchers.any(Expression.class))).thenReturn(mockCriteriaQuery);
    }

    @Test
    public void testGetAll() throws Exception {
        assertThat(maintenanceRepository.getAll(), is(maintenanceAssignmentList));
    }

    @Test
    public void testGetAllWithStatus() throws Exception {
        maintenanceAssignmentList.get(0).setStatus(MaintenanceStatus.IN_PROGRESS);
        when(mockTypedQuery.getResultList()).thenReturn(maintenanceAssignmentList);
        assertThat(maintenanceRepository.getAllWithStatus(MaintenanceStatus.IN_PROGRESS),
                is(maintenanceAssignmentList));

        maintenanceAssignmentList.get(0).setStatus(MaintenanceStatus.NEW);
        assertThat(maintenanceRepository.getAllWithStatus(), is(maintenanceAssignmentList));
    }

    @Test
    public void testFindById() throws Exception {
        when(mockTypedQuery.getSingleResult()).thenReturn(maintenanceAssignmentList.get(0));
        assertThat(maintenanceRepository.findById(1), is(Optional.of(maintenanceAssignmentList.get(0))));
    }

    @Test
    public void testFindByIdNotFound() throws Exception {
        when(mockTypedQuery.getSingleResult()).thenReturn(null);
        assertThat(maintenanceRepository.findById(1), is(Optional.empty()));
    }
}
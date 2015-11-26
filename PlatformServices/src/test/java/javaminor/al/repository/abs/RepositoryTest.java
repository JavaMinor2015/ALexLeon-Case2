package javaminor.al.repository.abs;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by alex on 11/22/15.
 */
public class RepositoryTest {
    // test coverage in sonarqube not being applied
    // correctly to this submodule, so here's yet another
    // repository test

    private EntityManager mockManager;

    private static final String TEST_STRING = "Help me, I'm trapped in a repository!";
    private Root<String> mockRoot;
    private List<String> stringList;

    private MockRepository mockRepository;
    private CriteriaBuilder mockBuilder;
    private CriteriaQuery<String> mockCriteriaQuery;
    private TypedQuery mockTypedQuery;

    @Before
    public void setUp() throws Exception {

        stringList = new ArrayList<>();
        stringList.add(TEST_STRING);

        mockRoot = mock(Root.class);
        mockManager = mock(EntityManager.class);
        mockBuilder = mock(CriteriaBuilder.class);
        mockCriteriaQuery = mock(CriteriaQuery.class);
        mockTypedQuery = mock(TypedQuery.class);
        mockRepository = new MockRepository();
        mockRepository.setEm(mockManager);

        assertThat(mockRepository.getEm(), is(mockManager));

        when(mockManager.getCriteriaBuilder()).thenReturn(mockBuilder);
        when(mockBuilder.createQuery(String.class)).thenReturn(mockCriteriaQuery);
        when(mockManager.createQuery(mockCriteriaQuery)).thenReturn(mockTypedQuery);
        when(mockTypedQuery.getResultList()).thenReturn(stringList);
        when(mockCriteriaQuery.from(String.class)).thenReturn(mockRoot);
    }

    @Test
    public void testGetAll() throws Exception {
        assertThat(mockRepository.getAll(), is(stringList));

        when(mockTypedQuery.getResultList()).thenThrow(NoResultException.class);
        assertThat(mockRepository.getAll().size(), is(0));
    }

    @Test
    public void testSave() throws Exception {
        // no exceptions
        mockRepository.save(null);
    }

    @Test
    public void testUpdate() throws Exception {
        // no exceptions
        mockRepository.update(null);
    }


    @Test
    public void testAdd() throws Exception {
        mockRepository.save(TEST_STRING);
        assertThat(mockRepository.getAll().get(0), is(TEST_STRING));
    }

    @Test
    public void testFindById() throws Exception {
        when(mockTypedQuery.getSingleResult()).thenReturn(stringList.get(0));
        assertThat(mockRepository.findById(String.class, 1L), is(stringList.get(0)));

        when(mockTypedQuery.getSingleResult()).thenThrow(NoResultException.class);
        assertThat(mockRepository.findById(String.class, 1L), is(nullValue()));
    }
}
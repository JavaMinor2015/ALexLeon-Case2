package javaminor.al.repository.abs;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by alex on 11/9/15.
 * <p>
 * An abstract repository which manages PersistentEntity's.
 *
 * @param <T> the generic type.
 */
@Getter
@Setter
public abstract class Repository<T> {

    @PersistenceContext(unitName = "GMSDerbyPersist")
    private EntityManager em;

    @Getter(AccessLevel.PROTECTED)
    private List<T> itemList = new ArrayList<>();

    /**
     * Retrieve all items.
     */
    public abstract List<T> getAll();

    /**
     * Persist all items in this repository.
     */
    public void save() {
        itemList.forEach(em::persist);
        em.flush();
    }

    /**
     * Update all items in this repository.
     */
    public void update() {
        itemList.forEach(em::merge);
        em.flush();
    }

    /**
     * Add an item to this repository.
     * <p>
     * Note: call {@link #save()} to persist added items.
     *
     * @param item the item to add.
     */
    public void add(final T item) {
        em.persist(item);
        itemList.clear();
        itemList.add(item);
    }

    /**
     * Retrieve all items from the persistence unit.
     *
     * @param clazz the class type of item
     * @return a list of items, empty if none found
     */
    protected List<T> getAll(final Class<T> clazz) {
        List<T> response;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> t = cq.from(clazz);
        cq.select(t);
        TypedQuery<T> q = em.createQuery(cq);
        response = q.getResultList();
        itemList = response;
        return response;
    }

    /**
     * Find an item by its id attribute.
     *
     * @param clazz    the item's class.
     * @param idToFind the id to find
     * @return the item, or null
     */
    protected T findById(final Class<T> clazz, final Long idToFind) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);

        cq.where(
                cb.equal(root.get("id"), idToFind)
        );
        TypedQuery<T> q = em.createQuery(cq);
        T response = q.getSingleResult();
        itemList.clear();
        itemList.add(response);
        return response;
    }
}

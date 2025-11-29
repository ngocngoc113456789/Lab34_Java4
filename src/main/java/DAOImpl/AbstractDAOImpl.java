package DAOImpl;

import DAO.AbstractDAO;
import Utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public abstract class AbstractDAOImpl<T> implements AbstractDAO<T> {

    private final Class<T> entityClass;

    // Lấy Class Entity cụ thể (T) thông qua Reflection
    @SuppressWarnings("unchecked")
    public AbstractDAOImpl() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    // --- Các Thao tác CRUD Cơ bản ---

    @Override
    public T insert(T entity) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity); // Thêm mới
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Lỗi khi thêm mới Entity: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public T update(T entity) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            T updatedEntity = em.merge(entity); // Cập nhật
            em.getTransaction().commit();
            return updatedEntity;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Lỗi khi cập nhật Entity: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public T delete(T entity) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            // Cần merge trước khi remove để đảm bảo entity đang ở trạng thái Managed
            T managedEntity = em.contains(entity) ? entity : em.merge(entity);
            em.remove(managedEntity);
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Lỗi khi xóa Entity: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public T findById(Object id) {
        EntityManager em = XJPA.getEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    // --- Các Thao tác Truy vấn ---

    @Override
    public List<T> findAll() {
        String queryStr = "SELECT o FROM " + entityClass.getSimpleName() + " o";
        return createQuery(queryStr).getResultList();
    }

    @Override
    public List<T> findAll(int page, int pageSize) {
        String queryStr = "SELECT o FROM " + entityClass.getSimpleName() + " o";
        return createQuery(queryStr)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    // --- Phương thức Hỗ trợ Truy vấn NamedQuery ---

    @Override
    public List<T> findByNamedQuery(String name, Object... params) {
        TypedQuery<T> query = createNamedQuery(name, params);
        return query.getResultList();
    }

    @Override
    public T findSingleByNamedQuery(String name, Object... params) {
        TypedQuery<T> query = createNamedQuery(name, params);
        try {
            return query.getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }

    @Override
    public Long countByNamedQuery(String name, Object... params) {
        EntityManager em = XJPA.getEntityManager();
        try {
            TypedQuery<Long> query = em.createNamedQuery(name, Long.class);
            setParameters(query, params);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    // --- Private Helper Methods ---

    private TypedQuery<T> createQuery(String queryStr) {
        EntityManager em = XJPA.getEntityManager();
        return em.createQuery(queryStr, entityClass);
    }

    protected TypedQuery<T> createNamedQuery(String name, Object... params) {
        EntityManager em = XJPA.getEntityManager();
        TypedQuery<T> query = em.createNamedQuery(name, entityClass);
        setParameters(query, params);
        return query;
    }

    // Thiết lập tham số cho Query
    private void setParameters(TypedQuery<?> query, Object... params) {
        if (params.length % 2 != 0) {
            throw new IllegalArgumentException("Tham số phải đi theo cặp (Tên, Giá trị).");
        }
        for (int i = 0; i < params.length; i += 2) {
            if (!(params[i] instanceof String)) {
                throw new IllegalArgumentException("Tên tham số phải là String.");
            }
            query.setParameter((String) params[i], params[i + 1]);
        }
    }
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> findByNamedQueryReturnMap(String namedQuery) {
        EntityManager em = XJPA.getEntityManager();
        try {
            return em.createNamedQuery(namedQuery).getResultList();
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> findByNamedQueryReturnMap(String namedQuery, Object... params) {
        EntityManager em = XJPA.getEntityManager();
        try {
            jakarta.persistence.Query query = em.createNamedQuery(namedQuery);
            setParameters((TypedQuery<?>) query, params);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
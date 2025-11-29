package DAOImpl;

import DAO.ShareDAO;
import Entity.Share;
import Utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ShareDAOImpl extends AbstractDAOImpl<Share> implements ShareDAO {

    @Override
    public List<Object[]> getVideoShareReport() {
        EntityManager em = XJPA.getEntityManager();
        try {
            String jpql = """
            SELECT v.title, COUNT(s), MIN(s.shareDate), MAX(s.shareDate)
            FROM Share s 
            JOIN s.video v 
            GROUP BY v.id, v.title
            ORDER BY COUNT(s) DESC
            """;
            return em.createQuery(jpql, Object[].class).getResultList();
        } finally {
            em.close();
        }
    }


    // Các phương thức còn lại (giữ nguyên logic, chỉ tối ưu nhẹ)
    @Override
    public List<Share> findSharedHistoryByUserId(String userId, int page, int pageSize) {
        TypedQuery<Share> query = createNamedQuery("Share.findByUser", "userId", userId);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<Share> findByUserId(String userId) {
        return super.findByNamedQuery("Share.findByUser", "userId", userId);
    }

    @Override
    public List<Share> findByVideoId(String videoId) {
        return super.findByNamedQuery("Share.findByVideo", "videoId", videoId);
    }

    @Override
    public List<Share> findAllShares() {
        return super.findByNamedQuery("Share.findAll");
    }

    @Override
    public Long countByUserId(String userId) {
        EntityManager em = XJPA.getEntityManager();
        try {
            String jpql = "SELECT COUNT(s) FROM Share s WHERE s.user.id = :userId";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("userId", userId);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
}
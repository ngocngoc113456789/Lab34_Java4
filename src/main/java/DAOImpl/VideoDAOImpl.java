package DAOImpl;

import DAO.VideoDAO;
import Entity.Video;
import Utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class VideoDAOImpl extends AbstractDAOImpl<Video> implements VideoDAO {

    @Override
    public List<Video> findActiveVideos(int page, int pageSize) {
        TypedQuery<Video> query = createNamedQuery("Video.findAllActive");
        return query.setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public List<Video> findByKeyword(String keyword, int page, int pageSize) {
        TypedQuery<Video> query = createNamedQuery("Video.findByKeyword",
                "keyword", "%" + keyword + "%");
        return query.setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public List<Video> findRandomVideos(int limit) {
        EntityManager em = XJPA.getEntityManager();
        try {
            String sql = "SELECT * FROM Video WHERE Active = 1 ORDER BY NEWID()";
            return em.createNativeQuery(sql, Video.class)
                    .setMaxResults(limit)
                    .getResultList();
        } finally {
            em.close();
        }
    }


    @Override
    public List<Video> searchByTitle(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return List.of(); // An toàn tuyệt đối
        }

        String jpql = "SELECT v FROM Video v WHERE LOWER(v.title) LIKE LOWER(:keyword) ORDER BY v.title";
        String search = "%" + keyword.trim() + "%";

        EntityManager em = XJPA.getEntityManager();
        try {
            return em.createQuery(jpql, Video.class)
                    .setParameter("keyword", search)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
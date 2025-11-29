package DAOImpl;

import DAO.FavoriteDAO;
import Entity.Favorite;
import java.util.List;
import jakarta.persistence.TypedQuery;

public class FavoriteDAOImpl extends AbstractDAOImpl<Favorite> implements FavoriteDAO {

    @Override
    public List<Favorite> findByUserId(String userId) {
        return super.findByNamedQuery("Favorite.findByUserId", "userId", userId);
    }

    @Override
    public Favorite findByUserAndVideo(String userId, String videoId) {
        return findSingleByNamedQuery("Favorite.findByUserAndVideo",
                "userId", userId,
                "videoId", videoId);
    }

    @Override
    public List<Favorite> findFavoritesByUserId(String userId, int page, int pageSize) {
        // Sửa tên NamedQuery cho đúng
        TypedQuery<Favorite> query = createNamedQuery("Favorite.findByUserId", "userId", userId);
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public Long countFavoritesByVideoId(String videoId) {
        return countByNamedQuery("Favorite.countByVideoId", "videoId", videoId);
    }

    // Cách tốt nhất: dùng NamedQuery + JOIN FETCH
    public List<Favorite> findByUserIdWithVideo(String userId) {
        return super.findByNamedQuery("Favorite.findByUserIdWithVideo", "userId", userId);
    }
}
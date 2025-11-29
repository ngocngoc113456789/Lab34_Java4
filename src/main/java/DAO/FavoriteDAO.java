package DAO;

import Entity.Favorite;
import java.util.List;

public interface FavoriteDAO extends AbstractDAO<Favorite> {

    // Phương thức chuyên biệt cho Favorite
    Favorite findByUserAndVideo(String userId, String videoId);

    List<Favorite> findFavoritesByUserId(String userId, int page, int pageSize);

    // Dùng cho báo cáo
    Long countFavoritesByVideoId(String videoId);
    public List<Favorite> findByUserId(String userId);
}
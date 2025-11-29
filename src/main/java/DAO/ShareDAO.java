package DAO;

import Entity.Share;
import java.util.List;
import java.util.Map;

public interface ShareDAO extends AbstractDAO<Share> {

    List<Object[]> getVideoShareReport();  // ← Đổi thành Object[] thay vì Map

    List<Share> findSharedHistoryByUserId(String userId, int page, int pageSize);
    List<Share> findByUserId(String userId);
    List<Share> findByVideoId(String videoId);
    List<Share> findAllShares();
    Long countByUserId(String userId);
}
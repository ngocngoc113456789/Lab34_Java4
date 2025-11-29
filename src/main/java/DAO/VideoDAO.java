package DAO;

import Entity.Video;
import java.util.List;

public interface VideoDAO extends AbstractDAO<Video> {

    // Phương thức chuyên biệt cho Video
    List<Video> findActiveVideos(int page, int pageSize);

    List<Video> findByKeyword(String keyword, int page, int pageSize);

    List<Video> findRandomVideos(int limit);
    public List<Video> searchByTitle(String keyword);
}
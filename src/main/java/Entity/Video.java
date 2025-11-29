package Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Video")
@NamedQueries({
        @NamedQuery(name = "Video.findAllActive",
                query = "SELECT v FROM Video v WHERE v.active = true ORDER BY v.views DESC"),

        @NamedQuery(name = "Video.findByKeyword",
                query = "SELECT v FROM Video v WHERE (v.title LIKE :keyword OR v.description LIKE :keyword) AND v.active = true"),

        @NamedQuery(name = "Video.findAll",
                query = "SELECT v FROM Video v ORDER BY v.id DESC"),
        @NamedQuery(name = "Video.countActive",
                query = "SELECT COUNT(v) FROM Video v WHERE v.active = true")
})
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Id", length = 20, nullable = false)
    private String id;

    @Column(name = "Title", length = 255, nullable = false)
    private String title;

    @Column(name = "Poster", length = 255, nullable = false)
    private String poster;

    @Column(name = "Views", nullable = false)
    private int views = 0;                    // primitive int + default 0

    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "Active", nullable = false)
    private boolean active = true;            // primitive boolean + default true

    // --- Quan hệ One-to-Many ---
    @OneToMany(mappedBy = "video", fetch = FetchType.LAZY)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "video", fetch = FetchType.LAZY)
    private List<Share> shares;
    // ===== SIÊU PHẨM: ĐẾM LƯỢT THÍCH MÀ KHÔNG BỊ LỖI LAZY =====
    @Formula("(SELECT COUNT(*) FROM Favorite f WHERE f.VideoId = id)")
    private Integer favoriteCount;

    // Getter bắt buộc (Hibernate sẽ tự tính)
    public Integer getFavoriteCount() {
        return favoriteCount != null ? favoriteCount : 0;
    }
    // ===== KẾT THÚC =====

    // Constructors
    public Video() {}

    public Video(String id, String title, String poster) {
        this.id = id;
        this.title = title;
        this.poster = poster;
    }

    public Video(String id, String title, String poster, String description, boolean active) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.description = description;
        this.active = active;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }

    public int getViews() { return views; }
    public void setViews(int views) { this.views = views; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public List<Favorite> getFavorites() { return favorites; }
    public void setFavorites(List<Favorite> favorites) { this.favorites = favorites; }

    public List<Share> getShares() { return shares; }
    public void setShares(List<Share> shares) { this.shares = shares; }

    // Tăng lượt xem (tiện dụng)
    public void increaseViews() {
        this.views++;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", views=" + views +
                ", active=" + active +
                '}';
    }
}
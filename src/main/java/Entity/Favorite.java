package Entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Favorite",
        uniqueConstraints = @UniqueConstraint(name = "UK_Favorite_UserVideo", columnNames = {"UserId", "VideoId"}))
@NamedQueries({
        @NamedQuery(name = "Favorite.findByUserIdWithVideo",
                query = "SELECT f FROM Favorite f JOIN FETCH f.video v WHERE f.user.id = :userId ORDER BY f.likeDate DESC"),
        @NamedQuery(name = "Favorite.findByUserId",
                        query = "SELECT f FROM Favorite f WHERE f.user.id = :userId ORDER BY f.likeDate DESC"),

        @NamedQuery(name = "Favorite.findByUserAndVideo",
                query = "SELECT f FROM Favorite f WHERE f.user.id = :userId AND f.video.id = :videoId"),

        @NamedQuery(name = "Favorite.findByUser",
                query = "SELECT f FROM Favorite f WHERE f.user.id = :userId ORDER BY f.likeDate DESC"),

        // Báo cáo: Top video được thích nhiều nhất (sẽ dùng sau)
        @NamedQuery(name = "Favorite.reportByVideo",
                query = "SELECT f.video.id, f.video.title, COUNT(f), MAX(f.likeDate), MIN(f.likeDate) " +
                        "FROM Favorite f GROUP BY f.video.id, f.video.title " +
                        "ORDER BY COUNT(f) DESC")
})
public class Favorite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", referencedColumnName = "Id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VideoId", referencedColumnName = "Id", nullable = false)
    private Video video;

    @Column(name = "LikeDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date likeDate;

    // Constructors
    public Favorite() {
        this.likeDate = new Date();
    }

    public Favorite(User user, Video video) {
        this.user = user;
        this.video = video;
        this.likeDate = new Date();
    }

    public Favorite(User user, Video video, Date likeDate) {
        this.user = user;
        this.video = video;
        this.likeDate = likeDate;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Video getVideo() { return video; }
    public void setVideo(Video video) { this.video = video; }

    public Date getLikeDate() { return likeDate; }
    public void setLikeDate(Date likeDate) { this.likeDate = likeDate; }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", user=" + (user != null ? user.getId() : null) +
                ", video=" + (video != null ? video.getId() : null) +
                ", likeDate=" + likeDate +
                '}';
    }
}
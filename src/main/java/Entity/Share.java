package Entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Share")
@NamedQueries({

        @NamedQuery(name = "Share.findAll",
                query = "SELECT s FROM Share s ORDER BY s.shareDate DESC"),

        @NamedQuery(name = "Share.findByUser",
                query = "SELECT s FROM Share s WHERE s.user.id = :userId ORDER BY s.shareDate DESC"),

        @NamedQuery(name = "Share.findByVideo",
                query = "SELECT s FROM Share s WHERE s.video.id = :videoId ORDER BY s.shareDate DESC")
})
public class Share implements Serializable {

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

    @Column(name = "Emails", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String emails;

    @Column(name = "ShareDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date shareDate;

    // Constructors
    public Share() {
        this.shareDate = new Date();
    }

    public Share(User user, Video video, String emails) {
        this.user = user;
        this.video = video;
        this.emails = emails;
        this.shareDate = new Date();
    }

    public Share(User user, Video video, String emails, Date shareDate) {
        this.user = user;
        this.video = video;
        this.emails = emails;
        this.shareDate = shareDate;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Video getVideo() { return video; }
    public void setVideo(Video video) { this.video = video; }

    public String getEmails() { return emails; }
    public void setEmails(String emails) { this.emails = emails; }

    public Date getShareDate() { return shareDate; }
    public void setShareDate(Date shareDate) { this.shareDate = shareDate; }

    @Override
    public String toString() {
        return "Share{" +
                "id=" + id +
                ", user=" + (user != null ? user.getId() : null) +
                ", video=" + (video != null ? video.getId() : null) +
                ", emails='" + emails + '\'' +
                ", shareDate=" + shareDate +
                '}';
    }
}
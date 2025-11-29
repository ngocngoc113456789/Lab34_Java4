package Entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Users")
@NamedQueries({
        @NamedQuery(name = "User.findByIdOrEmail",
                query = "SELECT u FROM User u WHERE u.id = :input OR u.email = :input"),
        @NamedQuery(name = "User.findAll",
                query = "SELECT u FROM User u"),

        @NamedQuery(name = "User.findByEmail",
                query = "SELECT u FROM User u WHERE u.email = :email"),

        @NamedQuery(name = "User.findByEmailAndPassword",
                query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Id", length = 20, nullable = false)
    private String id;                          // Phải là String, không auto-generated

    @Column(name = "Password", length = 50, nullable = false)
    private String password;

    @Column(name = "Fullname", length = 50, nullable = false)
    private String fullname;

    @Column(name = "Email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "Admin", nullable = false)
    private Boolean admin = false;              // Tên cột đúng là "Admin" trong DB

    // Constructors
    public User() {}

    public User(String id, String password, String fullname, String email, Boolean admin) {
        this.id = id;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.admin = admin;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Boolean getAdmin() { return admin; }
    public void setAdmin(Boolean admin) { this.admin = admin; }

    // Tiện cho JSP/Thymeleaf
    public boolean isAdmin() {
        return Boolean.TRUE.equals(admin);
    }

    public String getRole() {
        return isAdmin() ? "admin" : "user";
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                ", admin=" + admin +
                '}';
    }
}
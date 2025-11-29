package DAO;

import Entity.User;
import java.util.List;

public interface UserDAO extends AbstractDAO<User> {

    // Phương thức chuyên biệt cho User
    User findByEmail(String email);

    User login(String email, String password);
    User findByIdOrEmail(String input);
}
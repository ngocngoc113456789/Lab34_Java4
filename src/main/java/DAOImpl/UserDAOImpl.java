package DAOImpl;

import DAO.UserDAO;
import Entity.User;

public class UserDAOImpl extends AbstractDAOImpl<User> implements UserDAO {

    @Override
    public User findByEmail(String email) {
        return findSingleByNamedQuery("User.findByEmail", "email", email);
    }

    @Override
    public User login(String email, String password) {
        return findSingleByNamedQuery("User.findByEmailAndPassword",
                "email", email,
                "password", password);
    }
    public User findByIdOrEmail(String input) {
        if (input == null || input.trim().isEmpty()) return null;
        return super.findSingleByNamedQuery("User.findByIdOrEmail", "input", input);
    }
}
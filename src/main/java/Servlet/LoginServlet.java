package Servlet;

import DAO.UserDAO;
import DAOImpl.UserDAOImpl;
import Entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Hiển thị form đăng nhập
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idOrEmail = request.getParameter("idOrEmail");
        String password = request.getParameter("password");

        // Tìm user bằng Id hoặc Email
        User user = userDAO.findByIdOrEmail(idOrEmail);

        if (user == null || !user.getPassword().equals(password)) {
            // Sai tài khoản hoặc mật khẩu
            request.setAttribute("error", "Sai tên đăng nhập/Email hoặc mật khẩu!");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
            return;
        }

        // ĐĂNG NHẬP THÀNH CÔNG
        HttpSession session = request.getSession();
        session.setAttribute("currentUser", user);  // ← QUAN TRỌNG NHẤT: LƯU USER VÀO SESSION
        session.setAttribute("userId", user.getId()); // (nếu bạn dùng ở nơi khác)

        // Chuyển hướng về trang chủ hoặc trang yêu thích
        response.sendRedirect(request.getContextPath() + "/favorites");
        // Nếu muốn về trang chủ thì dùng: "/home"
    }
}
package Servlet;

import DAOImpl.FavoriteDAOImpl;
import Entity.Favorite;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

    @WebServlet("/favorite-list")
public class FavoriteListServlet extends HttpServlet {

    private final FavoriteDAOImpl favoriteDAO = new FavoriteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Lấy toàn bộ danh sách Favorite (kèm Video và User)
            List<Favorite> favorites = favoriteDAO.findAll(); // Đã có ORDER BY likeDate DESC trong NamedQuery

            // Đưa vào request
            request.setAttribute("favorites", favorites);

            // Forward đến JSP
            request.getRequestDispatcher("/views/favorite-list.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi tải dữ liệu: " + e.getMessage());
            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
        }
    }
}
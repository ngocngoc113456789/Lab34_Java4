package Servlet;

import Entity.User;
import Entity.Favorite;
import Entity.Video;
import DAOImpl.FavoriteDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Hibernate;

import java.io.IOException;
import java.util.List;

@WebServlet("/favorites")
public class FavoriteServlet extends HttpServlet {

    private final FavoriteDAOImpl favoriteDAO = new FavoriteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // BẮT BUỘC ĐĂNG NHẬP
        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            // LẤY DANH SÁCH YÊU THÍCH + ÉP LOAD SỚM VIDEO + POSTER TRƯỚC KHI SESSION ĐÓNG
            List<Favorite> favorites = favoriteDAO.findByUserIdWithVideo(currentUser.getId());

            // CÁCH DỰ PHÒNG: Nếu DAO chưa có JOIN FETCH, thì ép load ở đây
            if (favorites != null) {
                favorites.forEach(fav -> {
                    Video video = fav.getVideo();
                    if (video != null) {
                        Hibernate.initialize(video);                    // Load Video
                        Hibernate.initialize(video.getPoster());        // Load poster (String nên an toàn)
                        Hibernate.initialize(video.getTitle());         // Đảm bảo tất cả field cần dùng
                    }
                });
            }

            // Đưa dữ liệu vào request
            request.setAttribute("favorites", favorites);
            request.setAttribute("user", currentUser);

            // Forward đến JSP
            request.getRequestDispatcher("/views/favorite.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
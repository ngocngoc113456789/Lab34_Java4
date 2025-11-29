package Servlet;

import DAO.ShareDAO;
import DAOImpl.ShareDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/share-report")
public class ShareReportServlet extends HttpServlet {
    private ShareDAO shareDAO = new ShareDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Object[]> report = shareDAO.getVideoShareReport();
        request.setAttribute("report", report);
        request.getRequestDispatcher("/views/shareReport.jsp").forward(request, response);
    }
}
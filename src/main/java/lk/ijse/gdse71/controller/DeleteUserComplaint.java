package lk.ijse.gdse71.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * --------------------------------------------
 * Author: Zeenathul Ilma
 * GitHub: https://github.com/Seenathul-Ilma
 * Website: https://zeenathulilma.vercel.app/
 * --------------------------------------------
 * Created: 6/18/2025 4:23 PM
 * Project: CMS
 * --------------------------------------------
 **/

@WebServlet("/api/v1/delete/user/complaint")
public class DeleteUserComplaint extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null) {
            //resp.sendRedirect("login.jsp");
            resp.sendRedirect(resp.encodeRedirectURL( req.getContextPath() + "/login.jsp"));
            return;
        }

        Object success = session.getAttribute("flash_success");
        if (success != null) {
            req.setAttribute("success", success);
            session.removeAttribute("flash_success");
        }

        Object error = session.getAttribute("flash_error");
        if (error != null) {
            req.setAttribute("error", error);
            session.removeAttribute("flash_error");
        }

        req.getRequestDispatcher("/editUserComplaint.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String userId = (String) session.getAttribute("user_id");

        if (userId == null) {
            resp.sendRedirect(resp.encodeRedirectURL( req.getContextPath() + "/login.jsp"));
            return;
        }

        String complaintId = req.getParameter("complaint_id");

        if (complaintId == null || complaintId.isEmpty()) {
            session.setAttribute("flash_error", "No complaint selected for deletion");
            //req.getRequestDispatcher("/viewComplaint.jsp").forward(req, resp);
            return;
        }

        ServletContext servletContext = req.getServletContext();
        BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dataSource");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "DELETE FROM complaint WHERE complaint_id = ? AND user_id = ?"
             )) {

            stmt.setString(1, complaintId);
            stmt.setString(2, userId);

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                session.setAttribute("flash_success", "Complaint deleted Successfully");
                resp.sendRedirect(req.getContextPath() + "/api/v1/update/complaint");

            } else {
                session.setAttribute("flash_error", "Failed to delete complaint. Please try again.");
                resp.sendRedirect(req.getContextPath() + "/api/v1/update/complaint");
                resp.getWriter().write("Failed to delete complaint. Please try again.");
            }
        } catch (SQLException e) {
            session.setAttribute("flash_error", "Something went wrong..!");
            resp.sendRedirect(req.getContextPath() + "/api/v1/update/complaint");
            throw new RuntimeException(e);
        }

    }
}

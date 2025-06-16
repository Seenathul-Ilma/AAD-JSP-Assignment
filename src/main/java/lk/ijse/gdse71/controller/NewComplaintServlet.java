package lk.ijse.gdse71.controller;

import com.mysql.cj.protocol.x.Notice;
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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.UUID;

/**
 * --------------------------------------------
 * Author: Zeenathul Ilma
 * GitHub: https://github.com/Seenathul-Ilma
 * Website: https://zeenathulilma.vercel.app/
 * --------------------------------------------
 * Created: 6/16/2025 8:43 AM
 * Project: CMS
 * --------------------------------------------
 **/

@WebServlet("/api/v1/new/complaint")
public class NewComplaintServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String desc = req.getParameter("description");
        String admin_remark = "";
        String status = "Unresolved";

        HttpSession session = req.getSession(false);
        String userId = (String) session.getAttribute("user_id");

        if (userId == null) {
            resp.sendRedirect("login.jsp");
            resp.sendRedirect(resp.encodeRedirectURL( req.getContextPath() + "/login.jsp"));
            return;
        }

        try {
            ServletContext servletContext = req.getServletContext();
            BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dataSource");

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(
                         "INSERT INTO complaint (complaint_id, user_id, title, description, date_submitted, status, admin_remarks) VALUES (?, ?, ?, ?, ?, ?, ?)"
                 )) {

                stmt.setString(1, UUID.randomUUID().toString());
                stmt.setString(2, userId);
                stmt.setString(3, title);
                stmt.setString(4, desc);
                stmt.setDate(5, Date.valueOf(LocalDate.now()));
                stmt.setString(6, status);
                stmt.setString(7, admin_remark); // Corrected index to 7

                int executed = stmt.executeUpdate();
                if (executed > 0) {
                    String userName = session.getAttribute("name").toString();
                    String userEmail = session.getAttribute("email").toString();
                    String userRole = session.getAttribute("role").toString();

                    System.out.println("\n=== SESSION DETAILS ===");
                    System.out.println("Session ID: " + session.getId());
                    System.out.println("Is New Session: " + session.isNew());

                    System.out.println("\n=== Session Attributes  (When Add New Complaint) ===");
                    System.out.println("User Name In Session: " + userName);
                    System.out.println("User Email In Session: " + userEmail);
                    System.out.println("User Role In Session: " + userRole);

                } else {
                    resp.getWriter().write("Complaint submission failed.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Error: " + e.getMessage());
        }
    }

}

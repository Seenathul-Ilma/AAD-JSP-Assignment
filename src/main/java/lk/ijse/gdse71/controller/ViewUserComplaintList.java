package lk.ijse.gdse71.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.gdse71.model.UserComplaintList;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * --------------------------------------------
 * Author: Zeenathul Ilma
 * GitHub: https://github.com/Seenathul-Ilma
 * Website: https://zeenathulilma.vercel.app/
 * --------------------------------------------
 * Created: 6/17/2025 10:34 PM
 * Project: CMS
 * --------------------------------------------
 **/

@WebServlet("/api/v1/user/complaint/list")
public class ViewUserComplaintList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null) {
            //resp.sendRedirect("login.jsp");
            resp.sendRedirect(resp.encodeRedirectURL( req.getContextPath() + "/login.jsp"));
            return;
        }

        String userId = (String) session.getAttribute("user_id");
        System.out.println("The user Id is: "+ userId);

        try {
            ServletContext servletContext = req.getServletContext();
            BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dataSource");

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(
                         "SELECT title, description, date_submitted, status, admin_remarks FROM complaint WHERE user_id = ?"
                 )) {
                stmt.setString(1, userId);
                ResultSet rs = stmt.executeQuery();

                // Build a list of complaint
                List<UserComplaintList> userComplaintList = new ArrayList<>();

                while (rs.next()) {
                    UserComplaintList userComplaint = new UserComplaintList();
                    userComplaint.setTitle(rs.getString("title"));
                    userComplaint.setDescription(rs.getString("description"));
                    userComplaint.setDate_submitted(rs.getDate("date_submitted"));
                    userComplaint.setStatus(rs.getString("status"));
                    userComplaint.setAdmin_remarks(rs.getString("admin_remarks"));

                    userComplaintList.add(userComplaint);
                }

                req.setAttribute("userComplaintList", userComplaintList);
                req.getRequestDispatcher("/viewComplaint.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "Failed load your complaint list.");
            e.printStackTrace();
        }

    }
}

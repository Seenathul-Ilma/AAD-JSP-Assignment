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
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

/**
 * --------------------------------------------
 * Author: Zeenathul Ilma
 * GitHub: https://github.com/Seenathul-Ilma
 * Website: https://zeenathulilma.vercel.app/
 * --------------------------------------------
 * Created: 6/13/2025 2:30 PM
 * Project: CMS
 * --------------------------------------------
 **/

@WebServlet("/api/v1/signin")
public class LogInServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        //String role = req.getParameter("role");

        try {
            ServletContext servletContext = req.getServletContext();
            BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dataSource");

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(
                         "SELECT * FROM user WHERE email=? AND password=?"
                 )) {

                stmt.setString(1, email);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    HttpSession session = req.getSession(true);

                    session.setAttribute("user_id", rs.getString("user_id"));
                    session.setAttribute("name", rs.getString("name"));
                    session.setAttribute("email", rs.getString("email"));
                    session.setAttribute("role", rs.getString("role"));

                    // Redirect by role
                    String role = rs.getString("role").toLowerCase().trim();
                    String redirectPath = req.getContextPath() +
                            ("admin".equals(role) ? "/adminDashboard.jsp" : "/employeeDashboard.jsp");

                    resp.sendRedirect(resp.encodeRedirectURL(redirectPath));
                    //resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
                } else {
                    req.setAttribute("error", "Invalid credentials");
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred during log in.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }


    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        System.out.println("=== LOGIN ATTEMPT ===");
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        try {
            ServletContext servletContext = req.getServletContext();
            BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dataSource");

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(
                         "SELECT * FROM user WHERE email=? AND password=?"
                 )) {

                stmt.setString(1, email);
                stmt.setString(2, password);

                System.out.println("Executing query: " + stmt.toString());

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // Debug database results
                    System.out.println("\n=== DATABASE RESULTS ===");
                    System.out.println("user_id: " + rs.getString("user_id"));
                    System.out.println("name: " + rs.getString("name"));
                    System.out.println("email: " + rs.getString("email"));
                    System.out.println("role: " + rs.getString("role"));

                    // Session handling
                    HttpSession session = req.getSession();
                    System.out.println("\n=== SESSION DETAILS ===");
                    System.out.println("Session ID: " + session.getId());
                    System.out.println("Is New Session: " + session.isNew());

                    // Set session attributes
                    session.setAttribute("user_id", rs.getString("user_id"));
                    session.setAttribute("name", rs.getString("name"));
                    session.setAttribute("email", rs.getString("email"));
                    session.setAttribute("role", rs.getString("role"));

                    // Verify session attributes
                    System.out.println("\n=== SESSION ATTRIBUTES ===");
                    System.out.println("user_id in session: " + session.getAttribute("user_id"));
                    System.out.println("name in session: " + session.getAttribute("name"));
                    System.out.println("email in session: " + session.getAttribute("email"));
                    System.out.println("role in session: " + session.getAttribute("role"));

                    // Redirect based on role
                    String role = rs.getString("role").toLowerCase().trim();
                    String redirectPath = req.getContextPath() +
                            ("admin".equals(role) ? "/adminDashboard.jsp" : "/employeeDashboard.jsp");

                    System.out.println("\n=== REDIRECTING ===");
                    System.out.println("Role: " + role);
                    System.out.println("Redirecting to: " + redirectPath);

                    resp.sendRedirect(resp.encodeRedirectURL(redirectPath));
                } else {
                    System.out.println("\n=== LOGIN FAILED ===");
                    System.out.println("No matching user found");
                    req.setAttribute("error", "Invalid credentials");
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                }
            }
        } catch (Exception e) {
            System.out.println("\n=== EXCEPTION ===");
            e.printStackTrace();
            req.setAttribute("error", "An error occurred during log in: " + e.getMessage());
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
*/
}

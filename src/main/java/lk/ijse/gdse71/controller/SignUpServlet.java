package lk.ijse.gdse71.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.UUID;

/**
 * --------------------------------------------
 * Author: Zeenathul Ilma
 * GitHub: https://github.com/Seenathul-Ilma
 * Website: https://zeenathulilma.vercel.app/
 * --------------------------------------------
 * Created: 6/14/2025 5:19 PM
 * Project: CMS
 * --------------------------------------------
 **/

@WebServlet("/api/v1/signup")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        try {
            ServletContext servletContext = req.getServletContext();
            BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dataSource");

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(
                         "INSERT INTO user (user_id, name, email, password, role) VALUES (?, ?, ?, ?, ?)"
                 )) {

                stmt.setString(1, UUID.randomUUID().toString());
                stmt.setString(2, name);
                stmt.setString(3, email);
                stmt.setString(4, password);
                stmt.setString(5, role);

                int executed = stmt.executeUpdate();

                if (executed > 0) {
                    resp.sendRedirect(req.getContextPath() + "/login.jsp");
                } else {
                    req.setAttribute("error", "Failed to sign up..!");
                    req.getRequestDispatcher("/signup.jsp").forward(req, resp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred during registration");
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
        }
    }

}

package lk.ijse.gdse71.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.gdse71.dto.UserDTO;
import lk.ijse.gdse71.model.UserModel;
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

        ServletContext servletContext = req.getServletContext();
        BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dataSource");

        UserModel userModel = new UserModel(dataSource);

        try {

            UserDTO userDTO = userModel.getUserByCredentials(email, password);

            if (userDTO != null) {
                System.out.println("User Found!");

                HttpSession session = req.getSession(true);
                session.setAttribute("user_id", userDTO.getUserId());
                session.setAttribute("name", userDTO.getName());
                session.setAttribute("email", userDTO.getEmail());
                session.setAttribute("role", userDTO.getRole());

                String redirectPath = req.getContextPath() +
                        (userDTO.getRole().equalsIgnoreCase("admin") ? "/adminDashboard.jsp" : "/employeeDashboard.jsp");
                resp.sendRedirect(resp.encodeRedirectURL(redirectPath));
            } else {
                req.setAttribute("error", "Invalid credentials");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred during log in.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }

}

package lk.ijse.gdse71.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * --------------------------------------------
 * Author: Zeenathul Ilma
 * GitHub: https://github.com/Seenathul-Ilma
 * Website: https://zeenathulilma.vercel.app/
 * --------------------------------------------
 * Created: 6/13/2025 10:20 PM
 * Project: CMS
 * --------------------------------------------
 **/

@WebServlet("/api/v1/employee")
public class EmployeeDashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null) {
            //resp.sendRedirect("login.jsp");
            resp.sendRedirect(resp.encodeRedirectURL( req.getContextPath() + "/login.jsp"));
            return;
        }
    }
}

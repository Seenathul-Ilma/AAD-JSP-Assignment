package lk.ijse.gdse71.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.gdse71.model.ComplaintStatsModel;
import org.apache.commons.dbcp2.BasicDataSource;

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

        if (session == null || session.getAttribute("user_id") == null) {
            //resp.sendRedirect("login.jsp");
            resp.sendRedirect(resp.encodeRedirectURL( req.getContextPath() + "/login.jsp"));
            return;
        }

        String userId = session.getAttribute("user_id").toString();
        System.out.println("The user Id is: "+ userId);

        ServletContext context = req.getServletContext();
        BasicDataSource dataSource = (BasicDataSource) context.getAttribute("dataSource");

        ComplaintStatsModel model = new ComplaintStatsModel(dataSource);

        try {
            int total = model.getTotalComplaints(userId);
            System.out.println(total);
            req.setAttribute("totalComplaints", total);

            int resolved = model.getResolvedComplaints(userId);
            req.setAttribute("resolvedComplaints", resolved);

            int pending = model.getPendingComplaints(userId);
            req.setAttribute("pendingComplaints", pending);

            req.getRequestDispatcher(req.getContextPath()+"/employeeDashboard.jsp").forward(req, resp);
            //req.getRequestDispatcher("/employeeDashboard.jsp").forward(req, resp);
            //resp.sendRedirect(req.getContextPath() + "/api/v1/employee");


        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Unable to load complaint stats.");

            req.getRequestDispatcher(req.getContextPath()+"/employeeDashboard.jsp").forward(req, resp);
            //req.getRequestDispatcher("/employeeDashboard.jsp").forward(req, resp);
            //req.getRequestDispatcher("/api/v1/employee").forward(req, resp);
        }

    }


}

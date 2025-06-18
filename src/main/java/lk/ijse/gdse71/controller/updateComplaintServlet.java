package lk.ijse.gdse71.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.gdse71.dto.ComplaintDTO;
import lk.ijse.gdse71.model.ComplaintModel;
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
 * Created: 6/18/2025 10:54 AM
 * Project: CMS
 * --------------------------------------------
 **/

@WebServlet("/api/v1/update/complaint")
public class updateComplaintServlet extends HttpServlet {

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

        ServletContext servletContext = req.getServletContext();
        BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dataSource");

        ComplaintModel complaintModel = new ComplaintModel(dataSource);

        try {
            List<ComplaintDTO> complaintDTOS = complaintModel.getAllUnresolvedComplaintsByUser(userId, "Unresolved");
            req.setAttribute("complaintDTOS", complaintDTOS);

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

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Failed load your unresolved complaint list.");
            req.getRequestDispatcher("/editUserComplaint.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        String userId = (String) session.getAttribute("user_id");

        if (userId == null) {
            //resp.sendRedirect("login.jsp");
            resp.sendRedirect(resp.encodeRedirectURL( req.getContextPath() + "/login.jsp"));
            return;
        }

        String title = req.getParameter("title");
        String desc = req.getParameter("description");
        //String admin_remark = "";
        //String status = "Unresolved";
        String complaintId = req.getParameter("complaint_id");
        //String date = req.getParameter("date_submitted");

        if (complaintId == null || complaintId.isEmpty()) {
            session.setAttribute("flash_error", "No complaint selected for update");
            //req.getRequestDispatcher("/viewComplaint.jsp").forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/api/v1/update/complaint");
            return;
        }

        try {
            ServletContext servletContext = req.getServletContext();
            BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dataSource");

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(
                         "UPDATE complaint SET title = ?, description = ? WHERE complaint_id = ? AND user_id = ?"
                 )) {

                stmt.setString(1, title);
                stmt.setString(2, desc);
                stmt.setString(3, complaintId);
                stmt.setString(4, userId);

                int executed = stmt.executeUpdate();
                if (executed > 0) {
                    //req.setAttribute("success", "Complaint updated Successfully");

                    //req.getRequestDispatcher("/editUserComplaint.jsp").forward(req, resp);
                    //resp.sendRedirect(req.getContextPath() + "/api/v1/update/complaint");   // - form cleared, but lost success msg

                    session.setAttribute("flash_success", "Complaint updated Successfully");
                    resp.sendRedirect(req.getContextPath() + "/api/v1/update/complaint");

                } else {
                    //req.setAttribute("error", "Failed to submit complaint. Please try again.");
                    //req.getRequestDispatcher("/editUserComplaint.jsp").forward(req, resp);
                    session.setAttribute("flash_error", "Failed to update complaint. Please try again.");
                    resp.sendRedirect(req.getContextPath() + "/api/v1/update/complaint");
                    resp.getWriter().write("Failed to update complaint. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //req.setAttribute("error", "An error occurred: " + e.getMessage());
            //req.getRequestDispatcher("/editUserComplaint.jsp").forward(req, resp);
            session.setAttribute("flash_error", "Something went wrong..!");
            resp.sendRedirect(req.getContextPath() + "/api/v1/update/complaint");
        }
    }
}

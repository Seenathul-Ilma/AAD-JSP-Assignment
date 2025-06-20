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
            //List<ComplaintDTO> complaintDTOS = complaintModel.getAllUnresolvedComplaintsByUser(userId, "Unresolved");
            List<ComplaintDTO> complaintDTOS = complaintModel.getAllUnresolvedComplaintsByUser(userId, "Queued");
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
            //req.setAttribute("error", "Failed load your unresolved complaint list.");
            req.setAttribute("error", "Failed load your queued complaint list.");
            req.getRequestDispatcher("/editUserComplaint.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user_id") == null) {
            //resp.sendRedirect("login.jsp");
            resp.sendRedirect(resp.encodeRedirectURL( req.getContextPath() + "/login.jsp"));
            return;
        }

        String userId = session.getAttribute("user_id").toString();
        System.out.println("The user Id is: "+ userId);

        String title = req.getParameter("title");
        String desc = req.getParameter("description");
        String status = req.getParameter("status");
        String complaintId = req.getParameter("complaint_id");
        String admin_remarks = req.getParameter("admin_remarks");
        //String date = req.getParameter("date_submitted");

        if (complaintId == null || complaintId.isEmpty()) {
            session.setAttribute("flash_error", "No complaint selected for update");
            //req.getRequestDispatcher("/viewComplaint.jsp").forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/api/v1/update/complaint");
            return;
        }

        ServletContext servletContext = req.getServletContext();
        BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dataSource");

        ComplaintModel complaintModel = new ComplaintModel(dataSource);


        try {

            //boolean isUpdated = complaintModel.updateUnresolvedComplaintsByUser(complaintId, title, desc, status, admin_remarks);
            boolean isUpdated = complaintModel.updateComplaints(complaintId, title, desc, status, admin_remarks);

            if (isUpdated) {
                session.setAttribute("flash_success", "Complaint updated Successfully");
            } else {
                session.setAttribute("flash_error", "Failed to update complaint");
            }

            resp.sendRedirect(req.getContextPath() + "/api/v1/update/complaint");

        } catch (Exception e) {
            e.printStackTrace();
            //req.setAttribute("error", "An error occurred: " + e.getMessage());
            //req.getRequestDispatcher("/editUserComplaint.jsp").forward(req, resp);
            session.setAttribute("flash_error", "Something went wrong..!");
            resp.sendRedirect(req.getContextPath() + "/api/v1/update/complaint");
        }

    }
}

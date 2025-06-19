package lk.ijse.gdse71.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.gdse71.model.ComplaintModel;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;

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

@WebServlet("/api/v1/delete/complaint")
public class DeleteComplaint extends HttpServlet {

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

        if(session.getAttribute("role").equals("admin")) {
            req.getRequestDispatcher("/editAdminComplaint.jsp").forward(req, resp);
        } else {
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

        String complaintId = req.getParameter("complaint_id");

        if (complaintId == null || complaintId.isEmpty()) {
            session.setAttribute("flash_error", "No complaint selected for deletion");
            //req.getRequestDispatcher("/viewComplaint.jsp").forward(req, resp);
            return;
        }

        ServletContext servletContext = req.getServletContext();
        BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dataSource");

        ComplaintModel complaintModel = new ComplaintModel(dataSource);

        try {
            boolean isDeleted = complaintModel.deleteComplaint(complaintId);

            if (isDeleted) {
                session.setAttribute("flash_success", "Complaint deleted Successfully");
            } else {
                session.setAttribute("flash_error", "Failed to delete complaint.");
            }
        } catch (Exception e) {
            session.setAttribute("flash_error", "Something went wrong!");
            e.printStackTrace();
        }

        if(session.getAttribute("role").equals("admin")) {
            resp.sendRedirect(req.getContextPath() + "/api/v1/admin/update/complaint");
        } else {
            resp.sendRedirect(req.getContextPath() + "/api/v1/update/complaint");
        }
    }
}

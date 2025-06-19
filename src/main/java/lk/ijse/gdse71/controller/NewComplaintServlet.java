package lk.ijse.gdse71.controller;

import com.mysql.cj.protocol.x.Notice;
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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

        HttpSession session = req.getSession(false);
        String userId = (String) session.getAttribute("user_id");

        if (userId == null) {
            //resp.sendRedirect("login.jsp");
            resp.sendRedirect(resp.encodeRedirectURL( req.getContextPath() + "/login.jsp"));
            return;
        }

        String title = req.getParameter("title");
        String desc = req.getParameter("description");
        String admin_remark = "";
        String status = "Unresolved";

        ComplaintDTO complaintDTO = new ComplaintDTO(
                UUID.randomUUID().toString(),
                userId,
                title,
                desc,
                LocalDateTime.now(),
                status,
                admin_remark
        );

        try {
            ServletContext servletContext = req.getServletContext();
            BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dataSource");

            ComplaintModel complaintModel = new ComplaintModel(dataSource);

            boolean isSaved = complaintModel.saveComplaint(complaintDTO);

            if (isSaved) {
                req.setAttribute("success", "Complaint saved Successfully");
            } else {
                req.setAttribute("error", "Failed to submit complaint.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred: " + e.getMessage());
            //req.getRequestDispatcher("/newComplaint.jsp").forward(req, resp);
        }

        req.getRequestDispatcher("/newComplaint.jsp").forward(req, resp);

    }
}

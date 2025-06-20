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
 * Created: 6/17/2025 10:34 PM
 * Project: CMS
 * --------------------------------------------
 **/

@WebServlet("/api/v1/user/complaint/list")
public class ViewUserComplaintList extends HttpServlet {
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
            List<ComplaintDTO> complaintDTOS = complaintModel.getAllComplaintsByUser(userId);
            req.setAttribute("complaintDTOS", complaintDTOS);
            req.getRequestDispatcher("/viewComplaint.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Failed load your complaint list.");
            req.getRequestDispatcher("/viewComplaint.jsp").forward(req, resp);
        }
    }
}

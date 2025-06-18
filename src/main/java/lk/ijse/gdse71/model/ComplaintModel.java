package lk.ijse.gdse71.model;

import lk.ijse.gdse71.dto.ComplaintDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

/**
 * --------------------------------------------
 * Author: Zeenathul Ilma
 * GitHub: https://github.com/Seenathul-Ilma
 * Website: https://zeenathulilma.vercel.app/
 * --------------------------------------------
 * Created: 6/18/2025 8:15 PM
 * Project: CMS
 * --------------------------------------------
 **/

public class ComplaintModel {
    private final BasicDataSource dataSource;

    public ComplaintModel(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean saveComplaint(ComplaintDTO complaintDTO) {
        String sql = "INSERT INTO complaint (complaint_id, user_id, title, description, date_submitted, status, admin_remarks) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, complaintDTO.getComplaint_id());
            stmt.setString(2, complaintDTO.getUser_id());
            stmt.setString(3, complaintDTO.getTitle());
            stmt.setString(4, complaintDTO.getDescription());
            stmt.setDate(5, Date.valueOf(complaintDTO.getDate_submitted()));
            stmt.setString(6, complaintDTO.getStatus());
            stmt.setString(7, complaintDTO.getAdmin_remarks());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

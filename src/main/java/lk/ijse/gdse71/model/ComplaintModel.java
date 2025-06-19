package lk.ijse.gdse71.model;

import lk.ijse.gdse71.dto.ComplaintDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
            stmt.setTimestamp(5, Timestamp.valueOf(complaintDTO.getDate_submitted()));
            stmt.setString(6, complaintDTO.getStatus());
            stmt.setString(7, complaintDTO.getAdmin_remarks());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteComplaint(String complaint_id) {
        String sql = "DELETE FROM complaint WHERE complaint_id = ?";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, complaint_id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateUnresolvedComplaintsByUser(String complaintId, String title, String description, String status) {
        //String sql = "UPDATE complaint SET title = ?, description = ?, status = ? WHERE complaint_id = ? AND user_id = ?";
        String sql = "UPDATE complaint SET title = ?, description = ?, status = ? WHERE complaint_id = ?";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setString(3, status);
            stmt.setString(4, complaintId);
            //stmt.setString(5, userId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ComplaintDTO> getAllComplaintsByUser(String userId) {
        List<ComplaintDTO> complaintDTOS = new ArrayList<>();

        String sql = "SELECT complaint_id, user_id, title, description, date_submitted, status, admin_remarks FROM complaint WHERE user_id = ? ORDER BY date_submitted DESC";
        String sqlQuery = "SELECT * FROM complaint WHERE user_id = ? ORDER BY date_submitted DESC";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ComplaintDTO complaintDTO = new ComplaintDTO();
                complaintDTO.setComplaint_id(rs.getString("complaint_id"));
                complaintDTO.setUser_id(rs.getString("user_id"));
                complaintDTO.setTitle(rs.getString("title"));
                complaintDTO.setDescription(rs.getString("description"));
                complaintDTO.setDate_submitted(rs.getTimestamp("date_submitted").toLocalDateTime());
                complaintDTO.setStatus(rs.getString("status"));
                complaintDTO.setAdmin_remarks(rs.getString("admin_remarks"));

                complaintDTOS.add(complaintDTO);
            }

            return complaintDTOS;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ComplaintDTO> getAllUnresolvedComplaintsByUser(String userId, String status) {
        List<ComplaintDTO> complaintDTOS = new ArrayList<>();

        String sql = "SELECT complaint_id, user_id, title, description, date_submitted, status, admin_remarks FROM complaint WHERE user_id = ? AND status = ? ORDER BY date_submitted DESC";
        String sqlQuery = "SELECT * FROM complaint WHERE user_id = ?, status = ? ORDER BY date_submitted DESC";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            stmt.setString(2, status);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ComplaintDTO complaintDTO = new ComplaintDTO();
                complaintDTO.setComplaint_id(rs.getString("complaint_id"));
                complaintDTO.setUser_id(rs.getString("user_id"));
                complaintDTO.setTitle(rs.getString("title"));
                complaintDTO.setDescription(rs.getString("description"));
                complaintDTO.setDate_submitted(rs.getTimestamp("date_submitted").toLocalDateTime());
                complaintDTO.setStatus(rs.getString("status"));
                complaintDTO.setAdmin_remarks(rs.getString("admin_remarks"));

                complaintDTOS.add(complaintDTO);
            }

            return complaintDTOS;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ComplaintDTO> getAllComplaints() {
        List<ComplaintDTO> complaintDTOS = new ArrayList<>();

        String sql = "SELECT complaint_id, user_id, title, description, date_submitted, status, admin_remarks FROM complaint ORDER BY date_submitted DESC";
        String sqlQuery = "SELECT * FROM complaint ORDER BY date_submitted DESC";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {


            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ComplaintDTO complaintDTO = new ComplaintDTO();
                complaintDTO.setComplaint_id(rs.getString("complaint_id"));
                complaintDTO.setUser_id(rs.getString("user_id"));
                complaintDTO.setTitle(rs.getString("title"));
                complaintDTO.setDescription(rs.getString("description"));
                complaintDTO.setDate_submitted(rs.getTimestamp("date_submitted").toLocalDateTime());
                complaintDTO.setStatus(rs.getString("status"));
                complaintDTO.setAdmin_remarks(rs.getString("admin_remarks"));

                complaintDTOS.add(complaintDTO);
            }

            return complaintDTOS;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

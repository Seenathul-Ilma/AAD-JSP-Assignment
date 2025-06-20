package lk.ijse.gdse71.model;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * --------------------------------------------
 * Author: Zeenathul Ilma
 * GitHub: https://github.com/Seenathul-Ilma
 * Website: https://zeenathulilma.vercel.app/
 * --------------------------------------------
 * Created: 6/18/2025 10:52 PM
 * Project: CMS
 * --------------------------------------------
 **/

public class ComplaintStatsModel {
    private final BasicDataSource dataSource;

    public ComplaintStatsModel(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int getTotalComplaints(String userId) throws Exception {
        return getCount("SELECT COUNT(*) FROM complaint WHERE user_id = ?", userId);
    }

    public int getResolvedComplaints(String userId) throws Exception {
        return getCount("SELECT COUNT(*) FROM complaint WHERE status = 'Resolved' AND user_id = ?", userId);
    }

    public int getPendingComplaints(String userId) throws Exception {
        //return getCount("SELECT COUNT(*) FROM complaint WHERE status = 'Unresolved' AND user_id = ?", userId);
        return getCount("SELECT COUNT(*) FROM complaint WHERE status = 'Queued' AND user_id = ?", userId);
    }

    private int getCount(String query, String userId) throws Exception {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    private int getCount(String query) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getResolvedComplaints() {
        return getCount("SELECT COUNT(*) FROM complaint WHERE status = 'Resolved' AND user_id = ?");
    }

    public int getPendingComplaints() {
        //return getCount("SELECT COUNT(*) FROM complaint WHERE status = 'Unresolved' AND user_id = ?");
        return getCount("SELECT COUNT(*) FROM complaint WHERE status = 'Queued' AND user_id = ?");
    }

    public int getTotalComplaints() {
        return getCount("SELECT COUNT(*) FROM complaint WHERE user_id = ?");
    }
}

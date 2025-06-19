package lk.ijse.gdse71.model;

import lk.ijse.gdse71.dto.UserDTO;
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
 * Created: 6/20/2025 1:28 AM
 * Project: CMS
 * --------------------------------------------
 **/

public class UserModel {

    private final BasicDataSource dataSource;

    public UserModel(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean saveUser(UserDTO userDTO){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "INSERT INTO user (user_id, name, email, password, role) VALUES (?, ?, ?, ?, ?)"
             )) {
            stmt.setString(1, userDTO.getUserId());
            stmt.setString(2, userDTO.getName());
            stmt.setString(3, userDTO.getEmail());
            stmt.setString(4, userDTO.getPassword());
            stmt.setString(5, userDTO.getRole());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDTO getUserByCredentials(String email, String password) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "SELECT * FROM user WHERE email=? AND password=?"
             )) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new UserDTO(
                        rs.getString("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }

            return null;
        }
    }
}

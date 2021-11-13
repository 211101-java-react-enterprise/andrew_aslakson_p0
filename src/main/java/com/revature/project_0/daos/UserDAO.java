package com.revature.project_0.daos;

import com.revature.project_0.models.User;
import com.revature.project_0.util.connections.ConnectionFactory;
import com.revature.project_0.util.collections.TraversingList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserDAO implements CrudDAO<User> {

    public User findUserByUsername(String username) {
        return getUserBySingleField("username", username);
    }

    public User findUserByEmail(String email) {
        return getUserBySingleField("email", email);
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from users where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return createUserByResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User save(User newUser) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            newUser.setUserUUID(UUID.randomUUID().toString());

            String sql = "insert into users (user_uuid, first_name, last_name, email, username, password) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newUser.getUserUUID());
            pstmt.setString(2, newUser.getFirstName());
            pstmt.setString(3, newUser.getLastName());
            pstmt.setString(4, newUser.getEmail());
            pstmt.setString(5, newUser.getUsername());
            pstmt.setString(6, newUser.getPassword());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                return newUser;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public TraversingList<User> findAll() {
        return null;
    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public boolean update(User updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }

    private User getUserBySingleField(String field, String data) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from users where ? = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, field);
            pstmt.setString(2, data);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return createUserByResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User createUserByResultSet(ResultSet rs) throws SQLException {

        User user = new User(
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("username"),
                rs.getString("password")
            );
        user.setUserUUID(rs.getString("user_uuid"));

        return user;

    }
}

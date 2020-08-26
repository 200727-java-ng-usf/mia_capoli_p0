package com.revature.repos;

import com.revature.models.AppUser;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


public class AppUserRepo {
    /**
     * Find a user in the Repo based on the Username and Password provided.
     * @param username
     * @param password
     * @return
     */
    public Optional<AppUser> findUser(String username, String password) {

        Optional<AppUser> _user = Optional.empty();
        try (Connection conn = ConnectionFactory.getConnFactory().getConnection()) {
            // select the user matching the username and password provided.
            String sql = "SELECT * FROM project0.app_users WHERE username = ? AND PASSWORD = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            _user = mapResultSet(rs).stream().findFirst();

            return _user;


        } catch (SQLException sqle) {

            System.err.println("Database Error!");
        }

        return _user;
    }

    public Optional<AppUser> findUserByUsername(String username) {

        Optional<AppUser> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getConnFactory().getConnection()) {

            String sql = "SELECT * FROM project0.app_users";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                AppUser temp = new AppUser(rs.getString("username"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"));
                if (temp.getUsername().equals(username)) {
                    _user = Optional.of(temp);
                    return _user;
                }
            }

        } catch (SQLException sqle) {
            System.err.println("Database Error!");
        }
        return _user;

    }


    public void save(AppUser newUser) {


        try (Connection conn = ConnectionFactory.getConnFactory().getConnection()){

            String sql = "INSERT INTO project0.app_users (username, password, first_name, last_name) " +
                    "VALUES (?, ? , ? , ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id"}); //retrieve autogenerated values


            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getFirstName());
            pstmt.setString(4, newUser.getLastName());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();

                rs.next();
                newUser.setId(rs.getInt(1));

            }
        } catch (SQLException sqle){
            System.err.println("Database Error!");
        }
    }


    private Set<AppUser> mapResultSet(ResultSet rs) throws SQLException {

        Set<AppUser> users = new HashSet<>();


        while(rs.next()) {
            AppUser temp = new AppUser();
            temp.setId(rs.getInt("id"));
            temp.setUsername(rs.getString("username"));
            temp.setPassword(rs.getString("password"));
            temp.setFirstName(rs.getString("first_name"));
            temp.setLastName(rs.getString("last_name"));
            users.add(temp);
        }

        return users;

    }
}

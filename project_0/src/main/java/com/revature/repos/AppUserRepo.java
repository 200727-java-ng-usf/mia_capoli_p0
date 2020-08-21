package com.revature.repos;

import com.revature.models.AppUser;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AppUserRepo {

    public Optional<AppUser> findUser(String username, String password) {

        Optional<AppUser> _user = Optional.empty();
        try (Connection conn = ConnectionFactory.getConnFactory().getConnection()) {
            String sql = "SELECT * FROM project0.app_users WHERE username = ? AND PASSWORD = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            AppUser appUser = new AppUser();
            while(rs.next()) {
                appUser.setId(rs.getInt("id"));
                appUser.setUsername(rs.getString("username"));
                appUser.setPassword(rs.getString("password"));
                appUser.setFirstName(rs.getString("first_name"));
                appUser.setLastName(rs.getString("last_name"));
            }

            return Optional.of(appUser);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _user;
    }

    public Optional<AppUser> findUserByUsername(String username) {
        return Optional.of(null);
    }


    public void save(AppUser newUser) {
        try (Connection conn = ConnectionFactory.getConnFactory().getConnection()) {
            String sql = "INSERT INTO app_users (username = ?, password = ?, first_name = ?, last_name = ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getFirstName());
            pstmt.setString(4, newUser.getLastName());


            AppUser appUser = new AppUser();
            while(rs.next()) {
                appUser.setUsername(rs.getString("username"));
                appUser.setPassword(rs.getString("password"));
                appUser.setFirstName(rs.getString("first_name"));
                appUser.setLastName(rs.getString("last_name"));
            }
            System.out.println(appUser.toString());


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }


}

package com.revature.repos;

import com.revature.models.Account;
import com.revature.models.AppUser;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.revature.AppDriver.app;

public class AccountRepo {


//    public Optional<Account> findAccountByCredentials(String name, int accountId) {
//
//        Optional<Account> _account = Optional.empty();
//        try (Connection conn = ConnectionFactory.getConnFactory().getConnection()) {
//            String sql = "SELECT * FROM project0.user_accounts WHERE account_id = ? AND name = ?";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, accountId);
//            pstmt.setString(2, name);
//            ResultSet rs = pstmt.executeQuery();
//
//            _account = mapAccountResultSet(rs).stream().findFirst();
//
//        } catch (SQLException sqle) {
//            sqle.printStackTrace();
//        }
//
//        return _account;
//    }

    public Optional<Account> findUserByAccountId(int accountId) {

        Optional<Account> _account = Optional.empty();

        try (Connection conn = ConnectionFactory.getConnFactory().getConnection()) {

            String sql = "SELECT * FROM project0.user_accounts";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                Account temp = new Account();
                temp.setAccountId(rs.getInt("account_id"));

                if (temp.getAccountId() == accountId) {
                    _account = Optional.of(temp);
                }
            }


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _account;

    }


    public Set<Account> findUsersAccounts(AppUser currentUser) {

        Set<Account> _account = new HashSet<>();

        try (Connection conn = ConnectionFactory.getConnFactory().getConnection()) {

            String sql = "SELECT * FROM project0.user_accounts WHERE assoc_user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, currentUser.getId());

            ResultSet rs = pstmt.executeQuery();
            _account = mapAccountResultSet(rs);


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _account;

    }


    public void save(Account newAccount) {
        AppUser currentUser = app.getCurrentUser();
        try (Connection conn = ConnectionFactory.getConnFactory().getConnection()) {

            String sql = "INSERT INTO project0.user_accounts (account_id, name, balance, assoc_user_id) " +
                    "VALUES (?, ? , ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);


            pstmt.setInt(1, newAccount.getAccountId());
            pstmt.setString(2, newAccount.getAccountName());
            pstmt.setDouble(3, newAccount.getBalance());
            pstmt.setInt(4, currentUser.getId());


            pstmt.executeUpdate();


        } catch (SQLException sqle) {
            System.err.println("Database Error!");
        }
    }


    private Set<Account> mapAccountResultSet(ResultSet rs) throws SQLException {

        Set<Account> accounts = new HashSet<>();


        while (rs.next()) {
            Account temp = new Account();
            temp.setAccountId(rs.getInt("account_id"));
            temp.setAccountName(rs.getString("name"));
            temp.setBalance(rs.getInt("balance"));
            accounts.add(temp);
        }

        return accounts;

    }

    public void updateBalance(double balance) {

        try (Connection conn = ConnectionFactory.getConnFactory().getConnection()) {

            String sql = "UPDATE project0.user_accounts SET balance = ? WHERE assoc_user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);


            pstmt.setInt(1, (int) balance);
            pstmt.setInt(2, app.getCurrentUser().getId());


            pstmt.executeUpdate();


        } catch (SQLException sqle) {
            System.err.println("Database Error!");
        }
    }

}



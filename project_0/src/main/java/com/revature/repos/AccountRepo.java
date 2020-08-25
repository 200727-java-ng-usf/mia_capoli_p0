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

    /**
     * Finding a user in the Repository by the provided account id.
     *
     * @param accountId
     * @retur An optional account if one such exists.
     */
    public Optional<Account> findAccountByAccountId(int accountId) {
        //optional in case no account exists
        Optional<Account> _account = Optional.empty();

        //try to connect to database
        try (Connection conn = ConnectionFactory.getConnFactory().getConnection()) {
            //execute query to search through accounts - if an account associated with this user does not exist,
            // there isn't a problem, because the sql statement still can run.
            String sql = "SELECT * FROM project0.user_accounts";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            //loop through result set and obtain specific Account Assoc with user.
            while (rs.next()) {
                Account temp = new Account();
                temp.setAccountId(rs.getInt("account_id"));

                if (temp.getAccountId() == accountId) {
                    _account = Optional.of(temp);
                }
            }


        } catch (SQLException sqle) {
            System.err.println("Database Error!");
        }
        //return optional account, if found.
        return _account;

    }

    /**
     * Finding a set of accounts that are associated with the current user.
     * @param currentUserId
     * @return a set of accounts assoc. with a user id
     */
    public Set<Account> findUsersAccounts(int currentUserId) {
        //Create a set of accounts
        Set<Account> _account = new HashSet<>();

        try (Connection conn = ConnectionFactory.getConnFactory().getConnection()) {
            //Select from the database accounts that are associated with the current user's id.
            String sql = "SELECT * FROM project0.user_accounts WHERE assoc_user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, currentUserId);

            ResultSet rs = pstmt.executeQuery();
            _account = mapAccountResultSet(rs);


        } catch (SQLException sqle) {
            System.err.println("Database Error!");
        }
        //return the set of accounts.
        return _account;

    }

    /**
     * Save the newly registered account into the database.
     * @param newAccount
     */
    public void save(Account newAccount) {
        AppUser currentUser = app.getCurrentUser();
        try (Connection conn = ConnectionFactory.getConnFactory().getConnection()) {
            //Insert the account params as a row in the database.
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

    /**
     *
     * @param rs
     * @return
     * @throws SQLException
     */
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



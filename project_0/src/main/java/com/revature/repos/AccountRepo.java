package com.revature.repos;

import com.revature.models.Account;
import com.revature.models.AccountUtilPOJO;
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
            //TODO FIX
            String sql = "SELECT * FROM project0.user_accounts as ua " +
                    "JOIN project0.app_user_accounts as uae ON ua.account_id = uae.account_id";
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
            sqle.printStackTrace();
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
        Set<AccountUtilPOJO> _accountUtil = new HashSet<>();
        Set<Account> _account = new HashSet<>();


        try (Connection conn = ConnectionFactory.getConnFactory().getConnection()) {
            //Select from the database accounts that are associated with the current user's id.
            String sql = "SELECT ua.account_id, uae.user_id, ua.balance, ua.name FROM project0.user_accounts ua JOIN project0.app_user_accounts" +
                    " uae ON ua.account_id = uae.account_id WHERE uae.user_id = ?;";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, currentUserId);
            ResultSet rs = pstmt.executeQuery();

            _accountUtil = mapAccountUtilPOJOResultSet(rs);

            PreparedStatement pstmt2 = conn.prepareStatement(sql);
            pstmt.setInt(1, currentUserId);
            ResultSet rs2 = pstmt.executeQuery();

            _account = mapAccountResultSet(rs2);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.err.println("Database Error!");
        }
        //return the set of accounts.
        return _account;

    }

    /**
     * Update the balance of the account
     * @param balance
     */
    public void updateBalance(double balance) {

        try (Connection conn = ConnectionFactory.getConnFactory().getConnection()) {
            //Setting the balance to the new balance
            String sql = "UPDATE project0.user_accounts SET balance = ? WHERE account_id IN " +
                    "(SELECT account_id FROM project0.app_user_accounts WHERE app_user_accounts.account_id = account_id and account_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            System.out.println(app.getCurrentAccount().getAccountId());
            pstmt.setInt(1, (int) balance);
            pstmt.setInt(2, app.getCurrentAccount().getAccountId());


            pstmt.executeUpdate();


        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.err.println("Database Error!");
        }
    }

    /**
     * Save the newly registered account into the database.
     * @param newAccount
     */
    public void save(Account newAccount) {
        AppUser currentUser = app.getCurrentUser();
        try (Connection conn = ConnectionFactory.getConnFactory().getConnection()) {
            //Insert the account params as a row in the database.
            String sql = "INSERT INTO project0.user_accounts (account_id, name, balance) " +
                    "VALUES (?, ? , ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);


            pstmt.setInt(1, newAccount.getAccountId());
            pstmt.setString(2, newAccount.getAccountName());
            pstmt.setDouble(3, newAccount.getBalance());

            String sql2 = "INSERT INTO project0.app_user_accounts (user_id, account_id) " +
                    "VALUES (?, ?)";
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);

            pstmt2.setInt(1, currentUser.getId());
            pstmt2.setInt(2, newAccount.getAccountId());

            pstmt.executeUpdate();
            pstmt2.executeUpdate();


        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.err.println("Database Error!");
        }
    }


    private Set<Account> mapAccountResultSet(ResultSet rs) throws SQLException {

        Set<Account> accounts = new HashSet<>();
        //add all of the results into an account object
        while (rs.next()) {
            Account temp = new Account(); // TODO MAKE NEW JOIN OBJECT?
            temp.setAccountId(rs.getInt("account_id"));
            temp.setAccountName(rs.getString("name"));
            temp.setBalance(rs.getInt("balance"));
            accounts.add(temp);
            accounts.add(temp);
        }

        return accounts;

    }

    /**
     * Gather the returned accounts from the result set in a Java-readable format.
     * @param rs
     * @return
     * @throws SQLException
     */
    private Set<AccountUtilPOJO> mapAccountUtilPOJOResultSet(ResultSet rs) throws SQLException {

        Set<AccountUtilPOJO> accountsJoin = new HashSet<>();
        //add all of the results into an account object
        while (rs.next()) {
            AccountUtilPOJO temp = new AccountUtilPOJO();
            temp.setAccountId(rs.getInt("account_id"));
            temp.setUserId(rs.getInt("user_id"));
            accountsJoin.add(temp);
        }

//        Set<Account> accounts = new HashSet<>();
//        rs.first();
//
//        while (rs.next()) {
//            Account temp = new Account();
//            temp.setAccountId(rs.getInt("account_id"));
//            temp.setAccountName(rs.getString("name"));
//            temp.setBalance(rs.getInt("balance"));
//            accounts.add(temp);
//        }

        return accountsJoin;

    }


}



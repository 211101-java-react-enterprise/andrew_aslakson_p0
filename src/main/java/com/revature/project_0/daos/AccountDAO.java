package com.revature.project_0.daos;

import com.revature.project_0.models.accounts.Account;
import com.revature.project_0.models.accounts.CheckingAccount;
import com.revature.project_0.models.accounts.SavingsAccount;
import com.revature.project_0.util.connections.ConnectionFactory;
import com.revature.project_0.util.collections.DoubleLinkedList;
import com.revature.project_0.util.collections.TraversingList;
import com.revature.project_0.util.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 *      Follows Singleton Design pattern
 *          -Only one instance of this object can exist!
 *
 *      performs sql queries related to Accounts, data should
 *      be verified before entering this class!
 *
 *      Used for pulling accounts on account selection screen
 *      registering accounts and linking accounts to users
 */

public class AccountDAO implements CrudDAO<Account>{

    //0000000000000000000000000000000000000000000000000

    private static AccountDAO accountDao;

    private Logger logger;

    //0000000000000000000000000000000000000000000000000

    static {
        accountDao = new AccountDAO();
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    private AccountDAO() {
        logger = Logger.getLogger();
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    static public AccountDAO getInstance(){
        return accountDao;
    }

    //-------------------------------------------------

    public DoubleLinkedList findAccounts(String userUUID) {

        DoubleLinkedList<Account> results = new DoubleLinkedList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select a.account_uuid, a.type, a.account_name, a.current_balance " +
                         "from user_accounts ua " +
                         "join accounts a " +
                         "on a.account_uuid = ua.account_uuid " +
                         "where user_uuid = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userUUID);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                /*
                if (rs.getString("type").equals("C")) {
                    results.add(new CheckingAccount(rs.getString("account_name"), rs.getDouble("current_balance")));

                } else {
                    results.add(new SavingsAccount(rs.getString("account_name"), rs.getDouble("current_balance")));
                }
                */
                results.add(createAccountFromResultsSet(rs));

            }
            return results;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //-------------------------------------------------

    @Override
    public Account save(Account newAccount) {
        newAccount.setAccountUUID(UUID.randomUUID().toString());

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "insert into accounts (account_uuid, type, account_name, current_balance) values (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newAccount.getAccountUUID());
            pstmt.setString(2, newAccount.getType());
            pstmt.setString(3, newAccount.getName());
            pstmt.setDouble(4, newAccount.getCurrentBalance());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                return newAccount;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    //-------------------------------------------------

    // This should be run right after saving
    // is still a public method as it is useful
    // linking current users to current accounts
    public boolean linkAccountToUser(String userUUID, String accountUUID) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "insert into user_accounts (account_uuid, user_uuid) values (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountUUID);
            pstmt.setString(2, userUUID);

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //-------------------------------------------------

    @Override
    public TraversingList findAll() {
        return null;
    }

    //-------------------------------------------------

    @Override
    public Account findById(String id) {
        return null;
    }

    //-------------------------------------------------

    @Override
    public boolean update(Account updatedObj) {
        return false;
    }

    //-------------------------------------------------

    @Override
    public boolean removeById(String id) {
        return false;
    }

    //-------------------------------------------------

    public boolean doesUserIDHaveAccountName(String userUUID, String name) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select ua.user_uuid, a.account_uuid, a.type, a.account_name, a.current_balance " +
                         "from user_accounts ua " +
                         "join accounts a " +
                         "on a.account_uuid = ua.account_uuid " +
                         "where user_uuid = ? and account_name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userUUID);
            pstmt.setString(2, name);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //-------------------------------------------------

    private Account createAccountFromResultsSet(ResultSet rs) throws SQLException {
        Account tempAccount;
        if (rs.getString("type").equals("C")) {
            tempAccount = new CheckingAccount(
                                rs.getString("account_name"),
                                rs.getDouble("current_balance"));
            tempAccount.setAccountUUID(rs.getString("account_uuid"));

            return tempAccount;
        } else if (rs.getString("type").equals("S")) {
            tempAccount = new SavingsAccount(
                                rs.getString("account_name"),
                                rs.getDouble("current_balance"));
            tempAccount.setAccountUUID(rs.getString("account_uuid"));

            return tempAccount;

        }

        return null;
    }

    //-------------------------------------------------

    public boolean updateBalance(double newBalance, String accountUUID) {


        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "update accounts set current_balance = ? where account_uuid = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setDouble(1, newBalance);
            pstmt.setString(2, accountUUID);

            if (pstmt.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //-------------------------------------------------

}

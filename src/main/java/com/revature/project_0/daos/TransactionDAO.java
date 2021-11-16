package com.revature.project_0.daos;

import com.revature.project_0.models.Transaction;
import com.revature.project_0.models.accounts.Account;
import com.revature.project_0.models.accounts.CheckingAccount;
import com.revature.project_0.models.accounts.SavingsAccount;
import com.revature.project_0.util.collections.TraversingList;
import com.revature.project_0.util.connections.ConnectionFactory;
import com.revature.project_0.util.logger.Logger;

import java.sql.*;
import java.util.UUID;

/**
 *      Follows Singleton Design pattern
 *          -Only one instance of this object can exist!
 *
 *      performs sql queries related to Transactions, data should
 *      be verified before entering this class!
 *
 *      Used for populating transactions in an account and
 *      when making deposits and withdrawals.
 */

public class TransactionDAO implements CrudDAO<Transaction> {

    //0000000000000000000000000000000000000000000000000

    private static TransactionDAO transactionDao;

    private Logger logger;

    //0000000000000000000000000000000000000000000000000

    static {
        transactionDao = new TransactionDAO();
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    private TransactionDAO() {
        logger = Logger.getLogger();
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    static public TransactionDAO getInstance(){
        return transactionDao;
    }

    //-------------------------------------------------

    @Override
    public Transaction save(Transaction transaction) {
        transaction.setTransactionUUID(UUID.randomUUID().toString());

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            logger.log("attempting to save Transaction to database");

            String sql = "insert into transactions (transaction_uuid, account_uuid, date_time, type_flag, amount, old_balance, new_balance) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, transaction.getTransactionUUID());
            pstmt.setString(2, transaction.getAccountUUID());
            pstmt.setTimestamp(3, transaction.getDateTime());
            pstmt.setBoolean(4, transaction.isType());
            pstmt.setDouble(5, transaction.getAmount());
            pstmt.setDouble(6, transaction.getOldBalance());
            pstmt.setDouble(7, transaction.getNewBalance());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                logger.log("Transaction persisted to database");

                return transaction;
            }

        } catch (SQLException e) {
            logger.log("SQL statement failed execution");
            logger.log(e.getMessage());

            e.printStackTrace();
        }

        logger.log("failed to persist transaction to database");

        return null;
    }

    //-------------------------------------------------

    @Override
    public TraversingList<Transaction> findAll() {
        return null;
    }

    //-------------------------------------------------

    @Override
    public Transaction findById(String id) {
        return null;
    }

    //-------------------------------------------------

    @Override
    public boolean update(Transaction updatedObj) {
        return false;
    }

    //-------------------------------------------------

    @Override
    public boolean removeById(String id) {
        return false;
    }

    //-------------------------------------------------

    public void populateTransactions(Account currentAccount) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            logger.log("Attempting to populate transactions for currentAccount");

            String sql = "select t.date_time, t.type_flag, t.amount, t.old_balance, t.new_balance " +
                         "from transactions t " +
                         "where account_uuid = ? " +
                         "order by date_time";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, currentAccount.getAccountUUID());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                currentAccount.addTransaction(new Transaction(
                        rs.getBoolean("type_flag"),
                        rs.getTimestamp("date_time"),
                        rs.getDouble("amount"),
                        rs.getDouble("old_balance")
                        ));


            }

            logger.log("Transactions populated in currentAccount");

        } catch (SQLException e) {
            logger.log("SQL exception occured");
            logger.log(e.getMessage());

            e.printStackTrace();
        }
    }

    //-------------------------------------------------

}

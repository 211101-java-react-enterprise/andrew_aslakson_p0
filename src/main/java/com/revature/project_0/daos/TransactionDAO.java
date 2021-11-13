package com.revature.project_0.daos;

import com.revature.project_0.models.Transaction;
import com.revature.project_0.models.accounts.Account;
import com.revature.project_0.models.accounts.CheckingAccount;
import com.revature.project_0.models.accounts.SavingsAccount;
import com.revature.project_0.util.collections.TraversingList;
import com.revature.project_0.util.connections.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO implements CrudDAO<Transaction> {

    public  TransactionDAO() {

    }
    @Override
    public Transaction save(Transaction newObj) {
        return null;
    }

    @Override
    public TraversingList<Transaction> findAll() {
        return null;
    }

    @Override
    public Transaction findById(String id) {
        return null;
    }

    @Override
    public boolean update(Transaction updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }

    public void populateTransactions(Account currentAccount) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.revature.project_0.daos;

import com.revature.project_0.models.accounts.Account;
import com.revature.project_0.util.connections.ConnectionFactory;
import com.revature.project_0.util.collections.DoubleLinkedList;
import com.revature.project_0.util.collections.TraversingList;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountDAO implements CrudDAO{
    public DoubleLinkedList findAccounts(String userUUID) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from ";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Object save(Object newObj) {
        return null;
    }

    @Override
    public TraversingList findAll() {
        return null;
    }

    @Override
    public Object findById(String id) {
        return null;
    }

    @Override
    public boolean update(Object updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }

    public Account findAccountByUserIDAndName(String name, String userUUID) {

    }
}

package com.revature.project_0.daos;

import com.revature.project_0.util.ConnectionFactory;
import com.revature.project_0.util.DoubleLinkedList;
import com.revature.project_0.util.TraversingList;

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
}

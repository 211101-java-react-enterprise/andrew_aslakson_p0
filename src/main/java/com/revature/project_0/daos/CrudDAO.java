package com.revature.project_0.daos;

import com.revature.project_0.util.collections.TraversingList;

public interface CrudDAO<T> {
    T save(T newObj);
    TraversingList<T> findAll();
    T findById(String id);
    boolean update(T updatedObj);
    boolean removeById(String id);
}

package com.revature.project_0.daos;

import com.revature.project_0.util.collections.TraversingList;

/**
 *      A generic CrudDAO interface specifying some
 *      basic crud related functions
 *
 *      Classes that implement this interface didn't need
 *      any extra elements to be constructed and so have
 *      been created using the singleton design pattern
 *      as we don't want multiple instances of this object
 *      in our program
 */

public interface CrudDAO<T> {
    T save(T newObj);
    TraversingList<T> findAll();
    T findById(String id);
    boolean update(T updatedObj);
    boolean removeById(String id);
}

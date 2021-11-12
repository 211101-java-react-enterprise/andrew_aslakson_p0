package com.revature.project_0.util;

public interface Collection<T> {

    boolean add(T elem);
    boolean contains(T elem);
    boolean isEmpty();
    boolean remove(T elem);
    int size();
    void clear();

}

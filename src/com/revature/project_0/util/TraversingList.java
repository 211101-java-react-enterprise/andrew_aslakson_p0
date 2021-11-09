package com.revature.project_0.util;

public interface TraversingList<T> extends Collection<T> {
    /**
     *  This interface works with a list that tracks a current position
     *  as well as head and tail values
     */

    T get(int index);
    T retrieveCurrent();

    // These methods move currentNode to either the next or previous
    // node and returns that nodes value
    T next();
    T prev();

    void setCurrent(int index);

}

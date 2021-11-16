package com.revature.project_0.util.collections;

import com.revature.project_0.util.collections.Collection;

public interface TraversingList<T> extends Collection<T> {
    /**
     *  This interface works with a list that tracks a current position
     *  as well as head and tail values
     */

    T get(int index);
    T retrieveCurrent();

    // These methods return currentNode and then moves currentNode to
    // either the next or previous node
    T next();
    T prev();

    void setCurrent(int index); // move current node to given index
    void moveToBottom(); // moves currentNode to head
    void moveToTop(); // moves current Node to tail
    T getTop(); // returns tail.datum useful when editing items as they are being added

}

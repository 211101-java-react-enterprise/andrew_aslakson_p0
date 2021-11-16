package com.revature.project_0.util.collections;

import com.revature.project_0.util.collections.TraversingList;

/**
 *      DoubleLinkedList is a list where each member contains a reference
 *      to the previous node and to the next node
 *
 *          This particular list includes a field called currentNode that
 *          can keep track of "current position" in the list and is affected
 *          by methods like moveToTop or Next() allowing for easier and
 *          lighter - weight access to the list when used in loops
 */

public class DoubleLinkedList<T> implements TraversingList<T> {

    //0000000000000000000000000000000000000000000000000

    Node<T> head;
    Node<T> tail;
    Node<T> currentNode;

    int size;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public DoubleLinkedList() {
        head = tail = currentNode = null;
        size = 0;
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    //Adds Nodes with data to list --NOTE add can only append to the end of the list
    @Override
    public boolean add(T elem) {
        if (elem == null) return false;

        if (isEmpty()) {
            head = tail = currentNode = new Node<T>(null, elem);
            size++;
            return true;
        }

        Node<T> newNode = new Node(tail, elem);
        tail.nextNode = newNode;
        tail = newNode;
        size++;
        return true;
    }

    //-------------------------------------------------

    // Checks if an element is contained within the list
    @Override
    public boolean contains(T elem) {
        if (elem == null) return false;

        Node<T> tempNode = head;

        while (tempNode != null) {
            if (tempNode.datum.equals(elem)) return true;
            tempNode = tempNode.nextNode;
        }
        return false;
    }

    //-------------------------------------------------

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //-------------------------------------------------

    @Override
    public boolean remove(T elem) {
        if (elem == null) return false;

        if (head.datum.equals(elem)) {
            head = head.nextNode;
            head.prevNode = null;
            size--;
            return true;
        }

        Node<T> tempNode = head.nextNode;

        while (tempNode != tail) {
            if (tempNode.datum.equals(elem)) {
                tempNode.prevNode.nextNode = tempNode.nextNode;
                tempNode.nextNode.prevNode = tempNode.prevNode;
                size--;
                return true;
            }
            tempNode = tempNode.nextNode;
        }

        if (tail.datum.equals(elem)) {
            tail = tail.prevNode;
            tail.nextNode = null;
            size--;
            return true;
        }
        return false;
    }

    //-------------------------------------------------

    @Override
    public int size() {
        return size;
    }

    //-------------------------------------------------

    // Empty List
    @Override
    public void clear() {
        head = tail = currentNode = null;
        size = 0;

    }

    //-------------------------------------------------

    // used when index is known, for most applications next and prev will be better options if possible
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) return null;

        return getNodeByIndex(index).datum;
    }

    //-------------------------------------------------

    @Override
    public T retrieveCurrent() {
        return currentNode.datum;
    }

    //-------------------------------------------------

    // Note never returns null! if repeated calls are made this object will keep
    // sending out tail.datums data!
    @Override
    public T next() {
        if (currentNode == tail) return tail.datum;

        T tempObj = currentNode.datum;
        currentNode = currentNode.nextNode;
        return tempObj;
    }

    //-------------------------------------------------

    // Note never returns null! if repeated calls are made this object will keep
    // sending out head.datums data!
    @Override
    public T prev() {
        if (currentNode == head) return head.datum;

        T tempObj = currentNode.datum;
        currentNode = currentNode.prevNode;
        return tempObj;
    }

    //-------------------------------------------------

    @Override
    public void setCurrent(int index) {
        currentNode = getNodeByIndex(index);
    }

    //-------------------------------------------------

    @Override
    public void moveToBottom() {
        currentNode = head;
    }

    //-------------------------------------------------

    @Override
    public void moveToTop() {
        currentNode = tail;
    }

    //-------------------------------------------------

    @Override
    public T getTop() {
        return tail.datum;
    }

    //-------------------------------------------------

    private Node<T> getNodeByIndex(int index) {
        Node<T> tempNode = head;
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.nextNode;
        }
        return tempNode;
    }

    //-------------------------------------------------

    //*************************************************

    /**
     *  Node is not to be used by programmer, therefore it is private
     *
     *  A Node in this class simply contains a reference to the previous node
     *      in the list, a reference to the next node in the list, and a
     *      generic field called datum which is the data you wish to store
     *
     *          - Note adding nodes appends to the end of the list and nextNode
     *              is set to null on instantiation.
     */
    private static class Node<T> {

        //0000000000000000000000000000000000000000000000000

        Node<T> prevNode;
        Node<T> nextNode;
        T datum;

        //0000000000000000000000000000000000000000000000000

        //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

        Node(Node<T> prevNode, T datum) {
            this.prevNode = prevNode;
            this.nextNode = null;
            this.datum = datum;
        }

        //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    }

    //*************************************************

}

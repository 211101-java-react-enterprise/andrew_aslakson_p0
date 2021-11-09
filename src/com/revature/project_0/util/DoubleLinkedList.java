package com.revature.project_0.util;

/**
 *      add can only append to end of list
 */

public class DoubleLinkedList<T> implements TraversingList<T> {

    Node<T> head;
    Node<T> tail;
    Node<T> currentNode;

    int size;

    public DoubleLinkedList() {
        head = tail = currentNode = null;
        size = 0;
    }

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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

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

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) return null;

        return getNodeByIndex(index).datum;
    }

    @Override
    public T retrieveCurrent() {
        return currentNode.datum;
    }

    @Override
    public T next() {
        if (currentNode == tail) return null;

        currentNode = currentNode.nextNode;
        return retrieveCurrent();
    }

    @Override
    public T prev() {
        if (currentNode == head) return null;

        currentNode = currentNode.prevNode;
        return retrieveCurrent();
    }

    @Override
    public void setCurrent(int index) {
        currentNode = getNodeByIndex(index);
    }

    private Node<T> getNodeByIndex(int index) {
        Node<T> tempNode = head;
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.nextNode;
        }
        return tempNode;
    }

    private static class Node<T> {
        Node<T> prevNode;
        Node<T> nextNode;
        T datum;
        Node(Node<T> prevNode, T datum) {
            this.prevNode = prevNode;
            this.nextNode = null;
            this.datum = datum;
        }
    }
}

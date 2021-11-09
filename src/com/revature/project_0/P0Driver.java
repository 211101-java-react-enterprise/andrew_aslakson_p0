package com.revature.project_0;

import com.revature.project_0.util.DoubleLinkedList;

public class P0Driver {
    public static void main(String[] args) {
        DoubleLinkedList<String> myList = new DoubleLinkedList<>();
        myList.add("string_1");
        myList.add("string_2");
        myList.add("string_3");
        myList.add("string_4");
        myList.add("string_5");

        System.out.println(myList.retrieveCurrent());
        System.out.println(myList.next());
        System.out.println(myList.next());
        System.out.println(myList.next());
        System.out.println(myList.next());
        System.out.println(myList.next());

        System.out.println(myList.retrieveCurrent());
        System.out.println(myList.prev());
        System.out.println(myList.prev());
        System.out.println(myList.prev());
        System.out.println(myList.prev());
        System.out.println(myList.prev());

        System.out.println();
        System.out.println(myList.contains("string_3"));
        System.out.println(myList.contains("string_6"));

        System.out.println();
        System.out.println(myList.size());
        myList.remove("string_3");
        System.out.println(myList.size());
        myList.remove("string_1");
        System.out.println(myList.size());

    }
}

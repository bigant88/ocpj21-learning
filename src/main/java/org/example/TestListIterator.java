package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class TestListIterator {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("T", "S", "R", "I", "F");
        ListIterator<String> iter = list.listIterator(2);
        while(iter.hasNext()) {
            System.out.print(iter.next());
        }
    }
}
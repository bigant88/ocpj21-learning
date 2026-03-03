package org.example;

public class TestPrimitives {
    public static void main(String[] args) {

        var m = 10; //Line n1
        var n = 20; //Line n2
        var p = m = n = 30; //Line n3
        System.out.println(m + n + p); //Line n4

        final byte var = 127;
        byte x;
        System.out.println(x = var - 1);
//        x = var + 1;
//        x = var ++;

        int i = 2;
        boolean res = false;
        res = i++ == 2 || --i == 2 && --i == 2;
        System.out.println(i);
    }
}

package org.example;

class Super { static String ID = "QBANK"; }

class Sub extends Super{
    static { System.out.print("In Sub"); }
}
public class Main{
    public static void main(String[] args){
        System.out.println(Sub.ID);
    }
}
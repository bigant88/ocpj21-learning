package org.example;

public class TestString{
    public static void main(String args[ ] ){
        String s = "hello";
        StringBuilder sb = new StringBuilder( "hello" );
        sb.reverse().reverse();
        System.out.println(sb);
        if( s == sb.toString() )  System.out.println( "Equal" );
        else System.out.println( "Not Equal" );
    }
}

package org.example;

public class TestSwitch {
    public static void main(String[] args) {

        int x = 4;
        switch (x) {
            default:
                System.out.println("Still no idea what x is");
            case 1:
                System.out.println("x is equal to 1");
                break;
            case 2:
                System.out.println("x is equal to 2");
                break;
            case 3:
                System.out.println("x is equal to 3");
                break;
        }
        //
        switch ("HELLO") {
            case "HELLO":
                System.out.print(1);
            default:
                System.out.print(2);
            case "null":
                System.out.print(3);
        }
        /**
         * switch expression can accept following:
         *
         * char or Character,
         *
         * byte or Byte,
         *
         * short or Short,
         *
         * int or Integer,
         *
         * An enum only from Java 6,
         *
         * A String expression only from Java 7.
         *
         * Compatible literal value or constant can be used as the switch expression.
         */
    }
}

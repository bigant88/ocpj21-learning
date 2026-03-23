package org.example;

import java.util.function.Consumer;
import java.util.function.DoubleToIntFunction;

public class TestLambda {
    public static void main(String[] args) {
        int x = 5;
        int finalX = x;
        DoubleToIntFunction doubleToIntFunction = p -> finalX + 1;// ✔
        x = 6;        // ❌ nếu có lambda dùng x
        Consumer<Integer> consumer = System.out::print;
        Integer i = 5;
        consumer.andThen(consumer).accept(i++); //Line n1
    }
}

package org.example;

import java.util.List;

public class ParallelStream {

    public static void main(String[] args) {
        List.of(1,2,3,4,5)
                .parallelStream()
                .map(w -> doWork(w))
                .forEachOrdered(System.out::print);

        List.of(1,2,3,4,5)
                .stream()
                .map(w -> doWork(w))
                .forEach(System.out::print);
    }
    private static int doWork(int input) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {}
        return input;
    }

}

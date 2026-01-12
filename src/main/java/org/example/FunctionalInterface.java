package org.example;

import java.nio.file.DirectoryStream;
import java.util.function.Predicate;

public class FunctionalInterface {

    public static void main(String[] args) {
        Predicate<Animal1> p1  = a -> a.canHop();
        Predicate<Animal2> p2  = Animal2::canHop;
    }
}

interface Animal1 {
    boolean canHop();
}
interface Animal2 {
    boolean canHop();
}
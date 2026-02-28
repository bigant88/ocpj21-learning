package org.example;

import java.util.Arrays;
import java.util.List;

public class FI2 {
}
class Book {
    private String title;
    private String genre;
    public Book(String title, String genre){
        this.title = title; this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("Gone with the wind", "Fiction"),
                new Book("Bourne Ultimatum", "Thriller"),
                new Book("The Client", "Thriller")
        );

        Reader r = b->{
            System.out.println("Reading book "+b.getTitle());
        };
        books.forEach(x->r.read(x));
    }

    interface Reader{
        default void read(Book b){ }
        void unread(Book b);
        private void doSomethingelse(){ }
    }
}



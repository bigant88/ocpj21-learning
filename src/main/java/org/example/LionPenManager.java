package org.example;

import java.util.concurrent.*;
/**
 * 📌 CyclicBarrier dùng để:
 *
 * Chia task theo phase
 *
 * Chờ đủ thread rồi chạy tiếp
 */
public class LionPenManager {
    private void removeLions() { System.out.println("Removing lions"); }
    private void cleanPen()    { System.out.println("Cleaning the pen"); }
    private void addLions()    { System.out.println("Adding lions"); }

    public void performTask(CyclicBarrier c1, CyclicBarrier c2) {
        try {
            removeLions();
            c1.await();
            cleanPen();
            c2.await();
            addLions();
        } catch (InterruptedException | BrokenBarrierException e) {
            // handle exception
        }
    }

    public static void main(String[] args) {
        try (var service = Executors.newFixedThreadPool(4)) {
            var manager = new LionPenManager();
            var c1 = new CyclicBarrier(4);
            var c2 = new CyclicBarrier(4,
                    () -> System.out.println("*** Pen Cleaned!"));

            for (int i = 0; i < 4; i++)
                service.submit(() -> manager.performTask(c1, c2));
        }
    }
}


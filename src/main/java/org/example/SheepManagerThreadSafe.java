package org.example;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SheepManagerThreadSafe {
    /* first code - not thread safe
    private int sheepCount = 0;
    private void incrementAndReport() {
        System.out.print((++sheepCount) + " ");
    }*/
    //Atomic - thread-safe, not ordered
    /*
    private AtomicInteger sheepCount = new AtomicInteger(0);
    private void incrementAndReport() {
        synchronized(this) {
            System.out.print(sheepCount.incrementAndGet() + " ");
        }
    } */

    // lock
    private AtomicInteger sheepCount = new AtomicInteger(0);

    private final Lock lock = new ReentrantLock();
    private void incrementAndReport() {
        lock.lock();
        try {
            System.out.print(sheepCount.incrementAndGet() + " ");
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        try (var service = Executors.newFixedThreadPool(20)) {
            SheepManagerThreadSafe manager = new SheepManagerThreadSafe();
            for (int i = 0; i < 10; i++)
                service.submit(manager::incrementAndReport);
        }
    }



}

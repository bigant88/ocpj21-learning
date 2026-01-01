package org.example;


import java.util.concurrent.*;

public class SingleThreadExecutorResult {
    private static int counter = 0;

    public static void main(String[] unused) throws Exception {
        try (var service = Executors.newSingleThreadExecutor()) {

            Future<?> result = service.submit(() -> {
                for (int i = 0; i < 1_000_000; i++)
                    counter++;
            });

            var futureResult = result.get(10, TimeUnit.SECONDS); // chờ tối đa 10 giây
            System.out.println("Reached! " + futureResult);
        }
        catch (TimeoutException e) {
            System.out.println("TimeoutException Not reached in time");
        }
    }
}
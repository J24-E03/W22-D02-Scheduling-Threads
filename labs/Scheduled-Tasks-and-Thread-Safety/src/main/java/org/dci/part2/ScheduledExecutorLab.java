package org.dci.part2;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorLab {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        Runnable timePrinter = () -> {
            System.out.println("Probing service at " + LocalTime.now().format(formatter));
        };

        scheduledExecutorService.scheduleAtFixedRate(timePrinter, 2, 3, TimeUnit.SECONDS);

        scheduledExecutorService.schedule(() -> {
            System.out.println("\nShutting down ...");
            scheduledExecutorService.shutdown();
        }, 15, TimeUnit.SECONDS);
        try {
            scheduledExecutorService.awaitTermination(16, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Shutdown interrupted!");
            Thread.currentThread().interrupt();
        }

    }
}

package org.dci.part1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorLab {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        Runnable timePrinter = () -> {
            System.out.println("Current Time: " + LocalTime.now().format(formatter));
        };

        scheduledExecutorService.scheduleAtFixedRate(timePrinter, 0, 1, TimeUnit.SECONDS);

        try {
            if (!scheduledExecutorService.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("\nShutting down ...");
                scheduledExecutorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

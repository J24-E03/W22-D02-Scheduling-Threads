package org.dci.part3;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        Runnable task1 = () -> System.out.println("Task 1 started at " + LocalTime.now().format(formatter));
        Runnable task2 = () -> System.out.println("Task 2 started at " + LocalTime.now().format(formatter));
        Runnable task3 = () -> System.out.println("Task 3 started at " + LocalTime.now().format(formatter));

        scheduledExecutorService.schedule(task1, 1, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(task2, 2, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(task3, 5, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown();

        try {
            if (!scheduledExecutorService.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("\nForcing shutdown...");
                scheduledExecutorService.shutdownNow();
            } else {
                System.out.println("\nAll tasks completed within the time limit.");
            }
        } catch (InterruptedException e) {
            System.out.println("Shutdown interrupted!");
        }



    }
}
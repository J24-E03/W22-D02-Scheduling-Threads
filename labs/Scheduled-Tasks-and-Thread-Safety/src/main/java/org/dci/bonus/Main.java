package org.dci.bonus;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        CountDownLatch latch = new CountDownLatch(1);

        scheduleTask(scheduledExecutorService, latch, 1000, 3000);

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        scheduledExecutorService.shutdown();
        try {
            if (!scheduledExecutorService.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduledExecutorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduledExecutorService.shutdownNow();
        }
    }

    private static void scheduleTask(ScheduledExecutorService scheduler, CountDownLatch latch, int initialDelay, int delay) {
        if (initialDelay <= 0) {
            System.out.println("\nFinishing execution");
            latch.countDown();
            return;
        }
        scheduler.schedule(() -> {
            Task task = new Task(delay);
            task.run();
            int nextDelay = task.getNextDelay();

            scheduleTask(scheduler, latch, nextDelay, nextDelay);
        }, initialDelay, TimeUnit.MILLISECONDS);
    }
}

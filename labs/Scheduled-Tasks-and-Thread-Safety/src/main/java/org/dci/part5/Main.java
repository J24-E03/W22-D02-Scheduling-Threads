package org.dci.part5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        System.out.println("Initial counter value: " + sharedResource.getId());

        ExecutorService executor = Executors.newFixedThreadPool(5);

        Runnable incrementTask = () -> {
            for (int i = 0; i < 1000; i++) {
                sharedResource.increment();
            }
        };

        for (int i = 0; i < 5; i++) {
            executor.execute(incrementTask);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Final counter value: " + sharedResource.getId());
    }
}

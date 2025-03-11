package org.dci.bonus;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task implements Runnable {
    private int delay;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Task(int delay) {
        this.delay = delay;
    }

    @Override
    public void run() {
        System.out.println("Task executed at " + LocalTime.now().format(formatter) + " with delay: " + delay + " ms");
    }

    public int getNextDelay() {
        return Math.max(0, delay - 500);
    }
}

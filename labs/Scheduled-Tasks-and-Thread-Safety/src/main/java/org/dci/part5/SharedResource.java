package org.dci.part5;

import lombok.Data;
import lombok.Getter;

import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {
    @Getter
    private int id = 0;

    ReentrantLock lock = new ReentrantLock(true);

    public void increment() {
        lock.lock();
        try {
            id++;
        } finally {
            lock.unlock();
        }
    }
}

package deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockFreeResource {
    private final Lock lockA = new ReentrantLock();
    private final Lock lockB = new ReentrantLock();

    public void operation1() {
        try {
            if (lockA.tryLock()) {
                Thread.sleep(50);
                if (lockB.tryLock()) {
                    try {
                        System.out.println("Operation 1 completed");
                    } finally {
                        lockB.unlock();
                    }
                }
                lockA.unlock();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void operation2() {
        try {
            if (lockB.tryLock()) {
                Thread.sleep(50);
                if (lockA.tryLock()) {
                    try {
                        System.out.println("Operation 2 completed");
                    } finally {
                        lockA.unlock();
                    }
                }
                lockB.unlock();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
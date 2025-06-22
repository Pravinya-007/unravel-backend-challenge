package deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Demonstrates a deadlock-free approach using tryLock with ReentrantLocks.
 * Each operation attempts to acquire two locks but backs off safely if unable to acquire both,
 * avoiding circular wait conditions that lead to deadlocks.
 */
public class DeadlockFreeResource {
    private final Lock lockA = new ReentrantLock();
    private final Lock lockB = new ReentrantLock();

    /**
     * Attempts to acquire lockA and then lockB using tryLock,
     * simulating a safe concurrent operation (Operation 1).
     */
    public void operation1() {
        try {
            if (lockA.tryLock()) { // Try acquiring lockA
                Thread.sleep(50);  // Simulate work before acquiring second lock
                if (lockB.tryLock()) { // Try acquiring lockB
                    try {
                        System.out.println("Operation 1 completed");
                    } finally {
                        lockB.unlock(); // Always unlock in finally block
                    }
                }
                lockA.unlock(); // Release lockA even if lockB failed
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt flag
        }
    }

    /**
     * Attempts to acquire lockB and then lockA using tryLock,
     * simulating a safe concurrent operation (Operation 2).
     */
    public void operation2() {
        try {
            if (lockB.tryLock()) { // Try acquiring lockB first
                Thread.sleep(50);  // Simulate work before acquiring second lock
                if (lockA.tryLock()) { // Try acquiring lockA
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

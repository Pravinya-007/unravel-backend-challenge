package session;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/**
 * Thread-safe SessionManager that allows concurrent session reads
 * and exclusive access for writes using ReadWriteLock.
 *
 * Internally uses ConcurrentHashMap for non-blocking base access,
 * enhanced by ReadWriteLock to ensure atomic multi-step operations if needed.
 */
public class SessionManager {

    // Thread-safe map for storing user sessions
    private final ConcurrentHashMap<String, String> sessions = new ConcurrentHashMap<>();

    // ReadWriteLock to allow multiple concurrent reads but exclusive writes
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * Retrieves the session token for a given userId.
     * Uses read lock to allow concurrent reads.
     *
     * @param userId the user's identifier
     * @return session token if exists, else null
     */
    public String getSession(String userId) {
        lock.readLock().lock();
        try {
            return sessions.get(userId);
        } finally {
            lock.readLock().unlock(); // Always unlock in finally
        }
    }

    /**
     * Creates or updates the session token for the given userId.
     * Uses write lock to ensure exclusive access.
     *
     * @param userId       the user's identifier
     * @param sessionToken the session token to store
     */
    public void createOrUpdateSession(String userId, String sessionToken) {
        lock.writeLock().lock();
        try {
            sessions.put(userId, sessionToken);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Deletes the session associated with the given userId.
     * Uses write lock to ensure consistency.
     *
     * @param userId the user's identifier
     */
    public void deleteSession(String userId) {
        lock.writeLock().lock();
        try {
            sessions.remove(userId);
        } finally {
            lock.writeLock().unlock();
        }
    }
}

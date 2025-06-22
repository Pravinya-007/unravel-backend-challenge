package session;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class SessionManager {
    private final ConcurrentHashMap<String, String> sessions = new ConcurrentHashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public String getSession(String userId) {
        lock.readLock().lock();
        try {
            return sessions.get(userId);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void createOrUpdateSession(String userId, String sessionToken) {
        lock.writeLock().lock();
        try {
            sessions.put(userId, sessionToken);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void deleteSession(String userId) {
        lock.writeLock().lock();
        try {
            sessions.remove(userId);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
package memory;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

public class MemorySafeCache<K, V> {
    private final Map<K, WeakReference<V>> cache = new WeakHashMap<>();

    public void put(K key, V value) {
        cache.put(key, new WeakReference<>(value));
    }

    public V get(K key) {
        WeakReference<V> ref = cache.get(key);
        return ref != null ? ref.get() : null;
    }
}
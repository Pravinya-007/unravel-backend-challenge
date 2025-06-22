package memory;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * A memory-safe generic cache using WeakHashMap and WeakReference.
 *
 * - Keys are held weakly via WeakHashMap: if no strong reference exists to a key,
 *   the entry is eligible for garbage collection.
 * - Values are wrapped in WeakReference: allows value GC even if the key is still strongly referenced.
 *
 * This structure is ideal for caching objects without risking memory leaks.
 */
public class MemorySafeCache<K, V> {

    // Cache where both keys and values can be garbage collected if no strong references exist
    private final Map<K, WeakReference<V>> cache = new WeakHashMap<>();

    /**
     * Adds a key-value pair to the cache.
     * Value is wrapped in a WeakReference to allow GC when no longer in use.
     */
    public void put(K key, V value) {
        cache.put(key, new WeakReference<>(value));
    }

    /**
     * Retrieves the value associated with a key.
     * Returns null if the key is not present or if the value has been garbage collected.
     */
    public V get(K key) {
        WeakReference<V> ref = cache.get(key);
        return ref != null ? ref.get() : null; // ref.get() may return null if GC'd
    }
}

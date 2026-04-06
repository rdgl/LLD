import java.util.HashMap;
import java.util.Map;

public class CacheNode<K, V> {
    private final int id;
    private final int capacity;
    private final Map<K, V> storage;
    private final IEvictionPolicy<K> evictionPolicy;

    public CacheNode(int id, int capacity, IEvictionPolicy<K> evictionPolicy) {
        this.id = id;
        this.capacity = capacity;
        this.storage = new HashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    public V get(K key) {
        if (!storage.containsKey(key)) return null;
        evictionPolicy.onAccess(key);
        return storage.get(key);
    }

    public void put(K key, V value) {
        if (storage.containsKey(key)) {
            storage.put(key, value);
            evictionPolicy.onAccess(key);
            return;
        }

        if (storage.size() >= capacity) {
            K evictedKey = evictionPolicy.evict();
            if (evictedKey != null) storage.remove(evictedKey);
        }

        storage.put(key, value);
        evictionPolicy.onAdd(key);
    }
}

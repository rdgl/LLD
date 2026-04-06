import java.util.HashMap;
import java.util.Map;

public class MockDatabase<K, V> implements IDatabase<K, V> {
    private final Map<K, V> data = new HashMap<>();

    @Override
    public V get(K key) {
        return data.get(key);
    }

    @Override
    public void put(K key, V value) {
        data.put(key, value);
    }
}

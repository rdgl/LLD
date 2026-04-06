import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DistributedCache<K, V> {
    private final List<CacheNode<K, V>> nodes;
    private final IDistributionStrategy<K, V> distributionStrategy;
    private final IDatabase<K, V> database;

    public DistributedCache(int nodeCount, int nodeCapacity, 
                            IDistributionStrategy<K, V> distributionStrategy, 
                            Supplier<IEvictionPolicy<K>> evictionPolicyFactory, 
                            IDatabase<K, V> database) {
        this.nodes = new ArrayList<>();
        for (int i = 0; i < nodeCount; i++) {
            this.nodes.add(new CacheNode<>(i, nodeCapacity, evictionPolicyFactory.get()));
        }
        this.distributionStrategy = distributionStrategy;
        this.database = database;
    }

    public V get(K key) {
        int nodeIndex = distributionStrategy.selectNode(key, nodes);
        CacheNode<K, V> node = nodes.get(nodeIndex);
        
        V value = node.get(key);
        if (value != null) return value;

        value = database.get(key);
        if (value != null) node.put(key, value);
        
        return value;
    }

    public void put(K key, V value) {
        int nodeIndex = distributionStrategy.selectNode(key, nodes);
        CacheNode<K, V> node = nodes.get(nodeIndex);
        node.put(key, value);
        database.put(key, value);
    }
}

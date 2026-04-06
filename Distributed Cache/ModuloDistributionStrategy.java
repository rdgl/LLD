import java.util.List;

public class ModuloDistributionStrategy<K, V> implements IDistributionStrategy<K, V> {
    @Override
    public int selectNode(K key, List<CacheNode<K, V>> nodes) {
        if (nodes == null || nodes.isEmpty()) return -1;
        return Math.abs(key.hashCode()) % nodes.size();
    }
}

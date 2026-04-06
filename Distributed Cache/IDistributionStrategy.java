import java.util.List;

public interface IDistributionStrategy<K, V> {
    int selectNode(K key, List<CacheNode<K, V>> nodes);
}

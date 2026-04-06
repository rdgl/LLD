public interface IEvictionPolicy<K> {
    void onAccess(K key);
    void onAdd(K key);
    K evict();
}

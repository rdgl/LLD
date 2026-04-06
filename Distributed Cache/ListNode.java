public class ListNode<K> {
    K key;
    ListNode<K> prev;
    ListNode<K> next;

    public ListNode(K key) {
        this.key = key;
    }
}

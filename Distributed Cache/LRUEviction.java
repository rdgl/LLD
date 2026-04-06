import java.util.HashMap;
import java.util.Map;

public class LRUEviction<K> implements IEvictionPolicy<K> {
    private final Map<K, ListNode<K>> keyMap = new HashMap<>();
    private final ListNode<K> head = new ListNode<>(null);
    private final ListNode<K> tail = new ListNode<>(null);

    public LRUEviction() {
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public void onAccess(K key) {
        ListNode<K> node = keyMap.get(key);
        if (node != null) {
            removeNode(node);
            addAtHead(node);
        }
    }

    @Override
    public void onAdd(K key) {
        if (keyMap.containsKey(key)) {
            onAccess(key);
            return;
        }
        ListNode<K> newNode = new ListNode<>(key);
        keyMap.put(key, newNode);
        addAtHead(newNode);
    }

    @Override
    public K evict() {
        ListNode<K> lastNode = tail.prev;
        if (lastNode == head) return null;
        removeNode(lastNode);
        keyMap.remove(lastNode.key);
        return lastNode.key;
    }

    private void addAtHead(ListNode<K> node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(ListNode<K> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

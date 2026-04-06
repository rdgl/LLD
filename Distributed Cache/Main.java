public class Main {
    public static void main(String[] args) {
        MockDatabase<String, String> db = new MockDatabase<>();
        db.put("user1", "Alice");
        db.put("user2", "Bob");

        DistributedCache<String, String> cache = new DistributedCache<>(
            2, 2, 
            new ModuloDistributionStrategy<>(), 
            () -> new LRUEviction<>(), 
            db
        );

        System.out.println("Get user1: " + cache.get("user1"));
        System.out.println("Get user1 again: " + cache.get("user1"));
        
        cache.put("user3", "Charlie");
        cache.put("user4", "David");
        cache.put("user5", "Eve");

        System.out.println("Added multiple users to check eviction...");
    }
}

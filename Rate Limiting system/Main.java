public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimitConfig config = new RateLimitConfig(5, 1000); // 5 requests per second

        System.out.println("--- Testing Fixed Window Counter ---");
        RateLimiterService fixedService = new RateLimiterService(config, FixedWindowRateLimiter::new);
        testLimiter(fixedService, "User1");

        Thread.sleep(1100); // Wait for window to reset

        System.out.println("\n--- Testing Sliding Window Counter ---");
        RateLimiterService slidingService = new RateLimiterService(config, SlidingWindowRateLimiter::new);
        testLimiter(slidingService, "User2");
    }

    private static void testLimiter(RateLimiterService service, String key) {
        for (int i = 1; i <= 7; i++) {
            boolean allowed = service.isAllowed(key);
            System.out.println("Request " + i + " for " + key + ": " + (allowed ? "ALLOWED" : "REJECTED"));
        }
    }
}

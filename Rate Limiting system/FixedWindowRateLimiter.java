public class FixedWindowRateLimiter implements IRateLimiter {
    private final RateLimitConfig config;
    private int counter;
    private long windowStart;

    public FixedWindowRateLimiter(RateLimitConfig config) {
        this.config = config;
        this.counter = 0;
        this.windowStart = System.currentTimeMillis();
    }

    @Override
    public synchronized boolean isAllowed(String key) {
        long now = System.currentTimeMillis();
        if (now - windowStart >= config.getWindowSizeInMillis()) {
            windowStart = now;
            counter = 0;
        }

        if (counter < config.getLimit()) {
            counter++;
            return true;
        }
        return false;
    }
}

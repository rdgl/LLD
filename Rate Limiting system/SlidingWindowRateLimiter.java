public class SlidingWindowRateLimiter implements IRateLimiter {
    private final RateLimitConfig config;
    private int prevCount;
    private int currentCount;
    private long windowStart;

    public SlidingWindowRateLimiter(RateLimitConfig config) {
        this.config = config;
        this.prevCount = 0;
        this.currentCount = 0;
        this.windowStart = System.currentTimeMillis();
    }

    @Override
    public synchronized boolean isAllowed(String key) {
        long now = System.currentTimeMillis();
        long windowSize = config.getWindowSizeInMillis();
        long elapsed = now - windowStart;

        if (elapsed >= windowSize) {
            prevCount = (elapsed >= 2 * windowSize) ? 0 : currentCount;
            currentCount = 0;
            windowStart = now;
            elapsed = 0;
        }

        double weight = 1.0 - ((double) elapsed / windowSize);
        double estimatedCount = (prevCount * weight) + currentCount;

        if (estimatedCount < config.getLimit()) {
            currentCount++;
            return true;
        }
        return false;
    }
}

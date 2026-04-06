public interface IRateLimiter {
    boolean isAllowed(String key);
}

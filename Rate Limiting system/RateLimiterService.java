import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class RateLimiterService {
    private final ConcurrentHashMap<String, IRateLimiter> limiters = new ConcurrentHashMap<>();
    private final RateLimitConfig config;
    private final Function<RateLimitConfig, IRateLimiter> limiterFactory;

    public RateLimiterService(RateLimitConfig config, Function<RateLimitConfig, IRateLimiter> limiterFactory) {
        this.config = config;
        this.limiterFactory = limiterFactory;
    }

    public boolean isAllowed(String key) {
        IRateLimiter limiter = limiters.computeIfAbsent(key, k -> limiterFactory.apply(config));
        return limiter.isAllowed(key);
    }
}

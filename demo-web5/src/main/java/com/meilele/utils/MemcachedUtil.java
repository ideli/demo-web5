/*package com.meilele.utils;

import javax.annotation.PostConstruct;
import net.rubyeye.xmemcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class MemcachedUtil {
    private static final Logger   LOGGER = LoggerFactory.getLogger(MemcachedUtil.class);
    public static MemcachedClient staticMemcachedClient;

    @Autowired
    private MemcachedClient       memcachedClient;

    @PostConstruct
    private void memcachedClientInit() {
        staticMemcachedClient = this.memcachedClient;
    }

    public static boolean put(String key, Object value, int expiry) {
        try {
            return staticMemcachedClient.set(key, expiry, value);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    public static boolean remove(String key) {
        try {
            return staticMemcachedClient.delete(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    public static <T> T get(String key) {
        try {
            return staticMemcachedClient.get(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public static boolean replace(String key, Object value, int expiry) {
        try {
            return staticMemcachedClient.replace(key, expiry, value);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    public static MemcachedClient getStaticMemcachedClient() {
        return staticMemcachedClient;
    }
}*/
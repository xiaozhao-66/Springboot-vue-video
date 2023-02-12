package com.xz.video.common.utils;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalUtils {

    private static ThreadLocal<Map> dataMapThreadLocal = new ThreadLocal<Map>() {
        @Override
        public Map initialValue() {
            return new HashMap();
        }
    };

    public ThreadLocalUtils() {
    }

    public static <K, V> V get(K key) {
        return (V) dataMapThreadLocal.get().get(key);
    }

    public static <K, V> void put(K key, V value) {
        dataMapThreadLocal.get().put(key, value);
    }

    public static <K> void remove(K key) {
        (dataMapThreadLocal.get()).remove(key);
    }
}

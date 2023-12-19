package com.bitcoinpriceprovider.cache;

import lombok.Getter;

public class CacheEntry<T> {
    @Getter
    private T value;
    private long expirationTime;

    public CacheEntry(T value, long expirationTime) {
        this.value = value;
        this.expirationTime = expirationTime;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expirationTime;
    }
}

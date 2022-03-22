package io.may4th.tge22.security.impl;

public class AuthTokenTO {

    private String userId;
    private long issuedAt;
    private long expireAt;
    private long seed;
    private String sing;

    public String getUserId() {
        return userId;
    }

    public AuthTokenTO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public long getIssuedAt() {
        return issuedAt;
    }

    public AuthTokenTO setIssuedAt(long issuedAt) {
        this.issuedAt = issuedAt;
        return this;
    }

    public long getExpireAt() {
        return expireAt;
    }

    public AuthTokenTO setExpireAt(long expireAt) {
        this.expireAt = expireAt;
        return this;
    }

    public long getSeed() {
        return seed;
    }

    public AuthTokenTO setSeed(long seed) {
        this.seed = seed;
        return this;
    }

    public String getSing() {
        return sing;
    }

    public AuthTokenTO setSing(String sing) {
        this.sing = sing;
        return this;
    }
}

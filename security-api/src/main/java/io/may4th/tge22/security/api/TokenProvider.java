package io.may4th.tge22.security.api;

public interface TokenProvider {

    String generateToken(UserDetails userDetails, long now);
}

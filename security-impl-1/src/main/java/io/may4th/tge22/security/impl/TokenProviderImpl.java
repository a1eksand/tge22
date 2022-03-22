package io.may4th.tge22.security.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.may4th.tge22.security.api.TokenProvider;
import io.may4th.tge22.security.api.UserDetails;
import io.may4th.tge22.security.api.exceptions.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.Random;

@Component
public class TokenProviderImpl extends BaseCoder implements TokenProvider {

    private static final String ALGORITHM = "SHA-1";

    private static final long LIFETIME_MILLIS = 3600000;

    private static final int SECRET_LENGTH = 128;

    private final ObjectMapper objectMapper;

    private final byte[] secret;

    @Autowired
    public TokenProviderImpl(ObjectMapper objectMapper) {
        super(ALGORITHM);
        this.secret = new byte[SECRET_LENGTH];
        new Random().nextBytes(this.secret);
        this.objectMapper = objectMapper;
    }

    @Override
    public String generateToken(UserDetails userDetails, long now) {
        var authToken = new AuthTokenTO()
            .setUserId(userDetails.getId())
            .setIssuedAt(now)
            .setExpireAt(now + LIFETIME_MILLIS)
            .setSeed(new Random().nextLong());

        authToken.setSing(sing(authToken));

        try {
            return encode(objectMapper.writeValueAsString(authToken).getBytes());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public AuthTokenTO extractAuthTokenTO(String token) {
        try {
            return objectMapper.readValue(decode(token), AuthTokenTO.class);
        } catch (Exception e) {
            throw new AuthenticationException(e);
        }
    }

    public boolean isSingValid(AuthTokenTO authTokenTo) {
        return Objects.equals(authTokenTo.getSing(), sing(authTokenTo));
    }

    private byte[] toBytes(long... longs) {
        var buffer = ByteBuffer.allocate(Long.SIZE * longs.length / Byte.SIZE);
        for (var value : longs) {
            buffer.putLong(value);
        }
        return buffer.array();
    }

    private String sing(AuthTokenTO authTokenTo) {
        return encode(hash(
            authTokenTo.getUserId().getBytes(),
            toBytes(authTokenTo.getIssuedAt(), authTokenTo.getExpireAt(), authTokenTo.getSeed()),
            secret
        ));
    }
}

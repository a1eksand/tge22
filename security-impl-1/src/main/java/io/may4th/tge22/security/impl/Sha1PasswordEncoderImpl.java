package io.may4th.tge22.security.impl;

import io.may4th.tge22.security.api.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;

@Component
public class Sha1PasswordEncoderImpl extends BaseCoder implements PasswordEncoder {

    private static final String ALGORITHM = "SHA-1";
    private static final int SALT_LENGTH = 32;

    public Sha1PasswordEncoderImpl() {
        super(ALGORITHM);
    }

    private byte[] join(byte[] a, byte[] b) {
        var result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    private byte[] salt() {
        var data = new byte[SALT_LENGTH];
        new Random().nextBytes(data);
        return data;
    }

    @Override
    public String encode(String rawPassword) {
        if (rawPassword.isEmpty()) {
            throw new IllegalArgumentException();
        }
        var salt = salt();
        var hash = hash(salt, rawPassword.getBytes());
        return encode(join(salt, hash));
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        if (rawPassword.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (encodedPassword.isEmpty()) {
            throw new IllegalArgumentException();
        }
        var pass = decode(encodedPassword);
        var salt = Arrays.copyOfRange(pass, 0, SALT_LENGTH);
        var hash = Arrays.copyOfRange(pass, SALT_LENGTH, pass.length);
        return Arrays.equals(hash, hash(salt, rawPassword.getBytes()));
    }
}

package io.may4th.tge22.security.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

class BaseCoder {

    private final String algorithm;

    BaseCoder(String algorithm) {
        this.algorithm = algorithm;
    }

    String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    byte[] hash(byte[]... data) {
        try {
            var digest = MessageDigest.getInstance(algorithm);
            for (var chunk : data) {
                digest.update(chunk);
            }
            return digest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}

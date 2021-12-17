package com.company;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class GameManager {
    private final SecureRandom secureRandom;
    private byte[] secretKey = new byte[32];
    private String move = null;

    public GameManager() {
        secureRandom = new SecureRandom();
    }

    public String makeMove(String[] moves) {
        move = moves[secureRandom.nextInt(moves.length)];
        return move;
    }

    public String getMove() {
        return move;
    }

    private byte[] hmac(String algorithm, byte[] key, byte[] message) {
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key, algorithm));
            return mac.doFinal(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }
    }

    public String generateHMACsha256(String msg) {
        secureRandom.nextBytes(secretKey);
        byte[] hmac = hmac("HmacSHA256", secretKey, msg.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(hmac);
    }

    public String getSecretKey() {
        return Hex.encodeHexString(secretKey);
    }
}

package com.company;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.SecureRandom;

public class GameManager {

    private SecureRandom secureRandom;
    byte[] secretKey = new byte[32];
    private String computerChoice;

    public GameManager() {
        secureRandom = new SecureRandom();
    }

    public String makeAMove(String[] moves) {
        computerChoice = moves[secureRandom.nextInt(moves.length)];
        secureRandom.nextBytes(secretKey);

        byte[] hmacSha256 = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "HmacSHA256");
            mac.init(secretKeySpec);
            hmacSha256 = mac.doFinal(computerChoice.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }

        return String.format("%032x", new BigInteger(1, hmacSha256)).toLowerCase();
    }
}

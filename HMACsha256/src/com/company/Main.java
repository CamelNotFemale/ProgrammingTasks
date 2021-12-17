package com.company;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class Main {
    private static byte[] hmac(String algorithm, byte[] key, byte[] message) {
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key, algorithm));
            return mac.doFinal(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }
    }

    private static String generateHMACsha256(String msg, byte[] key) {
        byte[] hmac = hmac("HmacSHA256", key, msg.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(hmac);
    }

    public static void main(String[] args) throws DecoderException {
        if (args.length > 0 && args.length < 3) {
            byte[] key;
            if (args.length == 1) {
                SecureRandom secureRandom = new SecureRandom();
                key = new byte[32];
                secureRandom.nextBytes(key);
            }
            else {
                key = Hex.decodeHex(args[1]);
            }
            System.out.println("HMAC: " + generateHMACsha256(args[0], key));
            System.out.println("HMAC key: " + Hex.encodeHexString(key));
        }
        else System.out.println("Error: The number of args must exceed 0 and no more 2!");
    }
}

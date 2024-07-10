package com.upmscl.service;
import java.security.SecureRandom;
import java.math.BigInteger;

public class SessionUtils {

	public static String generateSessionToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[32];
        secureRandom.nextBytes(token);
        return new BigInteger(1, token).toString(16); // Convert to hexadecimal string
    }
}

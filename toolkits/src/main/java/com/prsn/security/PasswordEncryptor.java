package com.prsn.security;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.codec.digest.Crypt;

import javax.enterprise.context.Dependent;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 * @author プロソニーPRSN
 */
@Dependent
public class PasswordEncryptor {

    static final String MD5_PREFIX = "$1$";
    private static final Random random = new SecureRandom();

    public PasswordEncryptor(){}

    /**
     * Returns a random salt to be used to hash a password.
     * @return a 16 bytes random salt
     */
    private String getNextSalt() {
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        return Arrays.toString(bytes);
    }

    /**
     * Formation hash password.
     * @param password
     */
    public String encryptPassword(String password) {
        return Crypt.crypt(password, getNextSalt()); // 1 - ABeLd7prwPWdA
    }

    /**
     * Check password.
     * @param plainPassword password.
     * @param encryptedPassword hash password.
     * @param salt.
     * @return true - success.
     */
    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        // Пустые пароли пусть будут всегда неверны.

        if (StringUtils.isBlank(encryptedPassword) || StringUtils.isBlank(plainPassword))
            return false;

        final String salt;
        if(StringUtils.startsWith(encryptedPassword, MD5_PREFIX)) {
            int sepIndex = encryptedPassword.indexOf('$', MD5_PREFIX.length());
            if(sepIndex != -1) {
                salt = encryptedPassword.substring(0, sepIndex);
            } else {
                return false;
            }
        } else {
            salt = encryptedPassword.substring(0, 2);
        }

        final String hash = Crypt.crypt(plainPassword, salt);
        return StringUtils.equals(hash, encryptedPassword);
    }

}

package com.cabletech.rms.proxy.security;

import static org.junit.Assert.assertNotNull;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;


/**
 * @author YuLeyuan
 *
 */
public class TestSecurity {
	/**
     * testGeneratePassword
     */
    @Test
    public void testGeneratePassword(){
    	String salt="admin";
    	String plainTextPassword="password";
    	String hashedPasswordHex = new Sha256Hash(plainTextPassword, salt, 1024).toHex();
    	assertNotNull(hashedPasswordHex);
    }
}

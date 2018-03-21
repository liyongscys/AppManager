package com.cabletech.rms.test;

import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang.math.NumberUtils;

/**
 * @author YuLeyuan
 *
 */
public class CommonTest {

	/**
	 * @param args args
	 */
	public static void main(String[] args) {
		assertNotNull(NumberUtils.isNumber("12.34"));
		assertNotNull(NumberUtils.isNumber("-12.34"));
		assertNotNull(NumberUtils.isNumber("0"));
		assertNotNull(NumberUtils.isNumber(""));
		assertNotNull(NumberUtils.isNumber(null));
	}

}

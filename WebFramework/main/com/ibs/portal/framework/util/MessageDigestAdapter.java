package com.ibs.portal.framework.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

// TODO jvm
//import sun.misc.BASE64Encoder;

public class MessageDigestAdapter {

	private final String arithmetic;

	public MessageDigestAdapter(String arithmetic) {
		this.arithmetic = arithmetic;
	}

	public char[] encode(byte[] src) {
		try {
			MessageDigest md5 = MessageDigest.getInstance(arithmetic);
			byte[] d = md5.digest(src);
			// BASE64Encoder base64en = new BASE64Encoder();
			String result = new String(Base64.encodeBase64(d)); // base64en.encode(d);
			return result.toCharArray();
		} catch (NoSuchAlgorithmException e) {
			throw new java.lang.RuntimeException(e);
		}
	}

	public String encode(String src) {
		return new String(encode(src.getBytes()));
	}

}

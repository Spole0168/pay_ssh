package com.ibs.portal.integration.sso.generate;

import java.io.IOException;

import com.oreilly.servlet.Base64Decoder;
import com.oreilly.servlet.Base64Encoder;

public class TokenUtils {

	public static String encode(byte[] bytes) {
		return Base64Encoder.encode(bytes);
	}

	public static String encodeString(String source) {
		if (null == source) {
			return null;
		}
		return Base64Encoder.encode(source);
	}

	public static byte[] decode(String input) throws IOException {
		return Base64Decoder.decodeToBytes(input);
	}

	public static String decodeString(String input) throws IOException {
		return Base64Decoder.decode(input);
	}

}

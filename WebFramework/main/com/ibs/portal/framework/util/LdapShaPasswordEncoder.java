package com.ibs.portal.framework.util;

import org.apache.commons.codec.binary.Base64;
import java.security.MessageDigest;
import org.springframework.util.Assert;

public class LdapShaPasswordEncoder {

	private static final int SHA_LENGTH = 20;
	private static final String SSHA_PREFIX = "{SSHA}";
	private static final String SSHA_PREFIX_LC = SSHA_PREFIX.toLowerCase();
	private static final String SHA_PREFIX = "{SHA}";
	private static final String SHA_PREFIX_LC = SHA_PREFIX.toLowerCase();

	private boolean forceLowerCasePrefix;

	public String encodePassword(String rawPass, Object salt) {
		MessageDigest sha;

		try {
			sha = MessageDigest.getInstance("SHA"); // SHA-1 
		} catch (java.security.NoSuchAlgorithmException e) {
			throw new RuntimeException("No SHA implementation available!", e);
		}

		sha.update(rawPass.getBytes());

		if (salt != null) {
			Assert.isInstanceOf(byte[].class, salt,
					"Salt value must be a byte array");
			sha.update((byte[]) salt);
		}

		byte[] hash = combineHashAndSalt(sha.digest(), (byte[]) salt);

		String prefix;

		if (salt == null) {
			prefix = forceLowerCasePrefix ? SHA_PREFIX_LC : SHA_PREFIX;
		} else {
			prefix = forceLowerCasePrefix ? SSHA_PREFIX_LC : SSHA_PREFIX;
		}

		return prefix + new String(Base64.encodeBase64(hash));
	}

	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		String encPassWithoutPrefix;

		if (encPass.startsWith(SSHA_PREFIX)
				|| encPass.startsWith(SSHA_PREFIX_LC)) {
			encPassWithoutPrefix = encPass.substring(6);
			salt = extractSalt(encPass);
		} else {
			encPassWithoutPrefix = encPass.substring(5);
			salt = null;
		}

		// Compare the encoded passwords without the prefix
		return encodePassword(rawPass, salt).endsWith(encPassWithoutPrefix);
	}

	private byte[] combineHashAndSalt(byte[] hash, byte[] salt) {
		if (salt == null) {
			return hash;
		}

		byte[] hashAndSalt = new byte[hash.length + salt.length];
		System.arraycopy(hash, 0, hashAndSalt, 0, hash.length);
		System.arraycopy(salt, 0, hashAndSalt, hash.length, salt.length);

		return hashAndSalt;
	}

	private byte[] extractSalt(String encPass) {
		String encPassNoLabel = encPass.substring(6);

		byte[] hashAndSalt = Base64.decodeBase64(encPassNoLabel.getBytes());
		int saltLength = hashAndSalt.length - SHA_LENGTH;
		byte[] salt = new byte[saltLength];
		System.arraycopy(hashAndSalt, SHA_LENGTH, salt, 0, saltLength);

		return salt;
	}

	public boolean isForceLowerCasePrefix() {
		return forceLowerCasePrefix;
	}

	public void setForceLowerCasePrefix(boolean forceLowerCasePrefix) {
		this.forceLowerCasePrefix = forceLowerCasePrefix;
	}

	public static void main(String[] args) throws Exception {
//		 {SHA}cl9ZNbHpgxt5X47a4FNMLwfh+84=
		LdapShaPasswordEncoder enc = new LdapShaPasswordEncoder();
		enc.setForceLowerCasePrefix(false);
		
		System.out.println(enc.encodePassword("passw0rd", null));
		
		System.out.println(enc.isPasswordValid("{SHA}fGphxo74ubawYbKMNIvB7Xkhy1M=", "passw0rd", null));
		
//		System.out.println(new String(Base64.encodeBase64("base64实测测试反复".getBytes())));
	}
}

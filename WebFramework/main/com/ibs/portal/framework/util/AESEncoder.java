package com.ibs.portal.framework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


/**
 * 
 * @author 
 */
public class AESEncoder {

	/**
	 * 使用AES/ECB/PKCS5Padding进行加密
	 * @param keyBytes
	 * @param dataBytes
	 * @return
	 * @throws Exception 
	 */
	public static byte[] encrypt(byte[] keyBytes, byte[] dataBytes) throws Exception {
		
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);

		return encrypt(cipher, dataBytes);
	}
	
	/**
	 * 使用AES/CBC/PKCS5Padding(向量)进行加密
	 * @param keyBytes
	 * @param ivBytes
	 * @param dataBytes
	 * @return
	 */
	public static byte[] encrypt(byte[] keyBytes, byte[] ivBytes, byte[] dataBytes) throws Exception {
		
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

		return encrypt(cipher, dataBytes);
	}
	
	/**
	 * 使用AES/ECB/PKCS5Padding进行解密
	 * @param keyBytes
	 * @param encryptedDataBytes
	 * @return
	 */
	public static byte[] decrypt(byte[] keyBytes, byte[] encryptedDataBytes) throws Exception {
		
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);

		return decrypt(cipher, encryptedDataBytes);
	}

	/**
	 * 使用AES/CBC/PKCS5Padding(向量)进行解密
	 * @param keyBytes
	 * @param ivBytes
	 * @param encryptedDataBytes
	 * @return
	 */
	public static byte[] decrypt(byte[] keyBytes, byte[] ivBytes, byte[] encryptedDataBytes) throws Exception {
		
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
		
		return decrypt(cipher, encryptedDataBytes);
	}
	
	private static byte[] encrypt(Cipher cipher, byte[] dataBytes) throws Exception {
		ByteArrayInputStream bIn = new ByteArrayInputStream(dataBytes);
		CipherInputStream cIn = new CipherInputStream(bIn, cipher);
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		int ch;
		while ((ch = cIn.read()) >= 0) {
			bOut.write(ch);
		}
		return bOut.toByteArray();
	}

	public static byte[] decrypt(Cipher cipher, byte[] encryptedDataBytes) throws Exception {
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		CipherOutputStream cOut = new CipherOutputStream(bOut, cipher);
		cOut.write(encryptedDataBytes);
		cOut.close();
		return bOut.toByteArray();
	}
	
	public static void main(String[] args) throws Exception {
		String sDemoMesage = "测试";
		byte[] demoMesageBytes = sDemoMesage.getBytes("UTF-8");

		// shared secret
		String key = "ASDFGHJKLzxcvbn6";
		// Initialization Vector - usually a random data, stored along with the
		// shared secret,
		// or transmitted along with a message.
		// Not all the ciphers require IV - we use IV in this particular sample
		String iv = "uvo&^HSLLSDnnH13";
		byte[] demoKeyBytes = key.getBytes();
		byte[] demoIVBytes = iv.getBytes();

		System.out.println("Demo Key (base64): " + new String(Base64.encodeBase64(demoKeyBytes)));
		System.out.println("Demo IV  (base64): " + new String(Base64.encodeBase64(demoIVBytes)));

		// ECB
		byte[] demo1EncryptedBytes = encrypt(demoKeyBytes, demoMesageBytes);
		System.out.println("Demo1 encrypted (base64): " + new String(Base64.encodeBase64(demo1EncryptedBytes)));
		byte[] demo1DecryptedBytes = decrypt(demoKeyBytes, demo1EncryptedBytes);
		System.out.println("Demo1 decrypted message : " + new String(demo1DecryptedBytes, "UTF-8"));
		
		// CBC
		byte[] demo2EncryptedBytes = encrypt(demoKeyBytes, demoIVBytes, demoMesageBytes);
		System.out.println("Demo2 encrypted (base64): " + new String(Base64.encodeBase64(demo2EncryptedBytes)));
		byte[] demo2DecryptedBytes = decrypt(demoKeyBytes, demoIVBytes, demo2EncryptedBytes);
		System.out.println("Demo2 decrypted message : " + new String(demo2DecryptedBytes, "UTF-8"));

	}
}

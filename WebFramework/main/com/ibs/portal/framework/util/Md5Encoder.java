package com.ibs.portal.framework.util;

public class Md5Encoder extends MessageDigestAdapter {

	public Md5Encoder() {
		super("MD5");
	}
	
	public static void main(String[] args) throws Exception{
		Md5Encoder m5 = new Md5Encoder();
		System.out.println(m5.encode("password")); // X03MO1qnZdYdgyfeuILPmQ==
	}
}

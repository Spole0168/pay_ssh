package com.ibs.portal.framework.share.util.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import com.ibs.portal.framework.share.util.ISerializer;

/**
 * 序列化接口适配，便于实现
 * 
 * @author 
 *
 */
public abstract class AbstractSerializer implements ISerializer {

	public byte[] serialize(Serializable obj) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		serialize(obj, bo);
		return bo.toByteArray();
	}

	public Serializable deserialize(byte[] data) throws IOException {
		return deserialize(new ByteArrayInputStream(data));
	}
	
	public void serialize(Serializable obj, File file) throws IOException {
		serialize(obj, new FileOutputStream(file));
	}

	public Serializable deserialize(File file) throws IOException {
		return deserialize(new FileInputStream(file));
	}

}

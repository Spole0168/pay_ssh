package com.ibs.portal.framework.share.util.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;


/**
 * Java默认的序列化方式实现
 *
 * @see java.io.ObjectOutputStream
 * @see java.io.ObjectInputStream
 * @author 
 *
 */
public class JavaSerializer extends AbstractSerializer {

	public Serializable deserialize(InputStream in) throws IOException {
		//ObjectInputStream oo = new ObjectInputStream(new GZIPInputStream(new BufferedInputStream(in)));
		ObjectInputStream oo = new ObjectInputStream(new BufferedInputStream(in));
		try {
			return (Serializable)oo.readObject();
		} catch (ClassNotFoundException e) {// 此异常是运行期不可恢复的错误，不应强制调用者捕获
			throw new IOException("序列化类文件找不到：" + e.getMessage());
		} finally {
			oo.close();
		}
	}

	public void serialize(Serializable obj, OutputStream out)
			throws IOException {
		//ObjectOutputStream oo = new ObjectOutputStream(new GZIPOutputStream(new BufferedOutputStream(out)));
		ObjectOutputStream oo = new ObjectOutputStream(new BufferedOutputStream(out));
		try {
			oo.writeObject(obj);
			oo.flush();
		} finally {
			oo.close();
		}
	}

}

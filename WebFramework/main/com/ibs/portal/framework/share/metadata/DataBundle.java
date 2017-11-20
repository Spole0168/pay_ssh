package com.ibs.portal.framework.share.metadata;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibs.portal.framework.server.domain.IEntity;

/**
 * 数据包.<br/>
 * 支持链式调用setter, 如:
 * <pre>
 * DataBundle data = new DataBundle().setInt(1).setString("aa").setFile(new File("C:/xx"));
 * </pre>
 *
 * @author 
 *
 */
public class DataBundle implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean bool;

	public boolean getBoolean() {
		return bool;
	}

	public DataBundle setBoolean(boolean bool) {
		this.bool = bool;
		return this;
	}

	private char ch;

	public char getChar() {
		return ch;
	}

	public DataBundle setChar(char ch) {
		this.ch = ch;
		return this;
	}

	private byte b;

	public byte getByte() {
		return b;
	}

	public DataBundle setByte(byte b) {
		this.b = b;
		return this;
	}

	private short s;

	public short getShort() {
		return s;
	}

	public DataBundle setShort(short s) {
		this.s = s;
		return this;
	}

	private int i;

	public int getInt() {
		return i;
	}

	public DataBundle setInt(int i) {
		this.i = i;
		return this;
	}

	private long l;

	public long getLong() {
		return l;
	}

	public DataBundle setLong(long l) {
		this.l = l;
		return this;
	}

	private float f;

	public float getFloat() {
		return f;
	}

	public DataBundle setFloat(float f) {
		this.f = f;
		return this;
	}

	private double d;

	public double getDouble() {
		return d;
	}

	public DataBundle setDouble(double d) {
		this.d = d;
		return this;
	}

	private Object object;

	public Object getObject() {
		return object;
	}

	public DataBundle setObject(Object object) {
		this.object = object;
		return this;
	}

	private String string;

	public String getString() {
		return string;
	}

	public DataBundle setString(String string) {
		this.string = string;
		return this;
	}

	private File file;

	public File getFile() {
		return file;
	}

	public DataBundle setFile(File file) {
		this.file = file;
		return this;
	}

	private Exception exception;

	public Exception getException() {
		return exception;
	}

	public DataBundle setException(Exception exception) {
		this.exception = exception;
		return this;
	}

	private List list = new ArrayList();

	public List getList() {
		return list;
	}

	public DataBundle setList(List list) {
		this.list = list;
		return this;
	}

	private Map map = new HashMap();

	public Map getMap() {
		return map;
	}

	public DataBundle setMap(Map map) {
		this.map = map;
		return this;
	}

	private Date date;

	private Long id;

	private String code;

	private String result;

	private IEntity entity;

	public Date getDate() {
		return date;
	}

	public DataBundle setDate(Date date) {
		this.date = date;
		return this;
	}

	public Long getId() {
		return id;
	}

	public DataBundle setId(Long id) {
		this.id = id;
		return this;
	}

	public String getCode() {
		return code;
	}

	public DataBundle setCode(String code) {
		this.code = code;
		return this;
	}

	public String getResult() {
		return result;
	}

	public DataBundle setResult(String result) {
		this.result = result;
		return this;
	}

	public IEntity getEntity() {
		return entity;
	}

	public DataBundle setEntity(IEntity entity) {
		this.entity = entity;
		return this;
	}

}

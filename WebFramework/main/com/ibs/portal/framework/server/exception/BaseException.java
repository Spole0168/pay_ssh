package com.ibs.portal.framework.server.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class BaseException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4364872269911149050L;

	/**
	 * 来源
	 */
	private int source;

	/**
	 * 严重程度
	 */
	private int severity;

	/**
	 * 代码
	 */
	private int code;

	// 错误参数
	private Object[] errorParameters;

	Throwable cause = null;

	public BaseException(String message, Throwable cause) {
		super(message);
		this.cause = cause;
	}

	public BaseException(String message, Throwable cause, int code,
			int source, int severity) {
		super(message);
		this.cause = cause;
		this.code = code;
		this.severity = severity;
		this.source = source;
	}

	public BaseException(String message, Throwable cause, int code) {
		super(message);
		this.cause = cause;
		this.code = code;
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause.getMessage());
		this.cause = cause;
	}

	public Throwable getReason() {
		return cause;
	}

	public BaseException() {
		super();
	}

	public void printStackTrace(PrintStream stream) {
		if (cause != null) {
			cause.printStackTrace(stream);
			stream.println(new StringBuffer("rethrow as exception ").append(
					this.getClass().getName()).toString());
		}
		super.printStackTrace(stream);
	}

	public void printStackTrace(PrintWriter writer) {
		if (cause != null) {
			cause.printStackTrace(writer);
			writer.println(new StringBuffer("rethrow as exception ").append(
					this.getClass().getName()).toString());
		}
		super.printStackTrace(writer);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public Object[] getErrorParameters() {
		return errorParameters;
	}

	public void setErrorParameters(Object[] errorParameters) {
		this.errorParameters = errorParameters;
	}
}

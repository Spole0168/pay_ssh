package com.ibs.portal.framework.server.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public abstract class ObjectStreamBaseAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2094913488589933734L;

	public abstract void setRequestData(InputStream requestObject,
			Map<String, String> requestParameters) throws IOException;

	public abstract Map<String, String> getResponseParameters();

	public abstract boolean hasResponseData();

	public abstract void sendResponseData(OutputStream os) throws IOException;

}

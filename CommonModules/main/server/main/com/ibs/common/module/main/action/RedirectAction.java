package com.ibs.common.module.main.action;

import com.ibs.portal.framework.server.action.BaseAction;

public class RedirectAction extends BaseAction {

	private static final long serialVersionUID = -227231529503190323L;

	private String redirectNamespace;
	private String redirectActionName;
	
	public String redirect() {
		return SUCCESS;
	}

	public String getRedirectNamespace() {
		return redirectNamespace;
	}

	public void setRedirectNamespace(String redirectNamespace) {
		this.redirectNamespace = redirectNamespace;
	}

	public String getRedirectActionName() {
		return redirectActionName;
	}

	public void setRedirectActionName(String redirectActionName) {
		this.redirectActionName = redirectActionName;
	}
}

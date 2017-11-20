package com.ibs.common.module.main.action;

import org.apache.commons.lang.xwork.StringUtils;

import com.ibs.portal.framework.browser.tags.I18nEl;
import com.ibs.portal.framework.server.action.BaseAction;

public class SuccessMessageAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6615621645794568041L;

	private String nextAction;
	private String successMessage;

	public String getNextAction() {
		return nextAction;
	}

	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	
	public String showSuccess() {
		if (StringUtils.isNotEmpty(successMessage)) {
			setSuccessMessage(I18nEl.i18n(successMessage));
		}
		return SUCCESS;
	}

}

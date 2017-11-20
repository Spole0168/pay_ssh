package com.ibs.common.module.frameworkimpl.notify.action;

import com.ibs.common.module.frameworkimpl.notify.dto.NotifyMessage;
import com.ibs.portal.framework.server.action.BaseAction;
import com.ibs.portal.framework.server.metadata.IPage;

public class NotifyAction extends BaseAction {

	private static final long serialVersionUID = -53817925704268185L;
	
	protected IPage<NotifyMessage> messages = null;
	protected static int NOTIFY_PAGE_SIZE = 10;
	
	public IPage<NotifyMessage> getMessages() {
		return messages;
	}
}

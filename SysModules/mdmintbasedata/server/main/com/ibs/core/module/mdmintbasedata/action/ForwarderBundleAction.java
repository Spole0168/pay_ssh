package com.ibs.core.module.mdmintbasedata.action;

import com.ibs.core.module.mdmbasedata.domain.ClientUpdateDataPack;
import com.ibs.core.module.mdmintbasedata.biz.IForwarderBiz;
import com.ibs.portal.framework.server.action.BaseDataBundleAction;
import com.ibs.portal.framework.share.metadata.DataBundle;

@SuppressWarnings("serial")
public class ForwarderBundleAction extends BaseDataBundleAction {
	private IForwarderBiz forwarderBiz;

	/**
	 /**
	 * 客户端更新数据使用
	 * 
	 * @return
	 */
	public String getServerData() {

		DataBundle requestData = this.getRequestDataBundle();
		ClientUpdateDataPack object = (ClientUpdateDataPack) requestData
				.getObject();

		super.setResponseDataBundle(new DataBundle().setObject(forwarderBiz
				.listAll(object)));
		return SUCCESS;
	}
	
	public void setForwarderBiz(IForwarderBiz forwarderBiz) {
		this.forwarderBiz = forwarderBiz;
	}

	public IForwarderBiz getForwarderBiz() {
		return forwarderBiz;
	}

}

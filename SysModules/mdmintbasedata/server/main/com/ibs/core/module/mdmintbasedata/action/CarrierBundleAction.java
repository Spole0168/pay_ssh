package com.ibs.core.module.mdmintbasedata.action;

import com.ibs.core.module.mdmbasedata.domain.ClientUpdateDataPack;
import com.ibs.core.module.mdmintbasedata.biz.ICarrierBiz;
import com.ibs.portal.framework.server.action.BaseDataBundleAction;
import com.ibs.portal.framework.share.metadata.DataBundle;

@SuppressWarnings("serial")
public class CarrierBundleAction extends BaseDataBundleAction {
	private ICarrierBiz carrierBiz;
	/**
	 * 客户端更新数据使用
	 * 
	 * @return
	 */
	public String getServerData() {
		logger.trace("enter Action...");
		DataBundle requestData = this.getRequestDataBundle();
		ClientUpdateDataPack object = (ClientUpdateDataPack) requestData
				.getObject();

		super.setResponseDataBundle(new DataBundle().setObject(carrierBiz
				.listAll(object)));
		return SUCCESS;
	}
	public ICarrierBiz getCarrierBiz() {
		return carrierBiz;
	}
	public void setCarrierBiz(ICarrierBiz carrierBiz) {
		this.carrierBiz = carrierBiz;
	}
}

/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.interfaces.biz;

import com.ibs.core.module.account.domain.CnlReqTrans;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_REQ_TRANS
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlReqTransInfDataInqueryBiz {

	public IPage<CnlReqTrans> findCnlReqTransByPage(QueryPage queryPage);

	public CnlReqTrans getCnlReqTransById(String id);
	
	public CnlReqTrans saveCnlReqTrans(CnlReqTrans object);
	
	public CnlReqTrans updateCnlReqTrans(CnlReqTrans object);
	
	public void exportCnlReqTrans(ExportSetting exportSetting);

}

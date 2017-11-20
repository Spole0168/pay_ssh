/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz;

import java.util.List;

import com.ibs.core.module.account.domain.CnlTrans3m;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_3M
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlTrans3mBiz {

	public IPage<CnlTrans3m> findCnlTrans3mByPage(QueryPage queryPage);

	public CnlTrans3m getCnlTrans3mById(String id);
	
	public CnlTrans3m saveCnlTrans3m(CnlTrans3m object);
	
	public CnlTrans3m updateCnlTrans3m(CnlTrans3m object);
	
	public void exportCnlTrans3m(ExportSetting exportSetting);
	
	public List<OptionObjectPair> getCnlSourceList();

}

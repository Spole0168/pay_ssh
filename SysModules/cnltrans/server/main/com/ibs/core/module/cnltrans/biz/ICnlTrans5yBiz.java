/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnltrans.biz;

import com.ibs.core.module.cnltrans.domain.CnlTrans5y;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_5Y
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlTrans5yBiz {

	public IPage<CnlTrans5y> findCnlTrans5yByPage(QueryPage queryPage);

	public CnlTrans5y getCnlTrans5yById(String id);
	
	public CnlTrans5y saveCnlTrans5y(CnlTrans5y object);
	
	public CnlTrans5y updateCnlTrans5y(CnlTrans5y object);
	
	public void exportCnlTrans5y(ExportSetting exportSetting);

}

/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.interfaces.biz;

import com.ibs.core.module.interfaces.domain.CnlMsg;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_MSG
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlMsgBiz {

	public IPage<CnlMsg> findCnlMsgByPage(QueryPage queryPage);

	public CnlMsg getCnlMsgById(String id);
	
	public CnlMsg saveCnlMsg(CnlMsg object);
	
	public CnlMsg updateCnlMsg(CnlMsg object);
	public String findByMsgCode(String msgCode,String cnlCnlCode);
	
	public void exportCnlMsg(ExportSetting exportSetting);

}

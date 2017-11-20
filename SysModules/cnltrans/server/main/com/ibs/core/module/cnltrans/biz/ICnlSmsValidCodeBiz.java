/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnltrans.biz;

import com.ibs.core.module.cnltrans.domain.CnlSmsValidCode;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_SMS_VALID_CODE
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlSmsValidCodeBiz {

	public IPage<CnlSmsValidCode> findCnlSmsValidCodeByPage(QueryPage queryPage);

	public CnlSmsValidCode getCnlSmsValidCodeById(String id);
	
	public CnlSmsValidCode saveCnlSmsValidCode(CnlSmsValidCode object);
	
	public CnlSmsValidCode updateCnlSmsValidCode(CnlSmsValidCode object);
	
	public void exportCnlSmsValidCode(ExportSetting exportSetting);

}

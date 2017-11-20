/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.cnltrans.dao;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.cnltrans.domain.CnlSmsValidCode;
import com.ibs.core.module.cnltrans.domain.CnlSmsValidCodeHis;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_SMS_VALID_CODE
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlSmsValidCodeDao {

	public IPage<CnlSmsValidCode> findCnlSmsValidCodeByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CnlSmsValidCode cnlSmsValidCode);

	public CnlSmsValidCode load(String id);
	
	public List<CnlSmsValidCode> findCnlSmsValidCode();
	
	public void saveCnlSmsValidCodeHis(Collection<CnlSmsValidCodeHis> cnlSmsValidCodeHisList);
	
	public boolean deleteCnlSmsValidCode();
	
}

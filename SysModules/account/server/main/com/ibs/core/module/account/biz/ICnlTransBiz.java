/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz;

import java.util.List;

import com.ibs.core.module.account.domain.CnlTrans;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlTransBiz {

	public IPage<CnlTrans> findCnlTransByPage(QueryPage queryPage);

	public CnlTrans getCnlTransById(String id);
	
	public CnlTrans saveCnlTrans(CnlTrans object);
	
	public CnlTrans updateCnlTrans(CnlTrans object);
	
	public void exportCnlTrans(ExportSetting exportSetting);
	
	public IPage<CnlTrans> findCnlTransByHql(QueryPage queryPage,CnlTrans cnlTrans);
	
	public List<CnlCnlCfg> getCnlCnlCodeVal(CnlCnlCfg cnlCnlCfg);

}

/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlmgr.biz;

import java.util.List;

import com.ibs.core.module.cnlmgr.domain.CnlSysIntf;
import com.ibs.core.module.cnlmgr.domain.CnlSysIntfCfg;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_SYS_INTF
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlSysIntfBiz {

	public IPage<CnlSysIntf> findCnlSysIntfByPage(QueryPage queryPage);

	public CnlSysIntf getCnlSysIntfById(String id);
	
	public CnlSysIntf saveCnlSysIntf(CnlSysIntf object);
	
	public CnlSysIntf updateCnlSysIntf(CnlSysIntf object);
	
	public void exportCnlSysIntf(ExportSetting exportSetting);

	public IPage<CnlSysIntfCfg> findSysIntf(QueryPage queryPage,String cnlCustCode,String accessCode,String currency);
	
	public CnlSysIntfCfg findSysIntfCfgbyId(String id);
	
	public void deleteSysIntfByid(CnlSysIntf cnlSysIntf);
	
	public  CnlSysIntf getIntf(String  cnlcnlcode);
	
	public CnlSysIntfCfg isExitAccessCode(String accessCode,String cnlCustCode);
	
	public List<CnlSysIntfCfg> findBycurrency();
	
	public CnlSysIntf findByAccessCode(String accessCode);
	
	public CnlSysIntfCfg isExitCurrency(String accessCode,String cnlCustCode);
}

/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.cnlmgr.dao;

import java.util.List;

import com.ibs.core.module.account.domain.CnlCommentDto;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CNL_CFG
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlCnlCfgDao {

	public IPage<CnlCnlCfg> findCnlCnlCfgByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CnlCnlCfg cnlCnlCfg);

	public CnlCnlCfg load(String id);
	
	public List<CnlCnlCfg> findBy(CnlCnlCfg cnlCnlCfg);
	
	public IPage<CnlCommentDto> findCommentByPage(QueryPage queryPage);
	
	public List<CnlCnlCfg> findAll();

	public CnlCnlCfg loadBy(String string, String cnlCnlCode);
	
	public List<CnlCnlCfg> getList();
	
	public String findCnlCustCodeByCnlCnlCode(String cnlCnlCode);
	
	public List<CnlCnlCfg> getCfgList();
	
	public List<CnlCnlCfg> checkSame(String cnlcnlcode);
	
	public String findByCnlName(String cnlSysName);
}

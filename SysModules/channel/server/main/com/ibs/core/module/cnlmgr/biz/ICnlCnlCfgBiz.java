/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlmgr.biz;

import java.util.List;

import com.ibs.core.module.account.domain.CnlCommentDto;
import com.ibs.core.module.cnlcust.domain.CnlCustCompany;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CNL_CFG
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlCnlCfgBiz {

	public IPage<CnlCnlCfg> findCnlCnlCfgByPage(QueryPage queryPage);

	public CnlCnlCfg getCnlCnlCfgById(String id);
	
	public CnlCnlCfg saveCnlCnlCfg(CnlCnlCfg object);
	
	public CnlCnlCfg updateCnlCnlCfg(CnlCnlCfg object);
	
	public void exportCnlCnlCfg(ExportSetting exportSetting);
	
	public IPage<CnlCommentDto> findCommentByPage(QueryPage queryPage);
	
	public CnlCommentDto saveCnlComment(CnlCommentDto cnlComment, String creator);
	
	public CnlCommentDto updateCnlComment(CnlCommentDto cnlComment, String updator);
	
	public CnlCommentDto getData(String id,String csiid,CnlCommentDto cnlComment);

	public CnlCommentDto getInfo(String cnlCustCode);

	public 	CnlCommentDto getCommentByCode(String id);

	/**
	 * 获取所有数据
	 * @return
	 */
	public List<CnlCnlCfg> findAll();
	
	/**
	 * 判断渠道编号是否存在
	 * @param cnlcnlcode
	 * @return
	 */
	public List<CnlCnlCfg> isSame(String cnlcnlcode);
	/**
	 * 判断渠道客户编码在cnl_cust_company 表中是否存在
	 * @param cnlCustCode
	 * @return
	 */
	public boolean existCnlCustCode(String cnlCustCode);
	
 
}

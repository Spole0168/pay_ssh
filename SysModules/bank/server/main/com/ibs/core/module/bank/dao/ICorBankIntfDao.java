/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.bank.dao;

import java.util.List;

import org.hibernate.Query;

import com.ibs.core.module.bank.domain.BankInfoDTO;
import com.ibs.core.module.bank.domain.CorBankInfoDTO;
import com.ibs.core.module.bank.domain.CorBankIntf;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_INTF
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorBankIntfDao {
	////
	public IPage<CorBankIntf> findCorBankIntfByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CorBankIntf corBankIntf);

	public CorBankIntf load(String id);
	
	//查询DTO
	public IPage<CorBankInfoDTO> findCorBankInfoByPageAndMoreTable(QueryPage queryPage);
	//银行信息添加用
	public CorBankInfoDTO findAddByPage(String  id,String bid)  ;
	
	public void saveCorBankInfoDTO(CorBankInfoDTO corBankInfoDTO);

//	public CorBankInfoDTO findInnerCode(String bankInnerCode);
	public BankInfoDTO findInnerCode(String id);

	//杜小林的方法
	public String findCode(String code);
	public CorBankIntf findByCode(String code);
	
	//
	public List<CorBankIntf> findAll();
	
	
	public List<CorBankIntf> checkBankInnerCode(String bankInnerCode);
	
	
	
}

/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.cnlmgr.dao;

import java.util.List;

import com.ibs.core.module.cnlmgr.domain.CnlSysIntf;
import com.ibs.core.module.cnlmgr.domain.CnlSysIntfCfg;
import com.ibs.core.module.cnlmgr.dto.CnlSysIntfIpLimitConditionDto;
import com.ibs.core.module.cnlmgr.dto.CnlSysIntfIpLimitDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_SYS_INTF
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlSysIntfDao {

	public IPage<CnlSysIntf> findCnlSysIntfByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CnlSysIntf cnlSysIntf);

	public CnlSysIntf load(String id);
	
	public  CnlSysIntf getIntf(String  cnlcnlcode);
	
	public CnlSysIntf findByCnlCode(String cnlCode, String serviceCode) ;

	public IPage<CnlSysIntfIpLimitDto> findCnlSysIntfByPage(QueryPage queryPage,
			CnlSysIntfIpLimitConditionDto cnlSysIntfIpLimitConditionDto);

	public CnlSysIntfCfg findSysIntfCfgbyId(String id);
	/*
	 * @created by	: xbh
	 * @version 	: 1.0
	 * @comments	: 支付限额列表
	 * @modify		: your comments goes here (author,date,reason).
	 */
	public IPage<CnlSysIntfCfg> findSysIntf(QueryPage queryPage, String cnlCustCode, String accessCode,
			String currency);

	public void deleteSysIntfByid(CnlSysIntf cnlSysIntf);
	/*
	 * @created by	: xbh
	 * @version 	: 1.0
	 * @comments	: 网关接入号与商户号是否匹配
	 * @modify		: your comments goes here (author,date,reason).
	 */
	public CnlSysIntfCfg isExitAccessCode(String accessCode,String cnlCustCode);
	/*
	 * @created by	: xbh
	 * @version 	: 1.0
	 * @comments	: 获取币种List
	 * @modify		: your comments goes here (author,date,reason).
	 */
	public List<CnlSysIntfCfg> findBycurrency();
	/*
	 * @created by	: xbh
	 * @version 	: 1.0
	 * @comments	: 根据网关接入号得到渠道系统信息
	 * @modify		: your comments goes here (author,date,reason).
	 */
	public CnlSysIntf findByAccessCode(String accessCode);

	public boolean checkCnlSysIntf(String cnlCustCode, String cnlIntfCode);
	/*
	 * @created by	: xbh
	 * @version 	: 1.0
	 * @comments	: 网关接入号与商户号是否匹配且币种不为空
	 * @modify		: your comments goes here (author,date,reason).
	 */
	public CnlSysIntfCfg isExitCurrency(String accessCode,String cnlCustCode);
	/*
	 * @created by	: xbh
	 * @version 	: 1.0
	 * @comments	: 根据渠道编号得到渠道系统信息
	 * @modify		: your comments goes here (author,date,reason).
	 */
	public CnlSysIntf findByCnlCnlCode(String cnlCnlCode);

	public boolean checkIpLimit(String cnlCustCode, String cnlIntfCode);

	public CnlSysIntf loadBy(String string, String cnlCnlCode);


}

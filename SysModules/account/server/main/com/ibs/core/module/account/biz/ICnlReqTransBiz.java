/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz;

import java.util.List;
import java.util.Map;

import com.ibs.core.module.account.domain.CnlReqTrans;
import com.ibs.core.module.account.domain.CnlReqTransDto;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_REQ_TRANS
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlReqTransBiz {

	public IPage<CnlReqTrans> findCnlReqTransByPage(QueryPage queryPage);

	public CnlReqTrans getCnlReqTransById(String id);
	
	public CnlReqTrans saveCnlReqTrans(CnlReqTrans object);
	
	public CnlReqTrans updateCnlReqTrans(CnlReqTrans object);
	
	public void exportCnlReqTrans(ExportSetting exportSetting);

	public IPage<CnlReqTransDto> findReqTranBizByMoreTable(QueryPage queryPage, String conditon);
	/**
	 * 通过一个字段查询
	 * 
	 * @param columName
	 * @param condition
	 * @return
	 */
	public List<CnlReqTrans> findCnlReqTransByOneCondition(String columName, String condition);

	public Map<String, Object> findDetail(String reqInnerNum, String reqType);

	public IPage<CnlReqTransDto> findBalance(QueryPage queryPage, String reqInnerNum);

}

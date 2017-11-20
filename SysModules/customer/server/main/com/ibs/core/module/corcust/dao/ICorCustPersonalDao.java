/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.corcust.dao;

import com.ibs.core.module.corcust.domain.CorCustPersonal;
import com.ibs.core.module.corcust.dto.CorCompound;
import com.ibs.core.module.corcust.dto.CorCondition;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST_PERSONAL
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorCustPersonalDao {

	public IPage<CorCustPersonal> findCorCustPersonalByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CorCustPersonal corCustPersonal);

	public CorCustPersonal load(String id);

	public CorCustPersonal getByCustCode(String custCode);

	public IPage<CorCompound> findCorCompoundByPage(QueryPage queryPage, CorCondition corCondition);

}

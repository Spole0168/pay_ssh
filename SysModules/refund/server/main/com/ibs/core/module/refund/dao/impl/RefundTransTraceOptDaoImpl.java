package com.ibs.core.module.refund.dao.impl;

import com.ibs.core.module.refund.dao.IRefundTransTraceOptDao;
import com.ibs.core.module.refund.domain.RefundTransTraceOpt;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;

public class RefundTransTraceOptDaoImpl extends BaseEntityDao<RefundTransTraceOpt> implements IRefundTransTraceOptDao{

	@Override
	public void saveOrUpdate(RefundTransTraceOpt refundTransTraceOpt) {
		super.saveOrUpdate(refundTransTraceOpt);
	}

}

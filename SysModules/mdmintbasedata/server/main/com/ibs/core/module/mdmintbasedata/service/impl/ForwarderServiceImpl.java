package com.ibs.core.module.mdmintbasedata.service.impl;

import java.util.List;

import com.ibs.core.module.mdmintbasedata.dao.IForwarderDao;
import com.ibs.core.module.mdmintbasedata.domain.Forwarder;
import com.ibs.core.module.mdmintbasedata.service.IForwarderService;
import com.ibs.portal.framework.server.service.BaseService;

public class ForwarderServiceImpl extends BaseService implements IForwarderService {
	
	private IForwarderDao forwarderDao;

	public List<Forwarder> findAll() {
		return forwarderDao.listAll();
	}

 

	public Forwarder getForwarderByCode(String forwarderCode) {
		return forwarderDao.getForwarderByCode(forwarderCode);
	}

 

	public IForwarderDao getForwarderDao() {
		return forwarderDao;
	}

	public void setForwarderDao(IForwarderDao forwarderDao) {
		this.forwarderDao = forwarderDao;
	}

	public List<Forwarder> list(String orgCode,String forwarderName,
			String forwarderCode,  String status,String used) {
		return forwarderDao.list(orgCode,forwarderName,
				 forwarderCode,   status, used);
	}

	
}

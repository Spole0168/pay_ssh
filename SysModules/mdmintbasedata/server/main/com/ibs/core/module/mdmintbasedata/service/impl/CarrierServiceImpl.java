package com.ibs.core.module.mdmintbasedata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmintbasedata.dao.ICarrierDao;
import com.ibs.core.module.mdmintbasedata.domain.Carrier;
import com.ibs.core.module.mdmintbasedata.service.ICarrierService;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.service.BaseService;

public class CarrierServiceImpl extends BaseService implements ICarrierService{
	
	private ICarrierDao carrierDao;

	public List<Carrier> findAll() {
	 
		return carrierDao.findAll();
	}

	public void setCarrierDao(ICarrierDao carrierDao) {
		this.carrierDao = carrierDao;
	}

	public ICarrierDao getCarrierDao() {
		return carrierDao;
	}

	public Carrier getCarrierByName(String carrierName) {
	 
		return carrierDao.getCarrierByName(carrierName);
	}

	public Carrier getCarrierByCode(String carrierCode) {
		return carrierDao.getCarrierByCode(carrierCode);
	}

  

	public List<Carrier> list(String carrierName, String carrierCode,
			String status) {
		return carrierDao.list(carrierCode, carrierName, status);
	}

	public List<OptionObjectPair> listOptions() {
		List<Carrier> alist = carrierDao.list(null,null,Constants.STATUS_VALID);
		List<OptionObjectPair> returnVal=new ArrayList<OptionObjectPair>();
		if(alist!=null)
		{
			for(Carrier  res:alist)
			{
				OptionObjectPair object=new OptionObjectPair();
				object.setKey(res.getCode());
				object.setValue(res.getName());				
				returnVal.add(object);
			}
		}
		
		return returnVal;
	}

}

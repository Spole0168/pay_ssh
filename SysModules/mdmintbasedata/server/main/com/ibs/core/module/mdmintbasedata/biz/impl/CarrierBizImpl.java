package com.ibs.core.module.mdmintbasedata.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.cglib.beans.BeanCopier;

import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.domain.ClientUpdateDataPack;
import com.ibs.core.module.mdmintbasedata.biz.ICarrierBiz;
import com.ibs.core.module.mdmintbasedata.dao.ICarrierDao;
import com.ibs.core.module.mdmintbasedata.domain.Carrier;
import com.ibs.core.module.mdmintbasedata.domain.CarrierSimple;
import com.ibs.core.module.mdmintbasedata.service.ICarrierService;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.QueryPage;

public class CarrierBizImpl extends BaseBiz implements ICarrierBiz {

	private ICarrierDao carrierDao;
	private ICarrierService carrierService;

	/**
	 * 客户端更新数据使用
	 * 
	 * @param object
	 *            客户端传过来的条件数据
	 * @return
	 */
	public List<?> listAll(ClientUpdateDataPack object) {

		return carrierDao.listAll(object);
	}

	public void setCarrierDao(ICarrierDao carrierDao) {
		this.carrierDao = carrierDao;
	}

	public ICarrierDao getCarrierDao() {
		return carrierDao;
	}

	public IPage<Carrier> findCarrierByPage(QueryPage queryPage) {

		return carrierDao.findCarrierByPage(queryPage);

	}

	public Carrier load(String id) {
		return carrierDao.load(id);
	}

	public List<Carrier> findAll() {

		return carrierDao.listAll();
	}

	public Carrier getCarrierByName(String carrierName) {

		return carrierDao.getCarrierByName(carrierName);
	}

	public Carrier getCarrierByCode(String carrierCode) {

		return carrierDao.getCarrierByCode(carrierCode);
	}

	public List<Carrier> list(String carrierCode, String carrierName,
			String status) {
		// listOptions();
		return carrierDao.list(carrierCode, carrierName, status);
	}

	public String save(Carrier carrier) {

		return carrierDao.save(carrier);
	}

	public void saveOrUpdate(Carrier carrier) {

		carrierDao.saveOrUpdate(carrier);
	}

	public void delete(Carrier carrier) {
		carrier.setStatus(Constants.STATUS_INVALID);
		carrierDao.saveOrUpdate(carrier);
	}

	public List<OptionObjectPair> listOptions() {

		return carrierService.listOptions();
	}

	public ICarrierService getCarrierService() {
		return carrierService;
	}

	public void setCarrierService(ICarrierService carrierService) {
		this.carrierService = carrierService;
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> findUniversal(Class<E> clz,
			String orgCodeOrNameOrPinYin, Integer records, String statusValid) {
		List<CarrierSimple> alist = carrierDao.findUniversal(
				orgCodeOrNameOrPinYin, records, statusValid);
		if (CarrierSimple.class.getName().equals(clz.getName()))
			return (List<E>) alist;

		List<E> result = new ArrayList<E>(alist.size());

		try {
			BeanCopier copy = BeanCopier
					.create(CarrierSimple.class, clz, false);
			Iterator<CarrierSimple> iter = alist.iterator();
			while (iter.hasNext()) {
				E dto = clz.newInstance();
				copy.copy(iter.next(), dto, null);
				result.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	 
	public IPage<Carrier> findCarrierNotInGroup(String orgCode,
			String priceType, int pageSize, int pageIndex,
			Map<String, String> sortMap) {
		 return carrierDao.findCarrierNotInGroup(orgCode, priceType, pageSize, pageIndex, sortMap);
	}

	public void changeStatus(String[] idStringArray, String status) {
		IUser user =  getCurrentUser();
		try {
			if(idStringArray.length>0){
				for(String chosenId : idStringArray){
					Carrier chosenCarrier =  carrierDao.load(chosenId);
					chosenCarrier.setModifyUserCode(user.getUserCode());
					chosenCarrier.setModifyTime(new Date());
					chosenCarrier.setUsed(status.toCharArray()[0]);
					if((chosenCarrier.getUsed()+"").equals(Constants.YES_OR_NO_Y)){
						chosenCarrier.setStatus(Constants.STATUS_VALID);
					}else{
						chosenCarrier.setStatus(Constants.STATUS_INVALID);
					}
					carrierDao.saveOrUpdate(chosenCarrier);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

}

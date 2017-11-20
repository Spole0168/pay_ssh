package com.ibs.core.module.mdmintbasedata.service;

import java.util.List;

import com.ibs.core.module.mdmintbasedata.domain.Carrier;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;

/**
 * 承运商对外服务类接口
 * @author Adair
 *
 */
public interface ICarrierService {

	/**
	 * 查找所有的承运商信息
	 * @return
	 */
	public List<Carrier> findAll();
	
	/**
	 * 根据名称查询承运商信息
	 * @param carrierName 承运商名称 
	 * @return 承运商
	 */
	public Carrier getCarrierByName(String carrierName);
	
	/**
	 * 根据承运商代码查询承运商
	 * @param carrierCode 承运商代码
	 * @return 承运商
	 */
	public Carrier getCarrierByCode(String carrierCode);
	
	/**
	 * 根据传入参数获取相应的数据 
	 * @param carrierName  承运商名称
	 * @param carrierCode  承运商代码
	 * @param status       承运商状态
	 * @return  承运商列表
	 */
	public List<Carrier> list(String carrierName,String carrierCode,String status);
	
	/**
	 * 查看所有启用的承运商
	 * @return List<OptionObjectPair> 用在下拉列表框
	 */
	public List<OptionObjectPair> listOptions();
}

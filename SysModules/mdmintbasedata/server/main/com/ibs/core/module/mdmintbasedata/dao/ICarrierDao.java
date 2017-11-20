package com.ibs.core.module.mdmintbasedata.dao;

import java.util.List;
import java.util.Map;

import com.ibs.core.module.mdmbasedata.domain.ClientUpdateDataPack;
import com.ibs.core.module.mdmintbasedata.domain.Carrier;
import com.ibs.core.module.mdmintbasedata.domain.CarrierSimple;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface ICarrierDao {
	/**
	 * 客户端更新数据使用
	 * 
	 * @param object
	 *            客户端传过来的条件数据
	 * @return
	 */
	public List<?> listAll(ClientUpdateDataPack object);
	
	/**
	 *	获得所有承运商信息
	 * @return
	 */
	public List<Carrier> findAll();
	
	/**
	 * 根据承运商名称查询信息
	 * @param carrierName
	 * @return
	 */
	public Carrier getCarrierByName(String carrierName);
	
	/**
	 * 根据承运商代码查询信息
	 * @param carrierCode
	 * @return
	 */
	public Carrier getCarrierByCode(String carrierCode);
	
	/**
	 * 根据条件模糊查询获得数据
	 * @param carrierCode 承运商代码  可空
	 * @param carrierName 承运商名称  可空
	 * @param status 承运商状态  
	 * @return
	 */
	public List<Carrier> list(String carrierCode,String carrierName,String status);
	
	/**
	 * 保存承运商
	 * @param carrier
	 * @return
	 */
	public String save(Carrier carrier);
	
	/**
	 * 保存或者更新
	 * @param carrier
	 */
	public void saveOrUpdate(Carrier carrier);
	
	/**
	 * 根据ID显示承运商信息
	 * @param id
	 * @return
	 */
	public Carrier load(String id);
	
	/**
	 * 根据ID删除承运商，逻辑删除
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 显示所有可用的item
	 * @return
	 */
	public List<Carrier> listAll();
	
	/**
	 * 分页显示承运商信息
	 * @param queryPage
	 * @return
	 */
	public IPage<Carrier> findCarrierByPage(QueryPage queryPage);

	public List<CarrierSimple> findUniversal(String orgCodeOrNameOrPinYin,
			Integer records, String statusValid);

	public IPage<Carrier> findCarrierNotInGroup(String orgCode,
			String priceType, int pageSize, int pageIndex,
			Map<String, String> sortMap);
	
}

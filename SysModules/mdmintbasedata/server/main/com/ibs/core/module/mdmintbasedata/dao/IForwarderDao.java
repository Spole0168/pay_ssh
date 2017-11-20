package com.ibs.core.module.mdmintbasedata.dao;

import java.util.List;
import java.util.Map;

import com.ibs.core.module.mdmbasedata.domain.ClientUpdateDataPack;
import com.ibs.core.module.mdmintbasedata.domain.Forwarder;
import com.ibs.core.module.mdmintbasedata.domain.ForwarderSimple;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IForwarderDao {
	/**
	 * 客户端更新数据使用
	 * 
	 * @param object
	 *            客户端传过来的条件数据
	 * @return
	 */
	public List<?> listAll(ClientUpdateDataPack object);
	
	/**
	 *	获得所有货代信息
	 * @return
	 */
	public List<Forwarder> findAll();
	
  
	/**
	 * 根据货代代码查询信息
	 * @param ForwarderCode
	 * @return
	 */
	public Forwarder getForwarderByCode(String forwarderCode);
	
	/**
	 * 根据条件模糊查询获得数据
	 * @param  orgCode      组织机构code，不可为空
	 * @param ForwarderCode 货代代码  可空
	 * @param ForwarderName 货代名称  可空
	 * @param status 货代状态  
	 * @param used 是否启用
	 * @return
	 */
	public List<Forwarder> list(String orgCode,String forwarderCode,String forwarderName,String status,String used);
	
	/**
	 * 保存货代
	 * @param Forwarder
	 * @return
	 */
	public String save(Forwarder forwarder);
	
	/**
	 * 保存或者更新
	 * @param Forwarder
	 */
	public void saveOrUpdate(Forwarder forwarder);
	
	/**
	 * 根据ID显示货代信息
	 * @param id
	 * @return
	 */
	public Forwarder load(String id);
	
	/**
	 * 根据ID删除货代，逻辑删除
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 显示所有可用的item
	 * @return
	 */
	public List<Forwarder> listAll();
	
	/**
	 * 分页显示货代信息
	 * @param queryPage
	 * @return
	 */
	public IPage<Forwarder> findForwarderByPage(QueryPage queryPage);
	
	
	/**
	 * 生成该组织下的国际件货代代码
	 * @param orgCode
	 * @return
	 */
	public String genUserCode(String orgCode);

	public  List<ForwarderSimple>  findUniversal(String orgCode,
			String orgCodeOrNameOrPinYin, Integer records, String statusValid,
			String inused);
	public IPage<Forwarder> findForwarderNotInGroup(String operateOrgCode,
			String priceType, int pageSize, int pageIndex,
			Map<String, String> sortMap);
	
}

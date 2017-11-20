package com.ibs.core.module.mdmintbasedata.service;

import java.util.List;

import com.ibs.core.module.mdmintbasedata.domain.Forwarder;
/**
 * 货代对外服务接口
 * @author Adair
 *
 */

public interface IForwarderService {
	/**
	 * 查找所有的货代信息
	 * @return
	 */
	public List<Forwarder> findAll();
	
 
	
	/**
	 * 根据货代代码查询货代
	 * @param ForwarderCode 货代代码
	 * @return 货代
	 */
	public Forwarder getForwarderByCode(String forwarderCode);
	
	/**
	 * 根据传入参数查询货代信息
	 * @param orgCode  所属国际部code
	 * @param forwarderName 货代名称
	 * @param forwarderCode 货代代码
     * @param status  状态  VALID INVALID 
	 * @param used    是否启用 Y  N
	 * @return 货代信息列表
	 */
	public List<Forwarder> list(String orgCode,String forwarderName,String forwarderCode,String status,String used);
	
	
}

package com.ibs.core.module.mdmintbasedata.biz;

import java.util.List;
import java.util.Map;

import com.ibs.core.module.mdmbasedata.domain.ClientUpdateDataPack;
import com.ibs.core.module.mdmintbasedata.domain.Forwarder;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IForwarderBiz {
	/**
	 * 客户端更新数据使用
	 * 
	 * @param object
	 *            客户端传过来的条件数据
	 * @return
	 */
	public List<?> listAll(ClientUpdateDataPack object);
	
	/**
	 * get business type in page according to query page setting 
	 * which can add sort,alias and etc functions.
	 * @param queryPage
	 * @return
	 */
	public IPage<Forwarder> findForwarderByPage(QueryPage queryPage);
	
	public Forwarder load(String id);
	
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
	 * @param orgCode  所属国际部code
	 * @param ForwarderCode 货代代码  可空
	 * @param ForwarderName 货代名称  可空
	 * @param status  状态  VALID INVALID 
	 * @param used    是否启用 Y  N
	 * @return
	 */
	public List<Forwarder> list(String orgCode ,String forwarderCode,String forwarderName,String status,String used);
	
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
	 * 删除货代，逻辑删除
	 * @param id
	 */
	public void delete(Forwarder forwarder);
	
	public int checkForwardername(String name,String id);

	public <E> List<E>  findUniversal(
			Class<E> clz, String orgCode,
			String orgCodeOrNameOrPinYin, Integer records, String statusValid,
			String inused);
	public IPage<Forwarder> findForwarderNotInGroup(String operateOrgCode,
			String priceType, int pageSize, int pageIndex,
			Map<String, String> sortMap);
}

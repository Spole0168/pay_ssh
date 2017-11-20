package com.ibs.core.module.mdmintbasedata.biz.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.cglib.beans.BeanCopier;

import com.ibs.common.module.frameworkimpl.security.constant.RoleConst;
import com.ibs.common.module.frameworkimpl.security.domain.User;
import com.ibs.common.module.frameworkimpl.security.service.IPermissionService;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.domain.ClientUpdateDataPack;
import com.ibs.core.module.mdmintbasedata.biz.IForwarderBiz;
import com.ibs.core.module.mdmintbasedata.dao.IForwarderDao;
import com.ibs.core.module.mdmintbasedata.domain.Forwarder;
import com.ibs.core.module.mdmintbasedata.domain.ForwarderSimple;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

public class ForwarderBizImpl extends BaseBiz implements IForwarderBiz{
	private IForwarderDao forwarderDao;
	private Forwarder forwarder;
	private IPermissionService permissionService;

	/**
	 * 客户端更新数据使用
	 * 
	 * @param object
	 *            客户端传过来的条件数据
	 * @return
	 */
	public List<?> listAll(ClientUpdateDataPack object) {
		
		return forwarderDao.listAll(object);
	}

	public void setForwarderDao(IForwarderDao forwarderDao) {
		this.forwarderDao = forwarderDao;
	}

	public IForwarderDao getForwarderDao() {
		return forwarderDao;
	}

	public IPage<Forwarder> findForwarderByPage(QueryPage queryPage) {
		logger.trace("entering Biz...");
		
		return forwarderDao.findForwarderByPage(queryPage);
		
	}
	
	private String generateCode(String orgCode)
	{
		  return forwarderDao.genUserCode(orgCode);
		 
	}
	public Forwarder load(String id) {
		return forwarderDao.load(id);
	}

	public List<Forwarder> findAll() {
		
		return forwarderDao.findAll();
	}
 
	public Forwarder getForwarderByCode(String forwarderCode) {
		
		return forwarderDao.getForwarderByCode(forwarderCode);
	}

	public List<Forwarder> list(String orgCode,String forwarderCode, String forwarderName,
			String status,String used) {
		
		return forwarderDao.list(orgCode,forwarderCode, forwarderName, status,used);
	}

	public String save(Forwarder forwarder) {
		String uuid=StringUtils.getUUID();
		forwarder.setId(uuid);
		forwarder.setCode(generateCode(forwarder.getOrgCode()));
		forwarderDao.save(forwarder); 
//		permissionService.createUser(uuid, forwarder.getCode(), forwarder.getOrgCode(), User.USER_TYPE_INTAGENT, RoleConst.defaultIAgentRoleCode);
		permissionService.createUser(uuid, forwarder.getCode(), User.USER_TYPE_INTAGENT, RoleConst.defaultIAgentRoleCode);
		return uuid;
	}

	public void saveOrUpdate(Forwarder forwarder) {
		
		forwarderDao.saveOrUpdate(forwarder);
	}

	public void delete(Forwarder forwarder) {
		forwarder.setStatus(Constants.STATUS_INVALID);
		forwarder.setUsed(Constants.UNUSED);
		permissionService.writeOffUser(forwarder.getCode());
		forwarderDao.saveOrUpdate(forwarder);
	}

	public Forwarder getForwarder() {
		return forwarder;
	}

	public void setForwarder(Forwarder forwarder) {
		this.forwarder = forwarder;
	}

	public IPermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public int checkForwardername(String name, String id) {
		QueryPage queryPage=new QueryPage(2,0);
//		queryPage.addEqualSearch("orgCode", orgCode);
		queryPage.addEqualSearch("name", name);
		queryPage.addEqualSearch("status",Constants.STATUS_VALID);
		if(StringUtils.isNotEmpty(id))
		{
			queryPage.addNotEqualSearch("id", id);
		}
		IPage<Forwarder> alist = forwarderDao.findForwarderByPage(queryPage);
//		ClientUpdateDataPack object=new ClientUpdateDataPack();
//		object.setOrgCode(orgCode);
//		object.setVersionNo(1L);
//		this.listAll(object); 
		if(alist!=null)
		{
			return alist.getRecords();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public <E> List<E>  findUniversal(
			Class<E> clz, String orgCode,
			String orgCodeOrNameOrPinYin, Integer records, String statusValid,
			String inused) {
		
		List<ForwarderSimple> alist = forwarderDao.findUniversal(orgCode,orgCodeOrNameOrPinYin,records,statusValid,inused); 
		if (ForwarderSimple.class.getName().equals(clz.getName()))
			return (List<E>) alist;
		
		List<E> result = new ArrayList<E>(alist.size());

		try {
			BeanCopier copy = BeanCopier
					.create(ForwarderSimple.class, clz, false); 
			Iterator<ForwarderSimple> iter = alist.iterator();
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
	
	public IPage<Forwarder> findForwarderNotInGroup(String operateOrgCode,
			String priceType, int pageSize, int pageIndex,
			Map<String, String> sortMap)
			{
		     return forwarderDao.findForwarderNotInGroup(operateOrgCode, priceType, pageSize, pageIndex, sortMap);
			}

}

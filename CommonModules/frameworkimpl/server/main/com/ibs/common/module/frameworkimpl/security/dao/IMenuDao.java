package com.ibs.common.module.frameworkimpl.security.dao;

import java.util.List;
import java.util.Map;

import com.ibs.common.module.frameworkimpl.security.domain.Menu;
import com.ibs.common.module.frameworkimpl.security.dto.RoleDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.server.metadata.SystemMenu;

public interface IMenuDao {
	public String save(Menu menu);

	public void saveOrUpdate(Menu menu);

	public Menu load(String id);

	public Menu loadCascade(String id);

	public void delete(String id);
	
	public List<Menu> getAllMenuByApplicationId(String applicationId);
	
	public List<Menu> getAllMenuByApplicationIdAndLevel(String applicationId,int level);
	
	public List<Menu> getSubMenuByParentId(String parentId);
	
	public Integer getSubMenuCountByParentId(String parentId);
	
	public boolean userHasOperation(String userId, String operId);
	
	public Menu loadByTypeAndValue(final String[] menuTypes,
			final String location);
	
	public Menu loadByTypeAndName(final String menuType,
			final String menuName);
	
	public List<Menu> getAllMenu();
	
	public IPage<Menu> getOperListByPage(QueryPage page);
	
	public IPage<Menu> getMenuByRoleByPage(String roleId, QueryPage page, String [] type);
	
	public String saveOrUpdateMenu(final String menuId, final String menuName,
			final String menuTitle, final String menuTitleEn,
			final String location, final String description,
			final Integer displayOrder, final String target,
			final String parentMenuId, final String appId, final String menuType);
	
	public List<Menu> getAllMenuByLevel(int level);
	
	public List<String> getOperRelatedRoleIds(final String operId);
	
	public Menu getRestrictResourceByValue(String value);
	
	public List<Menu> searchMenuByTitleWithParent(String str);
	
	/**
	 * 查询管理的菜单，支持根据角色列表以及层次等信息
	 * @param roleCodes 
	 * @param level 查询菜单层次
	 * @param parentMenuId 查询某一菜单的下级菜单
	 * @return
	 */
	public List<Menu> getMenuByRoleCodes(List<String> roleCodes, Integer level,
			String parentMenuId,List<String> menuTypes);
	
	public List<SystemMenu> findMenuByRoleIds(List<String> roleIds);
	
	public List<SystemMenu> findGuiMenuByRoleIds(List<String> roleIds);
	
	public List<SystemMenu> findOperationByRoleIds(List<String> roleIds);
	
	public void updateLevels(String menuId,int levels);
	
	public List<String> getUserIdListByMenuId(String menuId);
	
	public IPage<RoleDto> getRoleByMenu(String menuId,
			int pageSize, int pageIndex, Map<String, String> sortMap);
	
	/**
	 * 根据菜单名称和类型查找菜单
	 * @param name 
	 * @param type
	 * @return
	 */
	public List<Menu> findMenuByParm(String menuName,String menuType);
	
	public Menu getMenuByMenuName(String menuName);
	
	public Menu getMenuByMenuTitle(String menuTitle);

}
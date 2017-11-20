package com.ibs.common.module.frameworkimpl.security.dao;

import java.util.List;
import java.util.Map;

import com.ibs.common.module.frameworkimpl.security.domain.Mac;
import com.ibs.common.module.frameworkimpl.security.dto.MacDto;
import com.ibs.common.module.frameworkimpl.security.dto.MacInfoDto;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IMacDao {
	public Page<MacDto> findMacByPage(QueryPage queryPage, Map<String, String> searchFields);
	
	public Page<MacDto> findMacMenuByPage(QueryPage queryPage, Map<String, String> searchFields);
	
	public Page<MacDto> findMacUserByPage(QueryPage queryPage, Map<String, String> searchFields);

	public Mac getMacById(String id);
	
	public Mac getMacByAddress(String mac);
	
	public Mac getMacBy(String mac, String cpuId);

	public String saveMac(Mac mac);

	public void updateMac(Mac mac);

	public void delete(String id);
	
	public List<Mac> getAllValidMacAddress();

	public List<Mac> getMacIdsByMenuId(String menuId);
	
	public List<MacInfoDto> getMacInfosByMenuId(String menuId);
}

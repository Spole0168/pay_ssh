package com.ibs.core.module.mdmbasedata.dao;

import java.util.List;

import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IDataDictDao {
	public String save(DataDict dataDict);

	public void saveOrUpdate(DataDict dataDict);

	public DataDict load(String id);
	
	public void delete(String id);

	/**
	 * 不显示停用的item
	 * 
	 * @param type 类型
	 * @param language 语言
	 * @return
	 */
	public List<DataDict> list(String type,String language);	

//	public IPage<DataDict> findDataDictByPage(String type, String code,
//			String name, String status, int pageSize, int pageIndex);

	public IPage<DataDict> findDataDictByPage(QueryPage queryPage);

	public String getCodeName(String type, String code,String language);

	public List<DataDict> getDataListByPage(QueryPage page);
	
	//public List<DataDict> findDataDictsByCodes(Collection<String> cols);
}

package com.ibs.core.module.mdmbasedata.biz;

import java.util.List;
import java.util.Map;

import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IDataDictBiz {
	
	/**
	 * 添加数据字典数据
	 * @param  DataDict 对象
	 * @return
	 */
	public String save(DataDict object);

	/**
	 * 更新数据字典数据
	 * @param object  DataDict 对象
	 */
	public void saveOrUpdate(DataDict object);

//	/**
//	 * 删除数据字典数据 （逻辑删除）
//	 * @param id 数据唯一ID
//	 */
//	public void delete(String id);

	/**
	 * 加载数据对象
	 * @param id 数据唯一ID
	 * @return DataDict 对象
	 */
	public DataDict load(String id);

	 
  

	/**
	 * 根据输入的条件， 查询数据字典详细信息 
	 * @param type
	 * @param code
	 * @param name
	 * @param status
	 * @param pageSize
	 * @param pageIndex
	 * @return IPage<DataDict>  放在IPage里面的数据，包含分页信息
	 */
//	public IPage<DataDict> findDataDictByPage(String type, String code,
//			String name, String status, int pageSize, int pageIndex);

	public IPage<DataDict> findDataDictByPage(QueryPage queryPage);

	public void deleteDataDict(String[] idStringArray);

	/**
	 * 查询系统支持的语言
	 * @param needDefault 是否需要默认值
	 * @return
	 */
	public List<OptionObjectPair> findSupportLangeage(boolean needDefault);

	public int checkValid(String language,String type,String checkParm, String checkContent,String id);
	
	public void exportDataDict(ExportSetting exportSetting, Map<String, String> dictTypeMap);
	
 
	public Map<String, DataDict>  listMap(String type);
	
	public List<OptionObjectPair> listOptions(String type);
}

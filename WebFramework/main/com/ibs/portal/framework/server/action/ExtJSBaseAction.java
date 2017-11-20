package com.ibs.portal.framework.server.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.PageCache;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.BeanUtils;
import com.ibs.portal.framework.util.StringUtils;
import com.opensymphony.xwork2.ActionContext;

public class ExtJSBaseAction<T> extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2114802374736799234L;

	//已选择的ID数组
	private String[] selectIds;

	private String callback; 
	
	//页号
	private int start;
	//页大小
	private int limit;
	//排序的字段
	private String sort;
	// 查询排序的方式，可能的值是ASC和DESC
	private String dir;
	
	protected Page<T> result;
	private Map<String, String> searchFields = new HashMap<String, String>(0);
	private Map<String, String[]> searchArrays = new HashMap<String, String[]>(0);
	
	//分页参数对象
	protected QueryPage queryPage;
	
	//页面缓存
	protected PageCache pageCache = new PageCache();

	@SuppressWarnings("unchecked")
	public void populateJqGridData() {
		// 是否导航到Session缓存的页面
		String isPageCache = null; 

		Map<String, String[]> parameters = ServletActionContext.getRequest()
				.getParameterMap();
		Set<String> keys = parameters.keySet();
		for (String key : keys) {
			String[] values = parameters.get(key);

			if ("sort".equals(key)) {
				if (null != values && values.length > 0)
					sort = values[0];
			} else if ("dir".equals(key)) {
				if (null != values && values.length > 0)
					dir = values[0];
			} else if ("callback".equals(key)) {
				if (null != values && values.length > 0)
					callback = values[0];
			} else if ("start".equals(key)) {
				if (null != values && values.length > 0)
					start = Integer.parseInt(values[0]);
			} else if ("limit".equals(key)) {
				if (null != values && values.length > 0)
					limit = Integer.parseInt(values[0]);
			} else if ("pageCache".equals(key)) {
				if (null != values && values.length > 0)
					isPageCache = values[0];
			}else if ("id".equals(key)) {
				if (null != values && values.length > 0) {
					selectIds = values[0].split(",");
				}
			} else if (key.endsWith("Array")) {
				// 数组参数
				searchArrays.put(key, values);
			} else {
				// 其他参数
				// System.out.println("####################key:" + key +
				// ",values:" + values);
				if (null != values && values.length > 0)
					searchFields.put(key, values[0]);
				// System.out.println("####################key:" + key +
				// ",value:" + values[0]);
			}

		}

		try {
			IUser su = getCurrentUser();
			if (null != su) {
				//String actionName = ActionContext.getContext().getName();
				String actionName = this.getClass().getName();
				
				//如果没有设置当作不需要缓存
				if(StringUtils.isEmpty(isPageCache)){
					return;
				}
				// 需要缓存当前页值
				else if ("true".equals(isPageCache)) {
					setPageProperties();
					
					su.putPageCache(actionName, pageCache);				
				} 
				//设置不缓存当前页
				else if ("false".equals(isPageCache)){
					//直接读取缓存页值
					pageCache = su.getPageCache(actionName);
					if(null!=pageCache) BeanUtils.populate(this, pageCache.getPageProperties());
					//BeanUtils.copyProperties(this,pageCache);
	
				}
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public Map<String, String[]> getSearchArrays() {
		return searchArrays;
	}

	public void setSearchArrays(Map<String, String[]> searchArrays) {
		this.searchArrays = searchArrays;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public void setResult(Page<T> result) {
		if (result.getTotal() < result.getPage()) {
			result.setPage(result.getTotal());
		} else {
			result.setPage(result.getPage()+1); // jqgrid 当前页从1开始
		}
		result.setCallback(callback);
		this.result = result;
	}

	public Page<T> getResult() {
		return result;
	}
	
	public Map<String, String> getSearchFields() {
		return searchFields;
	}

	public void setSearchFields(Map<String, String> searchFields) {
		this.searchFields = searchFields;
	}



	public String[] getSelectIds() {
		return selectIds;
	}



	public void setSelectIds(String[] selectIds) {
		this.selectIds = selectIds;
	}



	public int getStart() {
		return start;
	}



	public void setStart(int start) {
		this.start = start;
	}



	public int getLimit() {
		return limit;
	}



	public void setLimit(int limit) {
		this.limit = limit;
	}



	public String getSort() {
		return sort;
	}



	public void setSort(String sort) {
		this.sort = sort;
	}



	public String getDir() {
		return dir;
	}



	public void setDir(String dir) {
		this.dir = dir;
	}

	protected void setPageProperties(){
		pageCache.putPageProperties("callback", callback);
		pageCache.putPageProperties("start", start);
		pageCache.putPageProperties("limit", limit);
		pageCache.putPageProperties("dir", dir);
		pageCache.putPageProperties("sort", sort);
	}
	
}

package com.ibs.portal.framework.server.action;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.PageCache;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.BeanUtils;
import com.ibs.portal.framework.util.StringUtils;

public class JqGridBaseAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2114802374736799234L;

	//已选择的ID数组
	private String[] selectIds;
	// 是否是查询，值是true或者false
	private boolean _search; 
	// 每页显示的记录条数
	private int rows; 
	// 查询第几页的数据
	private int page;
	// 步长
	private int rowNum;
	
	private String nd;
	// 查询排序的条件
	private String sidx;
	// 查询排序的方式，可能的值是ASC和DESC
	private String sord;
	protected Page result;
	protected Map<String, String> searchFields = new HashMap<String, String>(0);
	protected Map<String, String[]> searchArrays = new HashMap<String, String[]>(
			0);
	
	//分页参数对象
	protected QueryPage queryPage;
	
	//页面缓存
	protected PageCache pageCache = new PageCache();

	@SuppressWarnings("unchecked")
	public void populateJqGridData() {
		// 是否导航到缓存的页面
		String isPageCache = ServletActionContext.getRequest().getParameter("pageCache");
		String loadPageCache = ServletActionContext.getRequest().getParameter("loadPageCache");
		
		Map<String, String[]> parameters = ServletActionContext.getRequest()
				.getParameterMap();
		Set<String> keys = parameters.keySet();
		for (String key : keys) {
			String[] values = parameters.get(key);

			if ("sidx".equals(key)) {
				if (null != values && values.length > 0)
					sidx = values[0];
			} else if ("sord".equals(key)) {
				if (null != values && values.length > 0)
					sord = values[0];
			} else if ("_search".equals(key)) {
				if (null != values && values.length > 0)
					_search = Boolean.parseBoolean(values[0]);
			} else if ("rows".equals(key)) {
				if (null != values && values.length > 0)
					rows = Integer.parseInt(values[0]);
			} else if ("page".equals(key)) {
				if (null != values && values.length > 0)
					try{ // 处理因为输入的翻页为非法字符时，报错的问题
						page = Integer.parseInt(values[0].trim()) - 1; // 从0开始
					}catch(NumberFormatException e){
					}
			} else if ("pageCache".equals(key) || "loadPageCache".equals(key)) {
				
//			} else if ("pageCache".equals(key)) {
//				if (null != values && values.length > 0)
//					isPageCache = values[0];
			} else if ("nd".equals(key)) {
				if (null != values && values.length > 0)
					nd = values[0];
			} else if ("rowNum".equals(key)) {
				if (null != values && values.length > 0) {
					rowNum = Integer.parseInt(values[0]);
			}
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
			IUser su = UserContext.getUserContext().getCurrentUser();
			if (null != su) {
				//String actionName = ActionContext.getContext().getName();
				String actionName = this.getClass().getName();
				
				//如果没有设置当作不需要缓存
				if(StringUtils.isEmpty(isPageCache)){
					return;
				}
				// 需要缓存当前页值
				else if ("true".equals(isPageCache)) {
					
					if (StringUtils.isNotEmpty(loadPageCache) && loadPageCache.equals("true")) {
						// load from cache
						PageCache oldCache = su.getPageCache(actionName);
						if (oldCache != null) {
							Map<String, Object> maps = oldCache.getPageProperties();
							
							Set<String> fields = new HashSet<String>();
							fields.add("sidx");
							fields.add("_search");
							fields.add("rows");
							fields.add("page");
							fields.add("pageCache");
							fields.add("loadPageCache");
							fields.add("nd");
							fields.add("rowNum");
							fields.add("id");
							
							for (Map.Entry<String, Object> entry : maps.entrySet()) {
								try {
									BeanUtils.copyProperty(this, entry.getKey(), entry.getValue());
									if (!fields.contains(entry.getKey()))
										searchFields.put(entry.getKey(), entry.getValue().toString());
								} catch (Exception e) {}
								// support ModelDriven
								try {
									Method mth = this.getClass().getMethod("getModel", null);
									Object model = mth.invoke(this);
									try {
										BeanUtils.copyProperty(model, entry.getKey(), entry.getValue());
										if (!fields.contains(entry.getKey()))
											searchFields.put(entry.getKey(), entry.getValue().toString());
									} catch(Exception e) {}
								} catch(Exception e) {}
							}
						}
					} else if (StringUtils.isEmpty(loadPageCache) || !loadPageCache.equals("true")){
						// save page cache
						// action fields
						BeanInfo bi = Introspector.getBeanInfo(this.getClass());
						PropertyDescriptor[] pds = bi.getPropertyDescriptors();
						for (PropertyDescriptor pd : pds) {
							if (BeanUtils.isPrimitiveOrLangType(pd.getPropertyType())) {
								if (parameters.containsKey(pd.getName())) {
									pageCache.putPageProperties(pd.getName(), pd.getReadMethod().invoke(this));
								}
							}
						}
						// support ModelDriven
						try {
							Method mth = this.getClass().getMethod("getModel", null);
							Object model = mth.invoke(this);
							BeanInfo mBi = Introspector.getBeanInfo(model.getClass());
							
							PropertyDescriptor[] mPds = mBi.getPropertyDescriptors();
							for (PropertyDescriptor mPd : mPds) {
								if (BeanUtils.isPrimitiveOrLangType(mPd.getPropertyType())) {
									if (parameters.containsKey(mPd.getName())) {
										pageCache.putPageProperties(mPd.getName(), mPd.getReadMethod().invoke(model));
									}
								}
							}
						} catch(Exception e) {}
						// searchFields
						for (Map.Entry<String, String> entry : searchFields.entrySet()) {
							pageCache.putPageProperties(entry.getKey(), entry.getValue());
						}
						
						setPageProperties();
						
						su.putPageCache(actionName, pageCache);
					}
					
				}
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
	
	

	public Map<String, String[]> getSearchArrays() {
		return searchArrays;
	}

	public void setSearchArrays(Map<String, String[]> searchArrays) {
		this.searchArrays = searchArrays;
	}

	public boolean is_Search() {
		return _search;
	}

	public void set_Search(boolean _search) {
		this._search = _search;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public <T>Page getResult() {
		return this.result;
	}

	public  <T> void setResult(Page<T> result) {
		if(result == null){
			result = new Page<T>(new ArrayList(), 0, 0, 0);
		}
		if (result.getTotal() <= result.getPage()) {
			result.setPage(result.getTotal());
		} else {
			result.setPage(result.getPage()+1); // jqgrid 当前页从1开始
		}
		this.result = result;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
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

	protected void setPageProperties(){
		/*
		pageCache.putPageProperties("rows", rows);
		pageCache.putPageProperties("page", page);
		pageCache.putPageProperties("rowNum", rowNum);
		pageCache.putPageProperties("nd", nd);
		pageCache.putPageProperties("sidx", sidx);
		pageCache.putPageProperties("sord", sord);
		*/
	}



	public void setQueryPage(QueryPage queryPage) {
		this.queryPage = queryPage;
	}

	public QueryPage getQueryPage() {
		return queryPage;
	}
	
}

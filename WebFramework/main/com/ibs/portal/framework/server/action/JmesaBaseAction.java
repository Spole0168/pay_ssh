package com.ibs.portal.framework.server.action;

import org.jmesa.limit.Limit;
import org.jmesa.limit.LimitFactory;
import org.jmesa.limit.LimitFactoryImpl;
import org.jmesa.limit.Order;
import org.jmesa.limit.RowSelect;
import org.jmesa.limit.Sort;
import org.jmesa.web.HttpServletRequestWebContext;
import org.jmesa.web.WebContext;

import com.ibs.portal.framework.server.metadata.QueryPage;

public abstract class JmesaBaseAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3676444455868213076L;
	
	//默认页码
	public static final int DEFAULT_MAX_ROWS = 10;
	

	protected Limit limit;
	
	private LimitFactory limitFactory;
	
	protected String tableId = "tagSimplest";
	protected String stateAttr = "restore";
	
	
	
	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getStateAttr() {
		return stateAttr;
	}

	public void setStateAttr(String stateAttr) {
		this.stateAttr = stateAttr;
	}



	//每页条数
	protected int maxRows = 10;
	//总条数
	protected int totalRows;
	//分页参数对象
	protected QueryPage queryPage;
	//当前页
	protected int pageIndex = new Integer(1);;
	//排序字段名
	protected String sortName;
	//排序类型 asc或desc
	protected String sortType;

	//是否修改
	protected boolean isModify = false;
	
	//已选择的ID
	protected String[] selectIds = new String[0];

	public String[] getSelectIds() {
		return selectIds;
	}

	public void setSelectIds(String[] selectIds) {
		this.selectIds = selectIds;
	}

	public boolean getIsModify() {
		return isModify;
	}

	public void setIsModify(boolean isModify) {
		this.isModify = isModify;
	}

	public Limit getLimit() {
		return limit;
	}

	public void setLimit(Limit limit) {
		this.limit = limit;
	}
	
	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
		
		RowSelect rowSelect = limitFactory.createRowSelect(maxRows, totalRows);
		limit.setRowSelect(rowSelect);
	}
	
	@Override
	public void beforeInvoke(String method) {

		if("list".equals(method)) {
			//获取jmesa的分页参数
			populateJmesaData();
			
			//创建QueryPage
			queryPage = new QueryPage(maxRows,pageIndex-1,sortName,sortType);
		}
		else if("export".equals(method)) {
			//获取jmesa的分页参数
			populateJmesaData();
			
			//创建QueryPage
			queryPage = new QueryPage(maxRows,pageIndex-1,sortName,sortType);
		}
		else if("create".equals(method)) {
			this.setIsModify(false);
		}
		else if("modify".equals(method)) {
			this.setIsModify(true);
		}
	}
	
	protected void populateJmesaData() {
		WebContext webContext = new HttpServletRequestWebContext(request);
		limitFactory = new LimitFactoryImpl(tableId,webContext);
		limitFactory.setStateAttr(stateAttr);
	
		//如果一开始没有设置每页条数，设置为默认每页条数
		if(maxRows == 0) {
			maxRows = DEFAULT_MAX_ROWS;
		}
	
		limit = limitFactory.createLimit();
		
		//获取排序
		if(limit.getSortSet().getSorts().size() != 0) {
			Sort sort = limit.getSortSet().getSorts().iterator().next();
			if(sort.getOrder().equals(Order.ASC)) {
				sortType = "asc";
				sortName = sort.getProperty();	
			}
			else if(sort.getOrder().equals(Order.DESC)){
				sortType = "desc";
				sortName = sort.getProperty();
			}
			else if(sort.getOrder().equals(Order.NONE)) {
				sortType = null;
			}
		}
	
		String pageIndexStr = request.getParameter(tableId + "_p_");
		if(pageIndexStr != null) {
			pageIndex = new Integer(pageIndexStr).intValue();
		}
		if(logger.isInfoEnabled()){
			logger.info("pageIndex是" + pageIndex);
		}
		//logger.info("pageIndex是" + pageIndex);
	}
	
	@Override
	public void afterInvoke(String method) {
		if("list".equals(method)) {
			RowSelect rowSelect = limit.getRowSelect();
			if(logger.isInfoEnabled()){
				logger.info("每页条数maxRows:" + rowSelect.getMaxRows());
				logger.info("总条数totalRows:" + rowSelect.getTotalRows());
				logger.info("第几页page:" + rowSelect.getPage());
				logger.info("开始rowStart:" + rowSelect.getRowStart());
				logger.info("结束rowEnd:" + rowSelect.getRowEnd());
			}
			//logger.info("每页条数maxRows:" + rowSelect.getMaxRows());
			//logger.info("总条数totalRows:" + rowSelect.getTotalRows());
			//logger.info("第几页page:" + rowSelect.getPage());
			//logger.info("开始rowStart:" + rowSelect.getRowStart());
			//logger.info("结束rowEnd:" + rowSelect.getRowEnd());
		}

	}

	public int getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}
	


	/**
	 * 将String数组转为Long数组
	 */
	protected Long[] toLongArray(String[] stringArray) {
		Long[] integerArray = new Long[stringArray.length];
		for(int i = 0 ; i < stringArray.length; i ++) {
			integerArray[i] = new Long(Long.parseLong(stringArray[i]));
		}
		
		return integerArray;
	}
	
	/**
	 * 以下为需要重载的方法
	 */
	
	
	/**
	 * Action方法，需要继承，跳转到新增页面
	 * @return
	 */
	public abstract String create();

	/**
	 * Action方法，需要继承，获取修改的数据，并跳转到修改页面
	 * @return
	 */
	public abstract String modify();
	
	/**
	 * Action方法，需要继承，保存当前数据，并跳转到列表页面
	 * @return
	 */
	public abstract String saveOrUpdate();
	
	/**
	 * Action方法，列表
	 * @return
	 */
	public abstract String list();
	
	/**
	 * Action方法，删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract String delete();
	
	/**
	 * Action方法，导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract String export();
	
}

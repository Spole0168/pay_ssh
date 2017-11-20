package com.ibs.portal.framework.server.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.BeanUtils;

public abstract class ExtJSCrudBaseAction<T> extends ExtJSBaseAction<T> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8474887933678699654L;
	
	private boolean isModify = false;
	
	//导出参数对象
	protected ExportSetting exportSetting;
	
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
	 * Action方法，查询
	 * @return
	 */
	public abstract String search();
	
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
	
	/**
	 * 导出相关数据
	 */
	@SuppressWarnings("unchecked")
	public void populateExportData() {
		Map<String, String[]> parameters = ServletActionContext.getRequest()
				.getParameterMap();
		Set<String> keys = parameters.keySet();
		for (String key : keys) {
			String[] values = parameters.get(key);
			
			if(key.startsWith(ExportSetting.EXPORT_COL_NAMES)) {
				String seqStr = key.substring(ExportSetting.EXPORT_COL_NAMES.length());
				int seq = Integer.parseInt(seqStr);
				if (null != values && values.length > 0){
					exportSetting.addExportColName(seq, values[0]);
				}

			} else if (key.startsWith(ExportSetting.EXPORT_COL_MODELS)) {
				String seqStr = key.substring(ExportSetting.EXPORT_COL_MODELS.length());
				int seq = Integer.parseInt(seqStr);
				if (null != values && values.length > 0){
					exportSetting.addExportColModel(seq, values[0]);
				}

			} else if ("fileName".equals(key)) {
				if (null != values && values.length > 0){
					String fileName = values[0];
					try {
						fileName = new String(fileName.getBytes("GBK"),"ISO8859-1");
						exportSetting.setFileName(fileName);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					} 
				}

			}
		}
	}	
	
    public InputStream getInputStream() throws Exception {
        return new FileInputStream(exportSetting.getRealName());    
    }
	
	
	/**
	 * 将String数组转为integer数组
	 */
	protected Integer[] toIntgerArray(String[] stringArray) {
		Integer[] integerArray = new Integer[stringArray.length];
		for(int i = 0 ; i < stringArray.length; i ++) {
			integerArray[i] = new Integer(Integer.parseInt(stringArray[i]));
		}
		
		return integerArray;
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
	 * 前端AOP
	 */
	public void beforeInvoke(String method) {
		super.beforeInvoke(method);
		
		//方法名是search
		if("search".equals(method)) {
			this.populateJqGridData();
			
			//创建QueryPage
			queryPage = new QueryPage(this.getLimit(),this.getStart()/this.getLimit(),this.getSort(),this.getDir());
		}
		//如果方法名是delete
		else if("delete".equals(method)) {
			this.populateJqGridData();
		}
		//如果方法名是create
		else if("create".equals(method)) {
			this.setIsModify(false);
		}
		//如果方法名是modify
		else if("modify".equals(method)) {
			this.setIsModify(true);
		}
		//如果方法名是list
		else if("list".equals(method)) {		
			IUser su = getCurrentUser();
			if (null != su) {
				//String actionName = ActionContext.getContext().getName();
				String actionName = this.getClass().getName();
				pageCache = su.getPageCache(actionName);
				try {
					if(null!=pageCache) BeanUtils.populate(this, pageCache.getPageProperties());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}		
		//如果方法名是export
		else if("export".equals(method)) {
			this.populateJqGridData();
			
			//创建ExportSetting
			exportSetting = new ExportSetting(this.getSort(),this.getDir());
			
			this.populateExportData();
			

		}		
	}
	public boolean getIsModify() {
		return isModify;
	}

	public void setIsModify(boolean isModify) {
		this.isModify = isModify;
	}
	
	public ExportSetting getExportSetting() {
		return exportSetting;
	}

	
}

package com.ibs.common.module.frameworkimpl.grid.action;

import java.util.Calendar;
import java.util.Date;

import com.ibs.common.module.frameworkimpl.grid.biz.IJqGridBiz;
import com.ibs.common.module.frameworkimpl.security.domain.JqGridObj;
import com.ibs.portal.framework.server.action.BaseAction;
import com.ibs.portal.framework.server.domain.IUser;

public class JqGridManagerAction extends BaseAction{
	
	private static final long serialVersionUID = -2845942966881862264L;
	
	private String field;
	private String id;
	private String jqgrid;
	private JqGridObj jqGridObj;
	
	private IJqGridBiz jqGridBiz;
	

	public JqGridObj getJqGridObj() {
		return jqGridObj;
	}

	public void setJqGridObj(JqGridObj jqGridObj) {
		this.jqGridObj = jqGridObj;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJqgrid() {
		return jqgrid;
	}

	public void setJqgrid(String jqgrid) {
		this.jqgrid = jqgrid;
	}

	
	public IJqGridBiz getJqGridBiz() {
		return jqGridBiz;
	}

	public void setJqGridBiz(IJqGridBiz jqGridBiz) {
		this.jqGridBiz = jqGridBiz;
	}

	public String saveJqGrid() {
		
		try {
			IUser user = this.getCurrentUser();
			JqGridObj jObj = jqGridBiz
					.findByJqGridObj(user.getUserCode(), jqgrid);
			if (jObj == null) {
				jObj = new JqGridObj();
			}
			jObj.setField(field);
			jObj.setJqgrid(jqgrid);
			jObj.setUserid(user.getUserCode());
			jObj.setOperTime(new Date(Calendar.getInstance().getTimeInMillis()));
			
			jqGridBiz.saveJqGridObj(jObj);
			message = "OK";
		} catch (Exception e) {
			addActionError(e.getMessage());
			return ERROR;
		}

		return AJAX_RETURN_TYPE;
	}
	
	public String delJqGrid() {
		
		try {
			IUser user = this.getCurrentUser();
			JqGridObj jObj = jqGridBiz
					.findByJqGridObj(user.getUserCode(), jqgrid);
			if (jObj != null) {
				jqGridBiz.delJqGridObj(jObj.getId());
			}
			
			message = "OK";
		} catch (Exception e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		
		return AJAX_RETURN_TYPE;
	}

	public String findJqGrid() {	
		IUser user = this.getCurrentUser();
		JqGridObj jqGridObj = jqGridBiz.findByJqGridObj(user.getUserCode(), jqgrid);
		
		this.setJqGridObj(jqGridObj);
		return AJAX_RETURN_TYPE;
	}

	
}

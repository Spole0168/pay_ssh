package com.ibs.core.module.mdmintbasedata.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibs.common.module.frameworkimpl.security.constant.PermissionConstant;
import com.ibs.common.module.frameworkimpl.security.domain.User;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmintbasedata.biz.IForwarderBiz;
import com.ibs.core.module.mdmintbasedata.domain.Forwarder;
import com.ibs.core.module.permission.biz.IPermissionBiz;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.SelectRenderUtils;
import com.ibs.portal.framework.util.StringUtils;

@SuppressWarnings("serial")
public class ForwarderAction extends CrudBaseAction {
	private IForwarderBiz forwarderBiz;
	private String usedColumnRender;
	private List<OptionObjectPair> usedList;
	private Forwarder forwarder;
	private String id;
	private Map<String, Object> jsonResult = new HashMap<String, Object>();
	private String forwarderCode;
	private IPermissionBiz permissionBiz;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Forwarder getForwarder() {
		return forwarder;
	}

	public void setForwarder(Forwarder forwarder) {
		this.forwarder = forwarder;
	}

	public void setForwarderBiz(IForwarderBiz forwarderBiz) {
		this.forwarderBiz = forwarderBiz;
	}

	public IForwarderBiz getForwarderBiz() {
		return forwarderBiz;
	}

	public String validateCode() {
		logger.trace("enter" + this.getClass().getSimpleName()
				+ "-->method: validateCode() ");
		StringBuffer sBuffer = new StringBuffer();
		if (StringUtils.isNotEmpty(forwarder.getCode())) {			
			List<User> alist = permissionBiz.checkUniqueUserName(forwarder.getCode());
			boolean isDuplicate = true;
			if(alist!=null&&alist.size()>0)
			{
				isDuplicate = true;
			}else
			{
				isDuplicate = false;
			}
	 		sBuffer.append(isDuplicate);
			PrintWriter pw = null;
			try {
	 			response.setCharacterEncoding("UTF-8");
				pw = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.print(sBuffer.toString());
			pw.flush();
			pw.close();
		}
		return null;
	}

	public String validateName() {
		logger.trace("enter" + this.getClass().getSimpleName()
				+ "-->method: validateCode() ");
		StringBuffer sBuffer = new StringBuffer();
		
		if (StringUtils.isNotEmpty(forwarder.getName())) {
			int count= forwarderBiz.checkForwardername(forwarder.getName(),id );
			boolean isDuplicate = true;
			if (count>0) { 
				isDuplicate = false; 
			}
			sBuffer.append(isDuplicate);
			PrintWriter pw = null;
			try {
				response.setCharacterEncoding("UTF-8");
				pw = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.print(sBuffer.toString());
			pw.flush();
			pw.close();
		}
		return null;
	}

	private void prepareUsedList(boolean needDefault) {
		usedList = new ArrayList<OptionObjectPair>();

		if (needDefault)
			usedList.add(OptionObjectPair.getDefaultOptionObjectPair());

		usedList.add(new OptionObjectPair(PermissionConstant.USER_USED,
				PermissionConstant.USER_USED_VALUE));
		usedList.add(new OptionObjectPair(PermissionConstant.USER_INUSED,
				PermissionConstant.USER_INUSED_VALUE));
		setUsedColumnRender(SelectRenderUtils.toRenderString(usedList));
	}

	@Override
	public String create() {
		logger.trace("enter" + this.getClass().getSimpleName()
				+ "-->method: create() ");
		setForwarder(new Forwarder());
		return Constants.CREATE_ACTION_URL;
	}

	@Override
	public String modify() {
		logger.trace("enter" + this.getClass().getSimpleName()
				+ "-->method: modify() ");
		forwarder = forwarderBiz.load(getId());
		prepareUsedList(false);
		return Constants.MODIFY_ACTION_URL;
	}

	@Override
	public String saveOrUpdate() {
		logger.trace("enter" + this.getClass().getSimpleName()
				+ "-->method: modify() ");
		IUser user = getCurrentUser();
		try {
			// 如果是新增
			if (this.getIsModify() == false) {
				forwarder.setStatus(Constants.STATUS_VALID);
				forwarder.setUsed(Constants.INUSED);
				forwarder.setCreateUserCode(user.getUserCode());
				forwarder.setCreateTime(new Date());
				forwarderBiz.save(forwarder);
			} else {
				// 修改
				Forwarder forwarderRef = forwarderBiz.load(forwarder.getId());
				forwarderRef.setName(forwarder.getName());
				forwarderRef.setDescription(forwarder.getDescription());
				forwarderRef.setEnglishName(forwarder.getEnglishName());
				forwarderRef.setModifyTime(new Date());
				forwarderRef.setModifyUserCode(user.getUserCode());
				forwarderRef.setUsed(forwarder.getUsed());
				forwarderRef.setContactPerson(forwarder.getContactPerson());
				forwarderRef.setContactType(forwarder.getContactType());
				forwarderBiz.saveOrUpdate(forwarderRef);
			}
		} catch (Exception e) {
			if (logger.isErrorEnabled())
				logger.error("添加或修改  货代信息(forwarder) 时出错");
			return Constants.ERROR_SAVE_ACTION_URL;
		}
		return Constants.AJAX_SAVE_ACTION_URL;
	}

	@Override
	public String list() {
		logger.trace("enter" + this.getClass().getSimpleName()
				+ "-->method: list() ");
		prepareUsedList(false);
		return SUCCESS;
	}

	@Override
	public String search() {
		logger.trace("enter" + this.getClass().getSimpleName()
				+ "-->method: search() ");
		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		String searchCode = getSearchFields().get("searchCode");

		if (StringUtils.isNotEmpty(searchCode)) {
			queryPage.addLikeSearch("code", searchCode.trim().toUpperCase());
		}

		String searchName = getSearchFields().get("searchName");
		if (StringUtils.isNotEmpty(searchName)) {
			queryPage.addLikeSearch("name", searchName.trim());
		}

		String searchDescription = getSearchFields().get("searchDescription");
		if (StringUtils.isNotEmpty(searchDescription)) {
			queryPage.addLikeSearch("description", searchDescription.trim());
		}
		/**
		 * 只查询status 为有效的数据
		 */
		queryPage.addEqualSearch("status", Constants.STATUS_VALID);

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<Forwarder> result = (Page<Forwarder>) forwarderBiz
				.findForwarderByPage(queryPage);
		setResult(result);

		/**
		 * 第三步，返回
		 */
		return AJAX_RETURN_TYPE;
	}

	@Override
	public String delete() {
		logger.trace("enter" + this.getClass().getSimpleName()
				+ "-->method: delete() ");
		String[] idStringArray = getSelectIds();
		IUser user = getCurrentUser();
		try {
			if (idStringArray.length > 0) {
				for (String chosenId : idStringArray) {
					Forwarder chosenForwarder = forwarderBiz.load(chosenId);
					chosenForwarder.setModifyUserCode(user.getUserCode());
					chosenForwarder.setModifyTime(new Date());
					forwarderBiz.delete(chosenForwarder);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "deleteerror";

		}
		message = "OK";
		// return AJAX_RETURN_TYPE;
		return "delete";
	}

	@Override
	public String export() {

		return null;
	}

	/*public String autoCompleteCode() {
		logger.trace("enter " + this.getClass().getSimpleName()
				+ " method autoCompleteCode");
		IUser user;
		try {
			user = getCurrentUser();
		} catch (Exception e) {
			logger.error("无法找到用户信息");
			return ERROR;
		}
		List<Forwarder> forwarders = forwarderBiz.list(forwarderCode, null,
				Constants.STATUS_VALID);
		jsonResult.put("codes", forwarders);
		return AJAX_RETURN_TYPE;
	}*/

	public void setUsedColumnRender(String usedColumnRender) {
		this.usedColumnRender = usedColumnRender;
	}

	public String getUsedColumnRender() {
		return usedColumnRender;
	}

	public void setUsedList(List<OptionObjectPair> usedList) {
		this.usedList = usedList;
	}

	public List<OptionObjectPair> getUsedList() {
		return usedList;
	}

	public Map<String, Object> getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(Map<String, Object> jsonResult) {
		this.jsonResult = jsonResult;
	}

	public String getForwarderCode() {
		return forwarderCode;
	}

	public void setForwarderCode(String forwarderCode) {
		this.forwarderCode = forwarderCode;
	}

	public IPermissionBiz getPermissionBiz() {
		return permissionBiz;
	}

	public void setPermissionBiz(IPermissionBiz permissionBiz) {
		this.permissionBiz = permissionBiz;
	}

}

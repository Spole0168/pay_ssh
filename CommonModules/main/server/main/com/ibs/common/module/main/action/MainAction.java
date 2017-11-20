package com.ibs.common.module.main.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.ibs.common.module.frameworkimpl.security.constant.PermissionConstant;
import com.ibs.common.module.frameworkimpl.security.dto.UserComplex;
import com.ibs.common.module.main.biz.IInformBiz;
import com.ibs.common.module.main.biz.IMainBiz;
import com.ibs.common.module.main.domain.MainInform;
import com.ibs.common.module.main.domain.MessageInform;
import com.ibs.portal.framework.server.action.BaseAction;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.util.StringUtils;

public class MainAction extends BaseAction {

	private static final long serialVersionUID = -2500781333638449462L;

	private IInformBiz informBiz;
	private IMainBiz mainBiz;
//	private ILoginBiz loginBiz;
	
	private List<OptionObjectPair> roles;
	private String currentRoleId;
	private String nextRoleId;
	private String userName;
	private String displayName;
	private List<MainInform> mainInforms;
	private List<MessageInform> messageInforms;
	private List<String> messages;
//	private String password;
	private String oripassword;
	private String newpassword;
	private String errMsg;
	private Integer passwordActiveDay;
	private String userId;
	private String pwdStatus;
	
	public String getPwdStatus() {
		return pwdStatus;
	}

	public void setPwdStatus(String pwdStatus) {
		this.pwdStatus = pwdStatus;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getPasswordActiveDay() {
		return passwordActiveDay;
	}

	public void setPasswordActiveDay(Integer passwordActiveDay) {
		this.passwordActiveDay = passwordActiveDay;
	}

	public String show(){
		logger.trace("entering action...");
		
		UserComplex suser = (UserComplex) getCurrentUser();
		//menus = mainBiz.loadUserMenu(suser);
		//suser.setMenus(menus);
		//this.session.put("menus", menus);
		displayName = suser.getDisplayName();
		roles = suser.getRolePairs();
//		roles = mainBiz.getAllRolesByUser(suser);
		//TODO 这里原来是positionId，现在改为roleId，该怎么处理
		currentRoleId = suser.getCurrentRoleId();
		
		// 首页提醒
		mainInforms = new ArrayList<MainInform>();
		List<MainInform> informs = informBiz.findValidMainInform();
		for (MainInform inform : informs) {
			if (suser.getUrlMaps().values().contains(inform.getInformUrl())) {
				mainInforms.add(inform);
			}
		}
		
		// 消息提醒
		messageInforms = informBiz.findValidMessageInform();

		// 密码过期提醒
//		Integer res = mainBiz.checkUserPwd(null, null);
//		Integer res = suser.getPasswordActiveDay();
//		if(null != res && res.intValue()>0 && res.intValue()<PermissionConstant.PWD_ACTIVE_WARN_DAY){
//			passwordActiveDay = res;
//		}
		return SUCCESS;
	}

	public String getInformMessages() {
		logger.trace("entering action...");
		
		messages = new ArrayList<String>();
		// 加入密码过期提醒
		Integer res = mainBiz.checkUserPwd(null, null);
		
		if(null != res && res.intValue()>0 && res.intValue()<PermissionConstant.PWD_ACTIVE_WARN_DAY){
			messages.add("<ul><li>您的密码即将在"+res+"天后过期，请及时修改密码</li></ul>");
		}
		return AJAX_RETURN_TYPE;
	}
	
	public String switchRole() throws Exception {
		UserComplex suser = (UserComplex) getCurrentUser();
		
//		menus = mainBiz.loadCurrentRoleMenu(suser, nextRoleId);
//		menus = suser.getMenus();
//		this.session.put("menus", menus);
		displayName = suser.getDisplayName();
//		roles = mainBiz.getAllRolesByUser(suser);
		roles = suser.getRolePairs();
		currentRoleId = nextRoleId;
		suser.setCurrentRoleId(nextRoleId);
		return SUCCESS;
	}
	
	public String toModifyPassword() {
		return SUCCESS;
	}
	
	public String initPassword() {
//		Object obj = getCurrentUser();
//		if(null == obj){
//			addActionError("无法获取登录用户信息！");
//			return ERROR;
//		}
//		IUser user = (IUser)obj;
//		displayName = user.getDisplayName();
		
		return SUCCESS;
	}
	
	public String modifyPassword(){
//		String loginIp = ServletActionContext.getRequest().getRemoteAddr();
		//修改该密码
		if(this.hasActionErrors()){
			return SUCCESS;
		}
		try{
			mainBiz.changePassword(oripassword, newpassword);
			this.addActionMessage("密码修改成功，请重新登录");
			pwdStatus="success";
		}catch(Exception e){
			logger.error(e.getMessage());
			this.addActionError("密码修改失败，请重新修改密码或联系管理员");
			pwdStatus="error";
		}
		
		return toModifyPassword();
	}
	
	public String checkUserPwd(){
		StringBuffer sBuffer = new StringBuffer();
		
		if(StringUtils.isNotEmpty(oripassword)){
			Integer res = mainBiz.checkUserPwd(userId, oripassword);
			if (null != res && res.intValue() >= 0) {
				sBuffer.append(true);
			} else {
				sBuffer.append(false);
			}
			
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
	public void validateModifyPassword(){
		if(oripassword.equalsIgnoreCase(newpassword)){
			this.addActionError(errMsg);
		}
	}
	
	public String forWard(){
		return SUCCESS;
	}
	public IMainBiz getMainBiz() {
		return mainBiz;
	}

	public void setMainBiz(IMainBiz mainBiz) {
		this.mainBiz = mainBiz;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<OptionObjectPair> getRoles() {
		return roles;
	}

	public void setRoles(List<OptionObjectPair> roles) {
		this.roles = roles;
	}

	public String getCurrentRoleId() {
		return currentRoleId;
	}

	public void setCurrentRoleId(String currentRoleId) {
		this.currentRoleId = currentRoleId;
	}

	public String getNextRoleId() {
		return nextRoleId;
	}

	public void setNextRoleId(String nextRoleId) {
		this.nextRoleId = nextRoleId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public IInformBiz getInformBiz() {
		return informBiz;
	}

	public void setInformBiz(IInformBiz informBiz) {
		this.informBiz = informBiz;
	}

	public List<MainInform> getMainInforms() {
		return mainInforms;
	}

	public void setMainInforms(List<MainInform> mainInforms) {
		this.mainInforms = mainInforms;
	}

//	public ILoginBiz getLoginBiz() {
//		return loginBiz;
//	}
//
//	public void setLoginBiz(ILoginBiz loginBiz) {
//		this.loginBiz = loginBiz;
//	}

//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

	public String getNewpassword() {
		return newpassword;
	}

	public String getOripassword() {
		return oripassword;
	}

	public void setOripassword(String oripassword) {
		this.oripassword = oripassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public List<MessageInform> getMessageInforms() {
		return messageInforms;
	}

	public void setMessageInforms(List<MessageInform> messageInforms) {
		this.messageInforms = messageInforms;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}

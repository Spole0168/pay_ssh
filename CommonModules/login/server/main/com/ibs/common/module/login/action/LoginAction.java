package com.ibs.common.module.login.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ibs.common.module.frameworkimpl.security.exception.AuthorizeException;
import com.ibs.common.module.frameworkimpl.security.exception.NoRoleAssignedException;
import com.ibs.common.module.login.biz.ILoginBiz;
import com.ibs.portal.framework.server.action.BaseAction;
import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.framework.util.StringUtils;
import com.ibs.portal.integration.sso.ConfigFactory;
import com.ibs.portal.integration.sso.Token;
import com.ibs.portal.integration.sso.generate.TokenUrlRewriter;

public class LoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6899274977262374672L;
	
	
	private String userName;
	private String password;
	private String errMsg;
	private String varification;
	private ILoginBiz loginBiz;
	private Integer passwordActiveDay;
	private String userId;
	private String oripassword;
	private String newpassword;
	private String pwdStatus;
	
	public String getPwdStatus() {
		return pwdStatus;
	}

	public void setPwdStatus(String pwdStatus) {
		this.pwdStatus = pwdStatus;
	}

	public String getOripassword() {
		return oripassword;
	}

	public void setOripassword(String oripassword) {
		this.oripassword = oripassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
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

	public String index() throws Exception {
		return SUCCESS;
	}
	
	public String login() {
		String loginIp = ServletActionContext.getRequest().getRemoteAddr();
		HttpSession session = ServletActionContext.getRequest().getSession(Boolean.FALSE);

		try {
			//得到之前生成的验证码
			String verify = (String)session.getAttribute("RAND");
			
			//移除验证码
			session.removeAttribute("RAND");
			
			//如果用户提交的验证码与之前生成的验证码不相等，抛出验证码错误异常
			if(!StringUtils.equals(verify, varification)){
				throw new AuthorizeException(AuthorizeException.AUTHORIZE_ERROR_VARIFICATION);
			}
			String userId= loginBiz.login(loginIp, userName, password);
			
			Integer roleNum = loginBiz.checkUserRoles(userName);
			if (null == roleNum || roleNum.intValue() <= 0) {
				throw new NoRoleAssignedException();
			}
			
			/**
			 * 以下验证用户密码是否需要修改
			 */
			if(logger.isDebugEnabled()){
				logger.debug("check user ["+userId+"] password ...");
			}
			// 检查是否正确
			Integer res = loginBiz.checkUserPwd(userId, password);
//			Integer res = user.getPasswordActiveDay();
			if(null != res && res.intValue()==0){ // 如果密码已经过期
				this.userId = userId;
				return initPassword();
			}
			
			UserContext.getUserContext().setUserId(userId);
			//得到Token
			Token token = TokenUrlRewriter.generate(session);
			
			//创建Cookie
			Cookie cookie = new Cookie(ConfigFactory.TOKEN_HEADER_KEY, token.toString()); 
			cookie.setPath("/");
			//cookie.setMaxAge(30*60);
			ServletActionContext.getResponse().addCookie(cookie);
			
			//SessionUser tuser = (SessionUser) getCurrentUser();
			
			// 保存登录日志
			
//			Date pwdModifiedTm = user.getPwdModifiedTm();
//			if (isExpiry(pwdModifiedTm))
//				return "password";
			
//			Object obj = UserContext.getUserContext().getUserCached(userId);
//			if(null == obj){
//				throw new InvalidUserException();
//			}
			
//			IUser user = (IUser)obj;
//			if(null == user.getRoleCodes() || user.getRoleCodes().size()<=0){
//				throw new NoRoleAssignedException();
//			}
			
			
			
			return SUCCESS;
		} catch (AuthorizeException e) {
			logger.warn(e.getMessage());
			if (AuthorizeException.AUTHORIZE_NOT_EXIST_USER.equals(e.getMessage())) {
				errMsg = "用户不存在";	// 用户不存在
			} else if (AuthorizeException.AUTHORIZE_INVALID_USER.equals(e.getMessage())) {
				errMsg = "用户或所在组织被禁用";	// 用户被禁用
			} else if (AuthorizeException.AUTHORIZE_ILLEGAL_USERNAME.equals(e.getMessage())) {
				errMsg = "用户名不合法";	// 用户名不合法
			} else if (AuthorizeException.AUTHORIZE_ILLEGAL_PASSWORD.equals(e.getMessage())) {
				errMsg = "密码不合法";	// 密码不合法
			} else if (AuthorizeException.AUTHORIZE_ERROR_PASSWORD.equals(e.getMessage())) {
				errMsg = "密码错误";		// 密码错误
			} else if (AuthorizeException.AUTHORIZE_ERROR_VARIFICATION.equals(e.getMessage())) {
				errMsg = "验证码错误";	// 验证码错误
			} else if (AuthorizeException.AUTHORIZE_NO_ROLE_ASSIGNED.equals(e.getMessage())) {
				errMsg = "用户未配置角色信息，请联系管理员";	// 用户未配置角色
			} else {
				errMsg = "系统错误";		// 系统错误
			}
			
			this.addActionError(errMsg);
			
			return ERROR;
		} 
		
	}
	
	public String logout(){
		loginBiz.logout();
		//清除Cookie值
		Cookie cookie = new Cookie(ConfigFactory.TOKEN_HEADER_KEY, ""); 
		cookie.setPath("/");
		cookie.setMaxAge(0);
		
		ServletActionContext.getResponse().addCookie(cookie);
		
		ServletActionContext.getRequest().getSession().invalidate();
		
		return SUCCESS;
	}
	
	public String initPassword() {
		return "initPassword";
	}
	
	public String modifyPassword(){
		//修改该密码
		if(this.hasActionErrors()){
			return SUCCESS;
		}
		try{
//			loginBiz.changePassword(oripassword, newpassword);
			loginBiz.changePassword(userId, oripassword, newpassword);
			this.addActionMessage("密码修改成功，请重新登录");
			pwdStatus="success";
		}catch(Exception e){
			logger.error(e.getMessage());
			this.addActionError("密码修改失败，请重新修改密码或联系管理员");
			pwdStatus="error";
			//return "initPassword";
		}
		
		return initPassword();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public ILoginBiz getLoginBiz() {
		return loginBiz;
	}

	public void setLoginBiz(ILoginBiz loginBiz) {
		this.loginBiz = loginBiz;
	}

	public String getVarification() {
		return varification;
	}

	public void setVarification(String varification) {
		this.varification = varification;
	}

}

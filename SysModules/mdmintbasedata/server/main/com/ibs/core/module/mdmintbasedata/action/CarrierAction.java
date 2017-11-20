package com.ibs.core.module.mdmintbasedata.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibs.common.module.frameworkimpl.security.constant.PermissionConstant;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmintbasedata.biz.ICarrierBiz;
import com.ibs.core.module.mdmintbasedata.dao.ICarrierDao;
import com.ibs.core.module.mdmintbasedata.domain.Carrier;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.SelectRenderUtils;
import com.ibs.portal.framework.util.StringUtils;

@SuppressWarnings("serial")
public class CarrierAction extends CrudBaseAction {
	private ICarrierBiz carrierBiz;
	private ICarrierDao carrierDao;
	private Carrier carrier;
	private String id;
	private String usedColumnRender;
	private List<OptionObjectPair> usedList;
	private String used;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCarrierBiz(ICarrierBiz carrierBiz) {
		this.carrierBiz = carrierBiz;
	}

	public ICarrierBiz getCarrierBiz() {
		return carrierBiz;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public String validateCode(){
		logger.trace("enter"+this.getClass().getSimpleName()+"-->method: validateCode() ");
		StringBuffer sBuffer = new StringBuffer();
		if (StringUtils.isNotEmpty(carrier.getCode())) {
			List<Carrier> list = carrierBiz.list(carrier.getCode(), null, null);
			boolean isDuplicate = true;
			if(list != null && list.size() >0){		
				for(Carrier obj : list) {
					if(!obj.getId().equals(id)){
						isDuplicate = false;
						break;
					}
				}				
			}
			sBuffer.append(isDuplicate);
			PrintWriter pw = null;
			try {
				//System.out.println(response.getCharacterEncoding());
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
	
	public String validateName(){
		logger.trace("enter"+this.getClass().getSimpleName()+"-->method: validateCode() ");
		StringBuffer sBuffer = new StringBuffer();
		if (StringUtils.isNotEmpty(carrier.getName())) {
			List<Carrier> list = carrierBiz.list( null,carrier.getName().toUpperCase(), Constants.STATUS_VALID);
			boolean isDuplicate = true;
			if(list != null && list.size() >0){		
				for(Carrier obj : list) {
					if(!obj.getId().equals(id)){
						isDuplicate = false;
						break;
					}
				}				
			}
			sBuffer.append(isDuplicate);
			PrintWriter pw = null;
			try {
				//System.out.println(response.getCharacterEncoding());
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
		usedColumnRender = SelectRenderUtils.toRenderString(usedList);
	}
	
	@Override
	public String create() {
		logger.trace("enter"+this.getClass().getSimpleName()+"-->method: create() ");
		setCarrier(new Carrier());
		return Constants.CREATE_ACTION_URL;
	}

	@Override
	public String modify() {
		logger.trace("enter"+this.getClass().getSimpleName()+"-->method: modify() ");
		carrier = carrierBiz.load(getId());
		prepareUsedList(false);
		return Constants.MODIFY_ACTION_URL;
	}

	@Override
	public String saveOrUpdate() {
		logger.trace("enter"+this.getClass().getSimpleName()+"-->method: saveOrUpdate() ");
		IUser user =  getCurrentUser();
		
		try {
			//如果是新增
			if(this.getIsModify() == false) {
				carrier.setStatus(Constants.STATUS_VALID);
				carrier.setUsed(Constants.INUSED);
				carrier.setCreateUserCode(user.getUserCode());
				carrier.setCreateTime(new Date());
				carrierBiz.save(carrier);
			}
			//如果是修改
			else {
				Carrier carrierRef = carrierBiz.load(carrier.getId());
				carrierRef.setName(carrier.getName());
				carrierRef.setDescription(carrier.getDescription());
				carrierRef.setEnglishName(carrier.getEnglishName());
				carrierRef.setModifyUserCode(user.getUserCode());
				carrierRef.setModifyTime(new Date());
				
				carrierBiz.saveOrUpdate(carrierRef);
			}
		} catch (Exception e) {
			if(logger.isErrorEnabled()) logger.error("添加或修改  承运商(carrier) 时出错");
			return Constants.ERROR_SAVE_ACTION_URL;
		}
		return Constants.AJAX_SAVE_ACTION_URL;
	}

	@Override
	public String list() {
		logger.trace("enter"+this.getClass().getSimpleName()+"-->method: list() ");
		prepareUsedList(true);
		return SUCCESS;
	}

	@Override
	public String search() {
		logger.trace("enter"+this.getClass().getSimpleName()+"-->method: search() ");
		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		String searchCode = getSearchFields().get("searchCode");

		if(StringUtils.isNotEmpty(searchCode)) {
			queryPage.addLikeSearch("code", searchCode.trim().toUpperCase());
		}

		String searchName = getSearchFields().get("searchName");
		if(StringUtils.isNotEmpty(searchName)) {
			queryPage.addLikeSearch("name", searchName.trim());
		}
		
		String searchUsed = getSearchFields().get("searchUsed");
		if(StringUtils.isNotEmpty(searchUsed)) {
			queryPage.addLikeSearch("used", searchUsed.trim());
		}
		
		String searchDescription = getSearchFields().get("searchDescription");
		if(StringUtils.isNotEmpty(searchDescription)) {
			queryPage.addLikeSearch("description", searchDescription.trim());
		}
		/**
		 * 只查询status 为有效的数据
		 */
		//queryPage.addEqualSearch("status", Constants.STATUS_VALID);

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<Carrier> result = (Page<Carrier>) carrierBiz.findCarrierByPage(queryPage);
		setResult(result);
		
		/**
		 * 第三步，返回
		 */
		return AJAX_RETURN_TYPE;
	}

	@Override
	public String delete() {
		
		logger.trace("enter"+this.getClass().getSimpleName()+"-->method: delete() ");
		String[] idStringArray = getSelectIds();
		IUser user =  getCurrentUser();
		try {
			if(idStringArray.length>0){
				for(String chosenId : idStringArray){
					Carrier chosenCarrier =  carrierBiz.load(chosenId);
					chosenCarrier.setModifyUserCode(user.getUserCode());
					chosenCarrier.setModifyTime(new Date());
					carrierBiz.delete(chosenCarrier);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "deleteerror";
			
		}
		message = "OK";
//		return AJAX_RETURN_TYPE;
		return "delete";
	}
	
	
	public String changeStatus(){
		logger.trace("Enter Action...");

		String[] idStringArray = getSelectIds()[0].split(",");
		String status=getUsed();
		
		try {
			if(StringUtils.isNotEmpty(status)&&"enable".equals(status)){
				carrierBiz.changeStatus(idStringArray, Constants.YES_OR_NO_Y);
			}else{
				carrierBiz.changeStatus(idStringArray, Constants.YES_OR_NO_N);
			}
			message = "OK";
		} catch (Exception e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		return "changeStatus";
	}

	@Override
	public String export() {
		
		return null;
	}

	public void setCarrierDao(ICarrierDao carrierDao) {
		this.carrierDao = carrierDao;
	}

	public ICarrierDao getCarrierDao() {
		return carrierDao;
	}

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
	
	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}
}

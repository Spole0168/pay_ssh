/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.blacklist.action;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.blacklist.biz.ISysNamelistBiz;
import com.ibs.core.module.blacklist.domain.SysNamelist;
import com.ibs.core.module.blacklist.dto.BlackListToNoticDto;
import com.ibs.core.module.mdmbasedata.biz.IDataDictBiz;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.CollectionUtils;
import com.ibs.portal.framework.util.DateUtils;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_NAMELIST
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class SysNamelistManagerAction extends CrudBaseAction {

	private ISysNamelistBiz sysNamelistBiz;
	
	private String id;
	private String status;//审核状态
	private String message;
	private String nlType;//黑名单类型
	private String nlId;//黑名单值
	
	private List<OptionObjectPair> nlTypeList;//黑名单类型
	private List<OptionObjectPair> statusList;//黑名单状态list
	
	private SysNamelist sysNamelist;
	private BlackListToNoticDto   blackListToNoticDto;//
	
	private IDataDictBiz  dataDictBiz;
	private IDataDictService dataDictService;

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::SysNamelistManagerAction.list()...");
		//黑名单类型
		nlTypeList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__BLACKLIST_TYPE);
		//黑名单状态
		statusList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__BLCAKLIST_STATUS);
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::SysNamelistManagerAction.create()...");
		nlTypeList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__BLACKLIST_TYPE);
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::SysNamelistManagerAction.modify()...");
		sysNamelist=sysNamelistBiz.getSysNamelistById(id);
		nlTypeList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__BLACKLIST_TYPE);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::SysNamelistManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<SysNamelist> result = (Page<SysNamelist>) sysNamelistBiz
				.findSysNamelistByValidAndPage(queryPage);
		
		Collection<SysNamelist> rows2 = result.getRows();
		
		
		
		List<DataDict> col1=dataDictService.list(IDataDictService.DATA_DICT_TYPE__BLCAKLIST_STATUS);
		List<DataDict> col2=dataDictService.list(IDataDictService.DATA_DICT_TYPE__BLACKLIST_TYPE);
		CollectionUtils.transformCollection(rows2, "status", "status", col1, "code", "name");
		CollectionUtils.transformCollection(rows2, "nlType", "nlType", col2, "code","name");
		
		
		setResult(result);

		/**
		 * 第三步，返回
		 */
		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 保存
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdate() {
		logger.info("entering action::SysNamelistManagerAction.saveOrUpdate()...");
		
	
		String  username=getUserName();//登录名
		// 如果是新增
		if (this.getIsModify() == false) {
			
			
			sysNamelistBiz.saveSysNamelist(sysNamelist,username);
		}
		// 如果是修改
		else {
			sysNamelistBiz.updateSysNamelist(sysNamelist,username);
		}

		return SUCCESS;
	}
	
	/**
	 * Action方法，批量删除数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() {
		logger.info("entering action::SysNamelistManagerAction.delete()...");

		/**
		 * 第一步，获取要删除的ID字符串数组,并转换为Integer数组
		 */
		String[] idStringArray = id.split(",");
		// Long[] idArray = this.toLongArray(idStringArray);

		/**
		 * 第二步，按ID数组删除对象
		 */
		try {
			sysNamelistBiz.deleteBlackList(idStringArray);
		//	message = "OK";
		} catch (Exception e) {
			addActionError(e.getMessage());
			return ERROR;
		}

		/**
		 * 第三步，返回
		 */
		return SUCCESS;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::SysNamelistManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		sysNamelistBiz.exportSysNamelist(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	/**
	 * 审批页面
	 */
	public String   review(){
		
		logger.info("entering action::SysNamelistManagerAction.review()...");
	 
		String  username=getUserName();//登录名
		
		nlTypeList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__BLACKLIST_TYPE);
		statusList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__BLCAKLIST_STATUS);
		
		
		sysNamelist=sysNamelistBiz.getReview(username,id);
		 
		return  SUCCESS;
	}
	
	/**
	 * 审批
	 * @return
	 */
	
	public String  doReview(){
		logger.info("entering action::SysNamelistManagerAction.doReview()...");
		
		
		
		sysNamelistBiz.doReview(sysNamelist);
		
		
		return SUCCESS;
		
	} 
	
	/**
	 * ajax  提交审批信息返回审批结果
	 * @return
	 */
	public String doReviewByAjax(){
		
		logger.info("entering action::SysNamelistManagerAction.doReviewByAjax()...");
		
		
		
		boolean falg=sysNamelistBiz.doReviewByAjax(sysNamelist);
		String status=sysNamelist.getStatus();
		if(falg){
			if(Constants.BLACKLIST_TYPE_CELLPHONE.equals(status)){
				
				message="已审核";
			}else{
				message="已拒绝";
			}
		}else{
			
			message="审批失败";
		}
		
		
		 return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 判断是否相同
	 * @return
	 */
	public String  isSame(){
		
		List<SysNamelist> sysNamelist=sysNamelistBiz.isSame(nlType, nlId);
		
		
		int num=sysNamelist.size();
		
		System.out.println(this.getIsModify()+"    --"+id);
		
		
		
		//判断如果是添加的情况
//		if(this.getIsModify() == false){
			
			if(num>=1){
				
				message="添加黑名单值重复!";
			}
//			
//		}else{
//			
//			
//			List<SysNamelist> sysNamelist2=sysNamelistBiz.isSame(nlType, nlId,id);
//			int num2=sysNamelist2.size();
//			//如果是更新的情况
//			if(num2>=1){
//					
//				message="黑名单值重复!";
//				
//			}
//		}
		
		return AJAX_RETURN_TYPE;
	}
	public String isSameModify(){
		
		List<SysNamelist> sysNamelist2=sysNamelistBiz.isSame(nlType, nlId,id);
		int num2=sysNamelist2.size();
		//如果是更新的情况
		if(num2>=1){
				
			message="黑名单值重复!";
			
		}
		
		
		return AJAX_RETURN_TYPE;
	}
	
	
	
	
	
	
	
	public String toTest(){
		
		return SUCCESS;
	}
	
	public  String  test(){
		
		//test
		sysNamelistBiz.valid(blackListToNoticDto);
		
		message="ok";
		
		System.out.println(message);
		
		return  AJAX_RETURN_TYPE;
	}
	
	
	public void setQueryCondition() {
		logger.info("entering action::SysNamelistManagerAction.setQueryCondition()...");


		// ID
		String id = getSearchFields().get("id");
		if (StringUtils.isNotEmpty(id)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("id", "%" + id + "%");
				queryPage.addLikeSearch("id", id);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("id", "%" + id + "%");
				exportSetting.addLikeSearch("id", id);
			}
		}


		// NL_TYPE
		String nlType = getSearchFields().get("nlType").toString();
		if (StringUtils.isNotEmpty(nlType)) {
			 
			if (null != queryPage) {
				queryPage.addQueryCondition("nlType",  nlType );
				queryPage.addEqualSearch("nlType", nlType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("nlType", nlType);
				exportSetting.addEqualSearch("nlType", nlType);
			}
			 
		}


		// NL_ID
		String nlId = getSearchFields().get("nlId");
		if (StringUtils.isNotEmpty(nlId)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("nlId", "%" + nlId + "%");
				queryPage.addLikeSearch("nlId", nlId);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("nlId", "%" + nlId + "%");
				exportSetting.addLikeSearch("nlId", nlId);
			}
		}


		// NL_DESC
		String nlDesc = getSearchFields().get("nlDesc");
		if (StringUtils.isNotEmpty(nlDesc)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("nlDesc", "%" + nlDesc + "%");
				queryPage.addLikeSearch("nlDesc", nlDesc);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("nlDesc", "%" + nlDesc + "%");
				exportSetting.addLikeSearch("nlDesc", nlDesc);
			}
		}


		// ACCESS_TYPE
		String accessType = getSearchFields().get("accessType");
		if (StringUtils.isNotEmpty(accessType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("accessType", "%" + accessType + "%");
				queryPage.addLikeSearch("accessType", accessType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("accessType", "%" + accessType + "%");
				exportSetting.addLikeSearch("accessType", accessType);
			}
		}


		// STATUS
		String status = getSearchFields().get("status").toString();
		if (StringUtils.isNotEmpty(status)) {
		 
				if (null != queryPage) {
					queryPage.addQueryCondition("status", status);
					queryPage.addEqualSearch("status", status);
				}
				if (null != exportSetting) {
					exportSetting.addQueryCondition("status",  status );
					exportSetting.addEqualSearch("status", status);
				}
		
		 
			
		}

		// REVIEWER
		String reviewer = getSearchFields().get("reviewer");
		if (StringUtils.isNotEmpty(reviewer)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reviewer", "%" + reviewer + "%");
				queryPage.addLikeSearch("reviewer", reviewer);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reviewer", "%" + reviewer + "%");
				exportSetting.addLikeSearch("reviewer", reviewer);
			}
		}


		// REVIEW_TIME
		String reviewTime = getSearchFields().get("reviewTime");
		if (StringUtils.isNotEmpty(reviewTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reviewTime",
						DateUtils.convert(reviewTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reviewTime",
						DateUtils.convert(reviewTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// CREATE_TIME
		String createTime = getSearchFields().get("createTime");
		if (StringUtils.isNotEmpty(createTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("createTime",
						DateUtils.convert(createTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("createTime",
						DateUtils.convert(createTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// UPDATE_TIME
		String updateTime = getSearchFields().get("updateTime");
		if (StringUtils.isNotEmpty(updateTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("updateTime",
						DateUtils.convert(updateTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("updateTime",
						DateUtils.convert(updateTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// CREATOR
		String creator = getSearchFields().get("creator");
		if (StringUtils.isNotEmpty(creator)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("creator", "%" + creator + "%");
				queryPage.addLikeSearch("creator", creator);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("creator", "%" + creator + "%");
				exportSetting.addLikeSearch("creator", creator);
			}
		}


		// UPDATOR
		String updator = getSearchFields().get("updator");
		if (StringUtils.isNotEmpty(updator)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("updator", "%" + updator + "%");
				queryPage.addLikeSearch("updator", updator);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("updator", "%" + updator + "%");
				exportSetting.addLikeSearch("updator", updator);
			}
		}

	}

	
	
	
	
	public String getNlType() {
		logger.info("entering action::SysNamelistManagerAction.getNlType()...");
		return nlType;
	}

	public void setNlType(String nlType) {
		logger.info("entering action::SysNamelistManagerAction.setNlType()...");
		this.nlType = nlType;
	}

	public String getNlId() {
		logger.info("entering action::SysNamelistManagerAction.getNlId()...");
		return nlId;
	}

	public void setNlId(String nlId) {
		logger.info("entering action::SysNamelistManagerAction.setNlId()...");
		this.nlId = nlId;
	}

	public String getMessage() {
		logger.info("entering action::SysNamelistManagerAction.getMessage()...");
		return message;
	}

	public void setMessage(String message) {
		logger.info("entering action::SysNamelistManagerAction.setMessage()...");
		this.message = message;
	}

	public List<OptionObjectPair> getStatusList() {
		logger.info("entering action::SysNamelistManagerAction.getStatusList()...");
		return statusList;
	}

	public void setStatusList(List<OptionObjectPair> statusList) {
		logger.info("entering action::SysNamelistManagerAction.setStatusList()...");
		this.statusList = statusList;
	}

	public List<OptionObjectPair> getNlTypeList() {
		logger.info("entering action::SysNamelistManagerAction.getNlTypeList()...");
		return nlTypeList;
	}

	public void setNlTypeList(List<OptionObjectPair> nlTypeList) {
		logger.info("entering action::SysNamelistManagerAction.setNlTypeList()...");
		this.nlTypeList = nlTypeList;
	}

	public IDataDictBiz getDataDictBiz() {
		logger.info("entering action::SysNamelistManagerAction.getDataDictBiz()...");
		return dataDictBiz;
	}

	public void setDataDictBiz(IDataDictBiz dataDictBiz) {
		logger.info("entering action::SysNamelistManagerAction.setDataDictBiz()...");
		this.dataDictBiz = dataDictBiz;
	}

	public IDataDictService getDataDictService() {
		logger.info("entering action::SysNamelistManagerAction.getDataDictService()...");
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		logger.info("entering action::SysNamelistManagerAction.setDataDictService()...");
		this.dataDictService = dataDictService;
	}


	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ISysNamelistBiz getSysNamelistBiz() {
		logger.info("entering action::SysNamelistManagerAction.getSysNamelistBiz()...");
		return sysNamelistBiz;
	}

	public void setSysNamelistBiz(ISysNamelistBiz sysNamelistBiz) {
		logger.info("entering action::SysNamelistManagerAction.setSysNamelistBiz()...");
		this.sysNamelistBiz = sysNamelistBiz;
	}

	public SysNamelist getSysNamelist() {
		logger.info("entering action::SysNamelistManagerAction.getSysNamelist()...");
		return sysNamelist;
	}

	public void setSysNamelist(SysNamelist sysNamelist) {
		logger.info("entering action::SysNamelistManagerAction.setSysNamelist()...");
		this.sysNamelist = sysNamelist;
	}

	public String getId() {
		logger.info("entering action::SysNamelistManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::SysNamelistManagerAction.setId()...");
		this.id = id;
	}

	public BlackListToNoticDto getBlackListToNoticDto() {
		return blackListToNoticDto;
	}

	public void setBlackListToNoticDto(BlackListToNoticDto blackListToNoticDto) {
		this.blackListToNoticDto = blackListToNoticDto;
	}

 
	
	

}

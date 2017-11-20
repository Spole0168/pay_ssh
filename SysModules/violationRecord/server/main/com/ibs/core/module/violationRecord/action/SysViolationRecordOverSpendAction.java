package com.ibs.core.module.violationRecord.action;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.mdmbasedata.biz.IDataDictBiz;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.core.module.violationRecord.biz.ISysViolationRecordBiz;
import com.ibs.core.module.violationRecord.biz.ISysViolationRecordBlackListBiz;
import com.ibs.core.module.violationRecord.biz.ISysViolationRecordOverSpendBiz;
import com.ibs.core.module.violationRecord.domain.SysViolationRecord;
import com.ibs.core.module.violationRecord.domain.SysViolationRecordBlackListDTO;
import com.ibs.core.module.violationRecord.domain.SysViolationRecordIPExceptionDTO;
import com.ibs.core.module.violationRecord.domain.SysViolationRecordOverSpendDTO;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.CollectionUtils;
import com.ibs.portal.framework.util.DateUtils;
import com.ibs.portal.framework.util.StringUtils;

public class SysViolationRecordOverSpendAction extends CrudBaseAction
{
/**
	 * 
	 */
	private static final long serialVersionUID = 7345678790878659242L;
	private ISysViolationRecordBiz sysViolationRecordBiz;
	private String id;
	private SysViolationRecord sysViolationRecord;
	private ISysViolationRecordOverSpendBiz sysViolationRecordOverSpendBiz;
	private IDataDictBiz dataDictBiz;
	private List<OptionObjectPair> violationTypeList;
	private IDataDictService dataDictService;
	private ISysViolationRecordBlackListBiz sysViolationRecordBlackListBiz;
	public IDataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	public ISysViolationRecordOverSpendBiz getSysViolationRecordOverSpendBiz() {
		return sysViolationRecordOverSpendBiz;
	}

	public void setSysViolationRecordOverSpendBiz(ISysViolationRecordOverSpendBiz sysViolationRecordOverSpendBiz) {
		this.sysViolationRecordOverSpendBiz = sysViolationRecordOverSpendBiz;
	}

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::SysViolationRecordManagerAction.list()...");
		load();
		return SUCCESS;
	}
	
	public void load(){
		violationTypeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__VIOLATION_TYPE);
	}
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::SysViolationRecordManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::SysViolationRecordManagerAction.modify()...");
		sysViolationRecord = sysViolationRecordBiz.getSysViolationRecordById(id);
		return SUCCESS;
	}
	
	public String search1() {
		logger.info("entering action::SysViolationRecordManagerAction.search()...");
		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		String searchCondition = getQueryCondition();
		queryPage.setHqlString(searchCondition);
		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<SysViolationRecordIPExceptionDTO> result= (Page<SysViolationRecordIPExceptionDTO>) sysViolationRecordBiz.findViolationRecordTable(queryPage);
		
		Collection<SysViolationRecordIPExceptionDTO> rows2 = result.getRows();
		
		//下拉框对应值的转换
		List<DataDict> col1=dataDictService.list(IDataDictService.DATA_DICT_TYPE__REQ_TYPE);

 
		CollectionUtils.transformCollection(rows2, "transType", "transType", col1, "code", "name");

		setResult(result);
	
		/**
		 * 第三步，返回
		 */
		return AJAX_RETURN_TYPE;
	}
	
	
	public String search2() {
		logger.info("entering action::SysViolationRecordManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		String searchCondition = getQueryCondition();
		queryPage.setHqlString(searchCondition);	
		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<SysViolationRecordBlackListDTO> result= (Page<SysViolationRecordBlackListDTO>) sysViolationRecordBlackListBiz.findViolationRecordBlackListTable(queryPage);
		
		Collection<SysViolationRecordBlackListDTO> rows2 = result.getRows();
		
		//下拉框对应值的转换
		List<DataDict> col1=dataDictService.list(IDataDictService.DATA_DICT_TYPE__REQ_TYPE);
		List<DataDict> col2=dataDictService.list(IDataDictService.DATA_DICT_TYPE__BLACKLIST_TYPE);
 
		CollectionUtils.transformCollection(rows2, "transType", "transType", col1, "code", "name");
		CollectionUtils.transformCollection(rows2, "violationSubType", "violationSubType", col2, "code","name");
		
		setResult(result);		
		/**
		 * 第三步，返回
		 */
		return AJAX_RETURN_TYPE;
	}
	
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search3() {
		logger.info("entering action::SysViolationRecordManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		String searchCondition = getQueryCondition();
		queryPage.setHqlString(searchCondition);
		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<SysViolationRecordOverSpendDTO> result= (Page<SysViolationRecordOverSpendDTO>) sysViolationRecordOverSpendBiz.findViolationRecordOverSpendTable(queryPage);
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
		logger.info("entering action::SysViolationRecordManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			sysViolationRecordBiz.saveSysViolationRecord(sysViolationRecord);
		}
		// 如果是修改
		else {
			sysViolationRecordBiz.updateSysViolationRecord(sysViolationRecord);
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
		logger.info("entering action::SysViolationRecordManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::SysViolationRecordManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		sysViolationRecordBiz.exportSysViolationRecord(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::SysViolationRecordManagerAction.setQueryCondition()...");


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


		// REQ_NUM
		String reqNum = getSearchFields().get("reqNum");
		if (StringUtils.isNotEmpty(reqNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reqNum", "%" + reqNum + "%");
				queryPage.addLikeSearch("reqNum", reqNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reqNum", "%" + reqNum + "%");
				exportSetting.addLikeSearch("reqNum", reqNum);
			}
		}


		// CNL_CNL_CODE
		String cnlCnlCode = getSearchFields().get("cnlCnlCode");
		if (StringUtils.isNotEmpty(cnlCnlCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("cnlCnlCode", "%" + cnlCnlCode + "%");
				queryPage.addLikeSearch("cnlCnlCode", cnlCnlCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("cnlCnlCode", "%" + cnlCnlCode + "%");
				exportSetting.addLikeSearch("cnlCnlCode", cnlCnlCode);
			}
		}


		// CNL_INTF_CODE
		String cnlIntfCode = getSearchFields().get("cnlIntfCode");
		if (StringUtils.isNotEmpty(cnlIntfCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("cnlIntfCode", "%" + cnlIntfCode + "%");
				queryPage.addLikeSearch("cnlIntfCode", cnlIntfCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("cnlIntfCode", "%" + cnlIntfCode + "%");
				exportSetting.addLikeSearch("cnlIntfCode", cnlIntfCode);
			}
		}


		// VIOLATION_TYPE
		String violationType = getSearchFields().get("violationType");
		if (StringUtils.isNotEmpty(violationType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("violationType", "%" + violationType + "%");
				queryPage.addLikeSearch("violationType", violationType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("violationType", "%" + violationType + "%");
				exportSetting.addLikeSearch("violationType", violationType);
			}
		}


		// VIOLATION_ID
		String violationId = getSearchFields().get("violationId");
		if (StringUtils.isNotEmpty(violationId)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("violationId", "%" + violationId + "%");
				queryPage.addLikeSearch("violationId", violationId);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("violationId", "%" + violationId + "%");
				exportSetting.addLikeSearch("violationId", violationId);
			}
		}


		// VIOLATION_DESC
		String violationDesc = getSearchFields().get("violationDesc");
		if (StringUtils.isNotEmpty(violationDesc)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("violationDesc", "%" + violationDesc + "%");
				queryPage.addLikeSearch("violationDesc", violationDesc);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("violationDesc", "%" + violationDesc + "%");
				exportSetting.addLikeSearch("violationDesc", violationDesc);
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

	public ISysViolationRecordBiz getSysViolationRecordBiz() {
		logger.info("entering action::SysViolationRecordManagerAction.getSysViolationRecordBiz()...");
		return sysViolationRecordBiz;
	}

	public void setSysViolationRecordBiz(ISysViolationRecordBiz sysViolationRecordBiz) {
		logger.info("entering action::SysViolationRecordManagerAction.setSysViolationRecordBiz()...");
		this.sysViolationRecordBiz = sysViolationRecordBiz;
	}

	public SysViolationRecord getSysViolationRecord() {
		logger.info("entering action::SysViolationRecordManagerAction.getSysViolationRecord()...");
		return sysViolationRecord;
	}

	public void setSysViolationRecord(SysViolationRecord sysViolationRecord) {
		logger.info("entering action::SysViolationRecordManagerAction.setSysViolationRecord()...");
		this.sysViolationRecord = sysViolationRecord;
	}

	public String getId() {
		logger.info("entering action::SysViolationRecordManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::SysViolationRecordManagerAction.setId()...");
		this.id = id;
	}

	public IDataDictBiz getDataDictBiz() {
		return dataDictBiz;
	}

	public void setDataDictBiz(IDataDictBiz dataDictBiz) {
		this.dataDictBiz = dataDictBiz;
	}

	public List<OptionObjectPair> getViolationTypeList() {
		return violationTypeList;
	}

	public void setViolationTypeList(List<OptionObjectPair> violationTypeList) {
		this.violationTypeList = violationTypeList;
	}

	public ISysViolationRecordBlackListBiz getSysViolationRecordBlackListBiz() {
		return sysViolationRecordBlackListBiz;
	}

	public void setSysViolationRecordBlackListBiz(ISysViolationRecordBlackListBiz sysViolationRecordBlackListBiz) {
		this.sysViolationRecordBlackListBiz = sysViolationRecordBlackListBiz;
	}

	public String getQueryCondition() {
		
		//Map<String, String> searchFields2 = getSearchFields();
		StringBuffer condition = new StringBuffer();
		queryPage.clearQueryCondition();
		queryPage.clearSortMap();
		
				// violationType  根据共同的时间进行查询
				String violationType = getSearchFields().get("violationType");
				if (StringUtils.isNotEmpty(violationType)) {
					condition.append(" and a.violationType = ? ");
					if (null != queryPage) {		
						queryPage.addQueryCondition("  and a.violationType = ? ", violationType );
						queryPage.addLikeSearch("violationType", violationType);
					}
					
				}
	
				//cnlCustCode 商户号				
				String cnlCustCode = getSearchFields().get("cnlCustCode");
				if (StringUtils.isNotEmpty(cnlCustCode)) {
					condition.append(" and a.cnlCustCode like ?");
					if (null != queryPage) {
						queryPage.addQueryCondition("  and a.cnlCustCode like ? ", "%" + cnlCustCode + "%");
						queryPage.addLikeSearch("cnlCustCode", cnlCustCode);
					}
					
				}
				
				//reqNum 渠道申请单号				
				String reqNum = getSearchFields().get("reqNum");
				if (StringUtils.isNotEmpty(reqNum)) {
					condition.append(" and a.reqNum like ?");
					if (null != queryPage) {
						queryPage.addQueryCondition("  and a.reqNum like ? ", "%" + reqNum + "%");
						queryPage.addLikeSearch("reqNum", reqNum);
					}
					
				}
		
				//cnlIntfCode 网关接入号			
				String cnlIntfCode = getSearchFields().get("cnlIntfCode");
				if (StringUtils.isNotEmpty(cnlIntfCode)) {
					condition.append(" and a.cnlIntfCode like ?");
					if (null != queryPage) {
						queryPage.addQueryCondition("  and a.cnlIntfCode like ? ", "%" + cnlIntfCode + "%");
						queryPage.addLikeSearch("cnlIntfCode", cnlIntfCode);
					}
					
				}
				
				
		//开始结束时间修改

				String starTime = getSearchFields().get("starTime");
				String endTime = getSearchFields().get("endTime");
				if(StringUtils.isNotEmpty(starTime)){
					if(null != queryPage){
					 	try {				 	
							condition.append(" and a.createTime >= ? ");
							queryPage.addQueryCondition("createTime", 	DateUtils.convert(starTime,
									DateUtils.DATE_TIME_FORMAT));
				
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				}
				
				if(StringUtils.isNotEmpty(endTime)){
					if(null != queryPage){
					 	try {				 	
					 		condition.append(" and a.createTime<=? ");
							queryPage.addQueryCondition("createTime", 	DateUtils.convert(endTime,
									DateUtils.DATE_TIME_FORMAT));
				
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				}
		
		
		return condition.toString();
	}

	@Override
	public String search() {
		// TODO Auto-generated method stub
		return null;
	}

}

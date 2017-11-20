/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlmgr.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.ibs.core.module.account.domain.CnlCommentDto;
import com.ibs.core.module.cnlcust.dao.ICnlCustCompanyDao;
import com.ibs.core.module.cnlcust.domain.CnlCustCompany;
import com.ibs.core.module.cnlmgr.biz.ICnlCnlCfgBiz;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.core.module.cnlmgr.domain.CnlSysIntf;
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
 * @comments	: code generated based on database T_CNL_CNL_CFG
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CnlCnlCfgManagerAction extends CrudBaseAction {

	private ICnlCnlCfgBiz cnlCnlCfgBiz;
	private CnlSysIntf   cnlSysIntf;
	private CnlCommentDto cnlComment;
	private CnlCustCompany cnlCustCompany;
	private IDataDictBiz  dataDictBiz;
	private CnlCnlCfg cnlCnlCfg;
	private IDataDictService dataDictService;
 
	
	private String id;
	private String csiid;
	private String cnlCustCode;//渠道客户编码
	private String cnlcnlcode;//渠道编号
	
	
	
	
	
	private String data;//判断cnlcustcode这个字段是否存在提示
	
	
	private List<OptionObjectPair> countryList;
	private List<OptionObjectPair> typeList;
	private List<CnlCustCompany>  cnlCustCompanyList;
	private List<CnlCnlCfg> cnlCnlCfgList;
	private ICnlCustCompanyDao cnlCustCompanyDao;
	
 
	
	
	 
	

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CnlCnlCfgManagerAction.list()...");
		
		//查询cnl_cust_company
//		cnlCustCompanyList=cnlCustCompanyDao.getAll();
		cnlCnlCfgList=cnlCnlCfgBiz.findAll();
		
		data=(String)request.getSession().getAttribute("sessionMessage");
		
		
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CnlCnlCfgManagerAction.create()...");
		cnlCustCompanyList=cnlCustCompanyDao.getAll();
		countryList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__COUNTRY);
		typeList=dataDictBiz.listOptions(IDataDictService. DATA_DICT_TYPE__ACCESS_TYPE);
 
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CnlCnlCfgManagerAction.modify()...");
		if (StringUtils.isNotEmpty(cnlCustCode)) {
			 
			cnlComment=cnlCnlCfgBiz.getInfo(cnlCustCode);
			
		}
		cnlComment=cnlCnlCfgBiz.getData(id, csiid, cnlComment);
		
		//渠道客户编码对应的下拉框数据
		cnlCustCompanyList=cnlCustCompanyDao.getAll();
		//国家下拉框
		countryList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__COUNTRY);
		//渠道接入类型下拉框
		typeList=dataDictBiz.listOptions(IDataDictService. DATA_DICT_TYPE__ACCESS_TYPE);
		return SUCCESS;
	}
	
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	@Override
	public String search() {
		logger.info("entering action::CnlCommentManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		//添加条件
		queryPage.setHqlString(getSearchCondition());
		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<CnlCommentDto> result = (Page<CnlCommentDto>) cnlCnlCfgBiz
				.findCommentByPage(queryPage);
		
		Collection<CnlCommentDto> rows2 = result.getRows();
		
		//下拉框对应值的转换
		List<DataDict> col1=dataDictService.list(IDataDictService.DATA_DICT_TYPE__COUNTRY);
		List<DataDict> col2=dataDictService.list(IDataDictService.DATA_DICT_TYPE__ACCESS_TYPE);
 
		CollectionUtils.transformCollection(rows2, "country", "country", col1, "code", "name");
		CollectionUtils.transformCollection(rows2, "accessType", "accessType", col2, "code","name");
		
		
		
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
		logger.info("entering action::CnlCnlCfgManagerAction.saveOrUpdate()...");
		
		String creator=getCurrentUser().getUserName();
		
		
		 
		
		// 如果是新增
		if (this.getIsModify() == false) {
			 
			cnlCnlCfgBiz.saveCnlComment(cnlComment,creator);
			
			request.getSession().setAttribute("sessionMessage", "添加成功!");
			 
			
		
			
		}
		// 如果是修改
		else {
			 
			cnlCnlCfgBiz.updateCnlComment(cnlComment,creator);
			
			request.getSession().setAttribute("sessionMessage", "更新成功!");
			 
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
		logger.info("entering action::CnlCnlCfgManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CnlCnlCfgManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		cnlCnlCfgBiz.exportCnlCnlCfg(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	
	
	

	/**
	 * Action方法，跳转到查看页面
	 * 
	 * @return
	 */
	public String view() {
		logger.info("entering action::CnlCnlCfgManagerAction.cnlCnlCfgView()...");
		if (StringUtils.isNotEmpty(id)) {
			cnlComment=cnlCnlCfgBiz.getCommentByCode(id);
		}
		
		//国家下拉框
		countryList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__COUNTRY);
		//渠道接入类型下拉框
		typeList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__ACCESS_TYPE);
		
		return SUCCESS;
	}
	
	/**
	 * 判断添加的渠道编号是否在数据库中存在
	 * @return
	 */
	public String  checkSame(){
		
		StringBuffer sBuffer = new StringBuffer();
		if (StringUtils.isNotEmpty(cnlcnlcode)) {
			cnlCnlCfgList=cnlCnlCfgBiz.isSame(cnlcnlcode);
			if (cnlCnlCfgList.size()>0) {
				sBuffer.append(false);
			} else {
				sBuffer.append(true);
			}
			PrintWriter pw = null;
			try {
				// System.out.println(response.getCharacterEncoding());
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
	
	/**
	 * 校验cnlcustcode
	 * @return
	 */
	public String checkCnlCustCode() {
		StringBuffer sBuffer = new StringBuffer();
		if (StringUtils.isNotEmpty(cnlCustCode)) {
			cnlComment=cnlCnlCfgBiz.getInfo(cnlCustCode);
			CnlCustCompany ccc=cnlComment.getCnlCustCompany();
			if (null==ccc) {
				sBuffer.append(false);
			} else {
				sBuffer.append(true);
			}
			PrintWriter pw = null;
			try {
				// System.out.println(response.getCharacterEncoding());
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
	
	/**
	 * 根据对应的cnlcustcode获取对应的数据
	 * @return
	 */
	public String findByCnlCustCode(){
		
		cnlComment=cnlCnlCfgBiz.getInfo(cnlCustCode);
		
		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 获取编辑的成功失败的信息之后将session置空
	 * @return
	 */
	public String setSession(){
		
		request.getSession().removeAttribute("sessionMessage");
		data="";
		return AJAX_RETURN_TYPE;
	}
	 
	
	
	
	/**
	 * 设置queryPage参数
	 */
	public void setQueryCondition() {
		logger.info("entering action::CnlCommentManagerAction.setQueryCondition()...");

	 
		
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

 

		// CNL_SYS_NAME
		String cnlSysName = getSearchFields().get("cnlSysName");
		if (StringUtils.isNotEmpty(cnlSysName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("cnlSysName", "%" + cnlSysName + "%");
				queryPage.addLikeSearch("cnlSysName", cnlSysName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("cnlSysName", "%" + cnlSysName + "%");
				exportSetting.addLikeSearch("cnlSysName", cnlSysName);
			}
		}

	}

	
	 

	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCnlcnlcode() {
		logger.info("entering action::CnlCnlCfgManagerAction.getCnlcnlcode()...");
		return cnlcnlcode;
	}

	public void setCnlcnlcode(String cnlcnlcode) {
		logger.info("entering action::CnlCnlCfgManagerAction.setCnlcnlcode()...");
		this.cnlcnlcode = cnlcnlcode;
	}

	public ICnlCnlCfgBiz getCnlCnlCfgBiz() {
		logger.info("entering action::CnlCnlCfgManagerAction.getCnlCnlCfgBiz()...");
		return cnlCnlCfgBiz;
	}

	public void setCnlCnlCfgBiz(ICnlCnlCfgBiz cnlCnlCfgBiz) {
		logger.info("entering action::CnlCnlCfgManagerAction.setCnlCnlCfgBiz()...");
		this.cnlCnlCfgBiz = cnlCnlCfgBiz;
	}

	public CnlCnlCfg getCnlCnlCfg() {
		logger.info("entering action::CnlCnlCfgManagerAction.getCnlCnlCfg()...");
		return cnlCnlCfg;
	}

	public void setCnlCnlCfg(CnlCnlCfg cnlCnlCfg) {
		logger.info("entering action::CnlCnlCfgManagerAction.setCnlCnlCfg()...");
		this.cnlCnlCfg = cnlCnlCfg;
	}

	public String getId() {
		logger.info("entering action::CnlCnlCfgManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CnlCnlCfgManagerAction.setId()...");
		this.id = id;
	}

	public CnlSysIntf getCnlSysIntf() {
		logger.info("entering action::CnlCnlCfgManagerAction.getCnlSysIntf()...");
		return cnlSysIntf;
	}

	public void setCnlSysIntf(CnlSysIntf cnlSysIntf) {
		logger.info("entering action::CnlCnlCfgManagerAction.setCnlSysIntf()...");
		this.cnlSysIntf = cnlSysIntf;
	}
	

	public CnlCommentDto getCnlComment() {
		logger.info("entering action::CnlCnlCfgManagerAction.getCnlComment()...");
		return cnlComment;
	}

	public void setCnlComment(CnlCommentDto cnlComment) {
		logger.info("entering action::CnlCnlCfgManagerAction.setCnlComment()...");
		this.cnlComment = cnlComment;
	}
	

	
	public String getCsiid() {
		logger.info("entering action::CnlCnlCfgManagerAction.getCsiid()...");
		return csiid;
	}

	public void setCsiid(String csiid) {
		logger.info("entering action::CnlCnlCfgManagerAction.setCsiid()...");
		this.csiid = csiid;
	}

	
	
	  

	public CnlCustCompany getCnlCustCompany() {
		return cnlCustCompany;
	}

	public void setCnlCustCompany(CnlCustCompany cnlCustCompany) {
		this.cnlCustCompany = cnlCustCompany;
	}

	public String getCnlCustCode() {
		logger.info("entering action::CnlCnlCfgManagerAction.getCnlCustCode()...");
		return cnlCustCode;
	}

	public void setCnlCustCode(String cnlCustCode) {
		logger.info("entering action::CnlCnlCfgManagerAction.setCnlCustCode()...");
		this.cnlCustCode = cnlCustCode;
	}

	public List<CnlCustCompany> getCnlCustCompanyList() {
		return cnlCustCompanyList;
	}

	public void setCnlCustCompanyList(List<CnlCustCompany> cnlCustCompanyList) {
		this.cnlCustCompanyList = cnlCustCompanyList;
	}

	public IDataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	public List<OptionObjectPair> getCountryList() {
		logger.info("entering action::CnlCnlCfgManagerAction.getCountryList()...");
		return countryList;
	}

	public void setCountryList(List<OptionObjectPair> countryList) {
		logger.info("entering action::CnlCnlCfgManagerAction.setCountryList()...");
		this.countryList = countryList;
	}

	public List<OptionObjectPair> getTypeList() {
		logger.info("entering action::CnlCnlCfgManagerAction.getTypeList()...");
		return typeList;
	}

	public void setTypeList(List<OptionObjectPair> typeList) {
		logger.info("entering action::CnlCnlCfgManagerAction.setTypeList()...");
		this.typeList = typeList;
	}

	public IDataDictBiz getDataDictBiz() {
		logger.info("entering action::CnlCnlCfgManagerAction.getDataDictBiz()...");
		return dataDictBiz;
	}

	public void setDataDictBiz(IDataDictBiz dataDictBiz) {
		logger.info("entering action::CnlCnlCfgManagerAction.getDataDictBiz()...");
		this.dataDictBiz = dataDictBiz;
	}

	
 

 
	public List<CnlCnlCfg> getCnlCnlCfgList() {
		return cnlCnlCfgList;
	}

	public void setCnlCnlCfgList(List<CnlCnlCfg> cnlCnlCfgList) {
		this.cnlCnlCfgList = cnlCnlCfgList;
	}

	public ICnlCustCompanyDao getCnlCustCompanyDao() {
		return cnlCustCompanyDao;
	}

	public void setCnlCustCompanyDao(ICnlCustCompanyDao cnlCustCompanyDao) {
		this.cnlCustCompanyDao = cnlCustCompanyDao;
	}

	/**
	 * 为hql语句生成查询条件
	 */
	public String getSearchCondition(){
		StringBuffer condition = new StringBuffer();
		//清除condition
		queryPage.clearQueryCondition();
		queryPage.clearSortMap();
		
		condition.append(" and cfg.isValid = ?");
		queryPage.addQueryCondition("isValid",Constants.IS_VALID_VALID);
		
		condition.append(" and csi.isValid = ?");
		queryPage.addQueryCondition("isValid",Constants.IS_VALID_VALID);
		
		condition.append(" and ccc.isValid = ?");
		queryPage.addQueryCondition("isValid",Constants.IS_VALID_VALID);
		
		//cnlCnlCode
		String cnlCnlCode = getSearchFields().get("cnlCnlCode");
		if (StringUtils.isNotEmpty(cnlCnlCode)) {
			condition.append(" and cfg.cnlCnlCode like ?");
			queryPage.addQueryCondition("cnlCnlCode", "%"+cnlCnlCode+"%");
		}
		//cnlSysName
		String cnlSysName = getSearchFields().get("cnlSysName");
		if (StringUtils.isNotEmpty(cnlSysName)) { 
			condition.append(" and cfg.cnlSysName like ? ");
			queryPage.addQueryCondition("cnlSysName", "%"+cnlSysName+"%");
		}
		//cnlCustCode
		String cnlCustCode = getSearchFields().get("cnlCustCode");
		
		logger.info("cnlCustCode = "+cnlCustCode);
		
		if (StringUtils.isNotEmpty(cnlCustCode)) {
			condition.append(" and cfg.cnlCustCode = ?");
			queryPage.addQueryCondition("cnlCustCode", cnlCustCode);
		}
		 
		// CREATE_TIME
		String startTime = getSearchFields().get("startTime");
		String endTime   = getSearchFields().get("endTime");
		if (StringUtils.isNotEmpty(startTime)||StringUtils.isNotEmpty(endTime)) {
			
		  
			if (null != queryPage) {
				
				if(StringUtils.isNotEmpty(startTime)){
					logger.info(" startTime= "+startTime);
					queryPage.addQueryCondition("createTime",
							DateUtils.convert(startTime,
									DateUtils.DATE_TIME_FORMAT));
					
					condition.append(" and cfg.createTime >= ? ");
				}
				if(StringUtils.isNotEmpty(endTime)){
					
					queryPage.addQueryCondition("createTime",
							DateUtils.convert(endTime,
									DateUtils.DATE_TIME_FORMAT));
					
					condition.append(" and cfg.createTime <= ? ");
				}
	
			}
		 
		}
		 
		return condition.toString();
	}
	
	
	
}

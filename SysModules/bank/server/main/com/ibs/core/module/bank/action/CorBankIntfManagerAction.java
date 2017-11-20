/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
 //test 7/20AM
package com.ibs.core.module.bank.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ibs.core.module.bank.biz.ICorBankIntfBiz;
import com.ibs.core.module.bank.biz.ICorBankSettingBiz;
import com.ibs.core.module.bank.domain.BankInfoDTO;
import com.ibs.core.module.bank.domain.CorBankInfoDTO;
import com.ibs.core.module.bank.domain.CorBankIntf;
import com.ibs.core.module.bank.domain.CorBankSetting;
import com.ibs.core.module.mdmbasedata.biz.IDataDictBiz;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.core.module.reservedfund.biz.ITcorReservedFundAcntBiz;
import com.ibs.core.module.reservedfund.domain.CorReservedFundAcnt;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.CollectionUtils;
import com.ibs.portal.framework.util.DateUtils;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_INTF
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CorBankIntfManagerAction extends CrudBaseAction {

	////
	private ICorBankIntfBiz corBankIntfBiz;
	private String id;
	private String bid;
	private String message;
	
	private String bankNum;
	
	
	private CorBankIntf corBankIntf;
	private CorBankSetting corBankSetting;
	private ICorBankSettingBiz corBankSettingBiz;
	private  CorBankInfoDTO corBankInfoDTO;
	private	BankInfoDTO bankInfoDTO;
	private String bankInnerCode;
	private List <CorBankIntf> bankInnerCodeList;
 
	private CorReservedFundAcnt corReservedFundAcnt;
	
	private ITcorReservedFundAcntBiz corReservedFundAcntBiz;
	
	
	
 


	


	public ITcorReservedFundAcntBiz getCorReservedFundAcntBiz() {
		return corReservedFundAcntBiz;
	}



	public void setCorReservedFundAcntBiz(ITcorReservedFundAcntBiz corReservedFundAcntBiz) {
		this.corReservedFundAcntBiz = corReservedFundAcntBiz;
	}



	public CorReservedFundAcnt getCorReservedFundAcnt() {
		return corReservedFundAcnt;
	}

 

	public void setCorReservedFundAcnt(CorReservedFundAcnt corReservedFundAcnt) {
		this.corReservedFundAcnt = corReservedFundAcnt;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}


	public List<CorBankIntf> getBankInnerCodeList() {
		return bankInnerCodeList;
	}

	public void setBankInnerCodeList(List<CorBankIntf> bankInnerCodeList) {
		this.bankInnerCodeList = bankInnerCodeList;
	}

	//下拉框专用
	private List<OptionObjectPair> bankNameList;
	private List<OptionObjectPair> countryList;
	private List<OptionObjectPair> bankLevel;
	private IDataDictBiz dataDictBiz;

	private IDataDictService dataDictService;
	
	
	
	public List<OptionObjectPair> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<OptionObjectPair> countryList) {
		this.countryList = countryList;
	}

	public List<OptionObjectPair> getBankLevel() {
		return bankLevel;
	}

	public void setBankLevel(List<OptionObjectPair> bankLevel) {
		this.bankLevel = bankLevel;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBankInnerCode() {
		return bankInnerCode;
	}

	public void setBankInnerCode(String bankInnerCode) {
		this.bankInnerCode = bankInnerCode;
	}

	public IDataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	public List<OptionObjectPair> getBankNameList() {
		return bankNameList;
	}

	public void setBankNameList(List<OptionObjectPair> bankNameList) {
		this.bankNameList = bankNameList;
	}

	public IDataDictBiz getDataDictBiz() {
		return dataDictBiz;
	}

	public void setDataDictBiz(IDataDictBiz dataDictBiz) {
		this.dataDictBiz = dataDictBiz;
	}

	public BankInfoDTO getBankInfoDTO() {
		return bankInfoDTO;
	}

	public void setBankInfoDTO(BankInfoDTO bankInfoDTO) {
		this.bankInfoDTO = bankInfoDTO;
	}

	public ICorBankSettingBiz getCorBankSettingBiz() {
		return corBankSettingBiz;
	}

	public void setCorBankSettingBiz(ICorBankSettingBiz corBankSettingBiz) {
		this.corBankSettingBiz = corBankSettingBiz;
	}

	public CorBankInfoDTO getCorBankInfoDTO() {
		return corBankInfoDTO;
	}

	public void setCorBankInfoDTO(CorBankInfoDTO corBankInfoDTO) {
		this.corBankInfoDTO = corBankInfoDTO;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public CorBankSetting getCorBankSetting() {
		return corBankSetting;
	}

	public void setCorBankSetting(CorBankSetting corBankSetting) {
		this.corBankSetting = corBankSetting;
	}

	/**
	 * Action 显示列表
	 */
	
	public  String showBankInfoJSP( ){
		bankNameList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__BANK_NAME);
		countryList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__COUNTRY);
		bankLevel=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__BANK_LEVEL);
//		String bankInnerCode = request.getParameter("bankInnerCode");
		bankInfoDTO = corBankIntfBiz.getInnerCode(id);
	//	System.out.println("---------------------------------------------------------"+innerCode);
		return SUCCESS;
	}
	 
	
	
	
	
	
	/**
	 * Action方法，显示列表页面
	 * 下拉列表 银行名称
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CorBankIntfManagerAction.list()...");
		
		bankInnerCodeList=corBankIntfBiz.findAll();
		countryList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__COUNTRY);
		bankNameList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__BANK_NAME);
		
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CorBankIntfManagerAction.create()...");
		bankNameList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__BANK_NAME);
		countryList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__COUNTRY);
		bankLevel=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__BANK_LEVEL);
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CorBankIntfManagerAction.modify()...");
		/*corBankIntf = corBankIntfBiz.getCorBankIntfById(id);*/
		bankNameList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__BANK_NAME);
		countryList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__COUNTRY);
		bankLevel=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__BANK_LEVEL);
		corBankInfoDTO=corBankIntfBiz.getBankInnerCode(id, bid);
		return SUCCESS;
	}
	

	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CorBankIntfManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
//		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
  
		//添加条件
		String searchCondition = getQueryCondition();
		//queryPage.clearSortMap();
		queryPage.setHqlString(searchCondition);
		Page<CorBankInfoDTO> result= (Page<CorBankInfoDTO>) corBankIntfBiz.findCorBankInfoByPageAndMoreTable(queryPage);
		 Collection<CorBankInfoDTO> collection = result.getRows();
		 List<DataDict> bankNameList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__BANK_NAME);
		 List<DataDict> countryList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__COUNTRY);
		 List<DataDict> bankLevelList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__BANK_LEVEL);
		 try {								
				CollectionUtils.transformCollection(collection, "bankName",							
						"bankName", bankNameList, "code", "name");		
				CollectionUtils.transformCollection(collection, "country",							
						"country", countryList, "code", "name");		
				CollectionUtils.transformCollection(collection, "bankLevel",							
						"bankLevel", bankLevelList, "code", "name");		
			} catch (Exception e) {								
				throw new BizException(e.getMessage());							
			}
		
		setResult(result);

		/**
		 * 第三步，返回
		 */
		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 保存
	 * 
	 * @returnse
	 * @throws Exception
	 */

	public String saveOrUpdate() {
		logger.info("entering action::CorBankIntfManagerAction.saveOrUpdate()...");
		

		//获取当前操作用户
		String userName=getCurrentUser().getUserName();
		//System.out.println(userName);
		// 如果是新增
		if (this.getIsModify() == false) {
			message = "";
			//zhubiao 
			//System.out.println(corBankInfoDTO);
			CorBankIntf  corBankIntf = new CorBankIntf();
			corBankIntf.setId(null);
			System.out.println("corBankInfoDTO.getCorBankIntf().getBankInnerCode()================="+corBankInfoDTO.getCorBankIntf().getBankInnerCode());
			corBankIntf.setBankInnerCode(corBankInfoDTO.getCorBankIntf().getBankInnerCode());
			corBankIntf.setCountry(corBankInfoDTO.getCorBankIntf().getCountry());
			corBankIntf.setTechSupportName(corBankInfoDTO.getCorBankIntf().getTechSupportName());
			corBankIntf.setTechSupportPhonenum(corBankInfoDTO.getCorBankIntf().getTechSupportPhonenum());
			corBankIntf.setTechSupportEmail(corBankInfoDTO.getCorBankIntf().getTechSupportEmail());
			corBankIntf.setBusinessSupportName(corBankInfoDTO.getCorBankIntf().getBusinessSupportName());
			corBankIntf.setBusinessSupportPhonenum(corBankInfoDTO.getCorBankIntf().getBusinessSupportPhonenum());
			corBankIntf.setBusinessSupportEmail(corBankInfoDTO.getCorBankIntf().getBusinessSupportEmail());
			corBankIntf.setCreateTime(new Date());
			corBankIntf.setCreator(userName);			
			corBankIntfBiz.saveCorBankIntf(corBankIntf);
			//fubiao 
			CorBankSetting corBankSetting = new CorBankSetting();
			corBankSetting.setId(null);
			corBankSetting.setBankInnerCode(corBankInfoDTO.getCorBankIntf().getBankInnerCode());
			corBankSetting.setCountry(corBankInfoDTO.getCorBankSetting().getCountry());
			corBankSetting.setBankName(corBankInfoDTO.getCorBankSetting().getBankName());
			corBankSetting.setBankBranchName(corBankInfoDTO.getCorBankSetting().getBankBranchName());
			corBankSetting.setBankLevel(corBankInfoDTO.getCorBankSetting().getBankLevel());
			corBankSetting.setBankAddr(corBankInfoDTO.getCorBankSetting().getBankAddr());
			corBankSetting.setContractEffectDate(corBankInfoDTO.getCorBankSetting().getContractEffectDate());
			corBankSetting.setContractExpireDate(corBankInfoDTO.getCorBankSetting().getContractExpireDate());
			corBankSetting.setBankNum(corBankInfoDTO.getCorBankSetting().getBankNum());
			corBankSetting.setSwiftCode(corBankInfoDTO.getCorBankSetting().getSwiftCode());
			corBankSetting.setBranchCode(corBankInfoDTO.getCorBankSetting().getBranchCode());
			corBankSetting.setNgsnCode(corBankInfoDTO.getCorBankSetting().getNgsnCode());
			corBankSetting.setDesc(corBankInfoDTO.getCorBankSetting().getDesc());
			corBankSetting.setCreateTime(new Date());
			corBankSetting.setCreator(userName);	
			corBankSettingBiz.saveCorBankSetting(corBankSetting);
			message = "新增成功";
			return SUCCESS;
					
		}
		// 如果是修改
		else {
			message = "";
			String id = corBankInfoDTO.getCorBankIntf().getId();
			String bid = corBankInfoDTO.getCorBankSetting().getId();
			CorBankIntf  corBankIntf = corBankIntfBiz.getCorBankIntfById(id);
			corBankIntf.setBankInnerCode(corBankInfoDTO.getCorBankIntf().getBankInnerCode());
			corBankIntf.setCountry(corBankInfoDTO.getCorBankIntf().getCountry());
			corBankIntf.setTechSupportName(corBankInfoDTO.getCorBankIntf().getTechSupportName());
			corBankIntf.setTechSupportPhonenum(corBankInfoDTO.getCorBankIntf().getTechSupportPhonenum());
			corBankIntf.setTechSupportEmail(corBankInfoDTO.getCorBankIntf().getTechSupportEmail());
			corBankIntf.setBusinessSupportName(corBankInfoDTO.getCorBankIntf().getBusinessSupportName());
			corBankIntf.setBusinessSupportPhonenum(corBankInfoDTO.getCorBankIntf().getBusinessSupportPhonenum());
			corBankIntf.setBusinessSupportEmail(corBankInfoDTO.getCorBankIntf().getBusinessSupportEmail());
/*			corBankIntf.setCreateTime(corBankInfoDTO.getCorBankIntf().getCreateTime());
			corBankIntf.setCreator(corBankInfoDTO.getCorBankIntf().getCreator());	*/	
			
			corBankIntfBiz.updateCorBankIntf(corBankIntf);
			
			CorBankSetting corBankSetting = corBankSettingBiz.getCorBankSettingById(bid);
			corBankSetting.setBankInnerCode(corBankInfoDTO.getCorBankIntf().getBankInnerCode());
			corBankSetting.setCountry(corBankInfoDTO.getCorBankSetting().getCountry());
			corBankSetting.setBankName(corBankInfoDTO.getCorBankSetting().getBankName());
			corBankSetting.setBankBranchName(corBankInfoDTO.getCorBankSetting().getBankBranchName());
			corBankSetting.setBankLevel(corBankInfoDTO.getCorBankSetting().getBankLevel());
			corBankSetting.setBankAddr(corBankInfoDTO.getCorBankSetting().getBankAddr());
			corBankSetting.setContractEffectDate(corBankInfoDTO.getCorBankSetting().getContractEffectDate());
			corBankSetting.setContractExpireDate(corBankInfoDTO.getCorBankSetting().getContractExpireDate());
			corBankSetting.setBankNum(corBankInfoDTO.getCorBankSetting().getBankNum());
			corBankSetting.setSwiftCode(corBankInfoDTO.getCorBankSetting().getSwiftCode());
			corBankSetting.setBranchCode(corBankInfoDTO.getCorBankSetting().getBranchCode());
			corBankSetting.setNgsnCode(corBankInfoDTO.getCorBankSetting().getNgsnCode());
			corBankSetting.setDesc(corBankInfoDTO.getCorBankSetting().getDesc());
/*			corBankSetting.setCreateTime(corBankInfoDTO.getCorBankSetting().getCreateTime());
			corBankSetting.setCreator(corBankInfoDTO.getCorBankSetting().getCreator());	*/
			
			
			corBankSettingBiz.updateCorBankSetting(corBankSetting);
			message = "修改成功";
			return SUCCESS;
		}

	}
	
	public void saveOrUp() {
		logger.info("entering action::CorBankIntfManagerAction.saveOrUpdate()...");
		

		//获取当前操作用户
		String userName=getCurrentUser().getUserName();
		//System.out.println(userName);
		// 如果是新增
		try{
			if (this.getIsModify() == false) {
				message = "";
				//zhubiao 
				//System.out.println(corBankInfoDTO);
				CorBankIntf  corBankIntf = new CorBankIntf();
				corBankIntf.setId(null);
				corBankIntf.setBankInnerCode(corBankInfoDTO.getCorBankIntf().getBankInnerCode());
				corBankIntf.setCountry(corBankInfoDTO.getCorBankIntf().getCountry());
				corBankIntf.setTechSupportName(corBankInfoDTO.getCorBankIntf().getTechSupportName());
				corBankIntf.setTechSupportPhonenum(corBankInfoDTO.getCorBankIntf().getTechSupportPhonenum());
				corBankIntf.setTechSupportEmail(corBankInfoDTO.getCorBankIntf().getTechSupportEmail());
				corBankIntf.setBusinessSupportName(corBankInfoDTO.getCorBankIntf().getBusinessSupportName());
				corBankIntf.setBusinessSupportPhonenum(corBankInfoDTO.getCorBankIntf().getBusinessSupportPhonenum());
				corBankIntf.setBusinessSupportEmail(corBankInfoDTO.getCorBankIntf().getBusinessSupportEmail());
				corBankIntf.setCreateTime(new Date());
				corBankIntf.setCreator(userName);			
				corBankIntf.setIsValid(Constants.IS_VALID_VALID);
				CorBankIntf cbi = corBankIntfBiz.saveCorBankIntf(corBankIntf);
				//fubiao 
				CorBankSetting corBankSetting = new CorBankSetting();
				corBankSetting.setId(null);
				corBankSetting.setBankInnerCode(corBankInfoDTO.getCorBankIntf().getBankInnerCode());
				corBankSetting.setCountry(corBankInfoDTO.getCorBankIntf().getCountry());
				
				corBankSetting.setBankCode(corBankInfoDTO.getCorBankSetting().getBankName());
				String bankName = dataDictService.getCodeName(IDataDictService.DATA_DICT_TYPE__BANK_NAME, corBankInfoDTO.getCorBankSetting().getBankName());
				corBankSetting.setBankName(bankName);
				
				corBankSetting.setBankBranchName(corBankInfoDTO.getCorBankSetting().getBankBranchName());
				corBankSetting.setBankLevel(corBankInfoDTO.getCorBankSetting().getBankLevel());
				corBankSetting.setBankAddr(corBankInfoDTO.getCorBankSetting().getBankAddr());
				corBankSetting.setContractEffectDate(corBankInfoDTO.getCorBankSetting().getContractEffectDate());
				corBankSetting.setContractExpireDate(corBankInfoDTO.getCorBankSetting().getContractExpireDate());
				corBankSetting.setBankNum(corBankInfoDTO.getCorBankSetting().getBankNum());
				corBankSetting.setSwiftCode(corBankInfoDTO.getCorBankSetting().getSwiftCode());
				corBankSetting.setBranchCode(corBankInfoDTO.getCorBankSetting().getBranchCode());
				corBankSetting.setNgsnCode(corBankInfoDTO.getCorBankSetting().getNgsnCode());
				corBankSetting.setDesc(corBankInfoDTO.getCorBankSetting().getDesc());
				corBankSetting.setCreateTime(new Date());
				corBankSetting.setCreator(userName);	
				corBankSetting.setIsValid(Constants.IS_VALID_VALID);
				CorBankSetting cbs = corBankSettingBiz.saveCorBankSetting(corBankSetting);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/plain;charset=UTF-8");
				if(cbs != null && cbi != null){
					response.getWriter().write("1");
				}else{
					response.getWriter().write("0");
				}
						
			}
			// 如果是修改
			else {
				message = "";
				String id = corBankInfoDTO.getCorBankIntf().getId();
				String bid = corBankInfoDTO.getCorBankSetting().getId();
				CorBankIntf  corBankIntf = corBankIntfBiz.getCorBankIntfById(id);
				corBankIntf.setBankInnerCode(corBankInfoDTO.getCorBankIntf().getBankInnerCode());
				corBankIntf.setCountry(corBankInfoDTO.getCorBankIntf().getCountry());
				corBankIntf.setTechSupportName(corBankInfoDTO.getCorBankIntf().getTechSupportName());
				corBankIntf.setTechSupportPhonenum(corBankInfoDTO.getCorBankIntf().getTechSupportPhonenum());
				corBankIntf.setTechSupportEmail(corBankInfoDTO.getCorBankIntf().getTechSupportEmail());
				corBankIntf.setBusinessSupportName(corBankInfoDTO.getCorBankIntf().getBusinessSupportName());
				corBankIntf.setBusinessSupportPhonenum(corBankInfoDTO.getCorBankIntf().getBusinessSupportPhonenum());
				corBankIntf.setBusinessSupportEmail(corBankInfoDTO.getCorBankIntf().getBusinessSupportEmail());
	/*			corBankIntf.setCreateTime(corBankInfoDTO.getCorBankIntf().getCreateTime());
				corBankIntf.setCreator(corBankInfoDTO.getCorBankIntf().getCreator());	*/	
				CorBankIntf cbi = corBankIntfBiz.updateCorBankIntf(corBankIntf);
				
				CorBankSetting corBankSetting = corBankSettingBiz.getCorBankSettingById(bid);
				corBankSetting.setBankInnerCode(corBankInfoDTO.getCorBankIntf().getBankInnerCode());
				corBankSetting.setCountry(corBankInfoDTO.getCorBankSetting().getCountry());
				corBankSetting.setBankCode(corBankInfoDTO.getCorBankSetting().getBankName());
				
				String bankName = dataDictService.getCodeName(IDataDictService.DATA_DICT_TYPE__BANK_NAME, corBankInfoDTO.getCorBankSetting().getBankName());
				corBankSetting.setBankName(bankName);
				
				corBankSetting.setBankBranchName(corBankInfoDTO.getCorBankSetting().getBankBranchName());
				corBankSetting.setBankLevel(corBankInfoDTO.getCorBankSetting().getBankLevel());
				corBankSetting.setBankAddr(corBankInfoDTO.getCorBankSetting().getBankAddr());
				corBankSetting.setContractEffectDate(corBankInfoDTO.getCorBankSetting().getContractEffectDate());
				corBankSetting.setContractExpireDate(corBankInfoDTO.getCorBankSetting().getContractExpireDate());
				corBankSetting.setBankNum(corBankInfoDTO.getCorBankSetting().getBankNum());
				corBankSetting.setSwiftCode(corBankInfoDTO.getCorBankSetting().getSwiftCode());
				corBankSetting.setBranchCode(corBankInfoDTO.getCorBankSetting().getBranchCode());
				corBankSetting.setNgsnCode(corBankInfoDTO.getCorBankSetting().getNgsnCode());
				corBankSetting.setDesc(corBankInfoDTO.getCorBankSetting().getDesc());
	/*			corBankSetting.setCreateTime(corBankInfoDTO.getCorBankSetting().getCreateTime());
				corBankSetting.setCreator(corBankInfoDTO.getCorBankSetting().getCreator());	*/
				
				
				CorBankSetting cbs = corBankSettingBiz.updateCorBankSetting(corBankSetting);
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/plain;charset=UTF-8");
				if(cbs != null && cbi != null){
					response.getWriter().write("1");
				}else{
					response.getWriter().write("0");
				}
			}
		}catch(Exception e){
			
		}

	}
	
	/**
	 * Action方法，批量删除数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() {
		logger.info("entering action::CorBankIntfManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 判断银行编号是否重复
	 * @return
	 */
	public void  isSame(){
		try{
		if (StringUtils.isNotEmpty(bankInnerCode)) {
			List<CorBankIntf> list=corBankIntfBiz.isSame(bankInnerCode);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=UTF-8");
			if(list.size() > 0){
				response.getWriter().write("1");
			}else{
				response.getWriter().write("0");
			}
		}
		}catch(Exception e){
			
		}
	}
	
	
	/**
	 * 判断添加的银行编号是否在数据库中存在
	 * @return
	 */
	public String  isSameEdit(){
		
		boolean  falg=corBankIntfBiz.isSameEdit(bankInnerCode, id);
		
		if(falg){
			
		  message="您添加的银行编码已经存在!";
		  logger.info("  "+message );
		}
		
		return AJAX_RETURN_TYPE;
	}
	
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CorBankIntfManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		corBankIntfBiz.exportCorBankIntf(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CorBankIntfManagerAction.setQueryCondition()...");


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


		// BANK_INNER_CODE
		String bankInnerCode = getSearchFields().get("bankInnerCode");
		if (StringUtils.isNotEmpty(bankInnerCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankInnerCode", "%" + bankInnerCode + "%");
				queryPage.addLikeSearch("bankInnerCode", bankInnerCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankInnerCode", "%" + bankInnerCode + "%");
				exportSetting.addLikeSearch("bankInnerCode", bankInnerCode);
			}
		}


		// INTF_CODE
		String intfCode = getSearchFields().get("intfCode");
		if (StringUtils.isNotEmpty(intfCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("intfCode", "%" + intfCode + "%");
				queryPage.addLikeSearch("intfCode", intfCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("intfCode", "%" + intfCode + "%");
				exportSetting.addLikeSearch("intfCode", intfCode);
			}
		}


		// COUNTRY
		String country = getSearchFields().get("country");
		if (StringUtils.isNotEmpty(country)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("country", "%" + country + "%");
				queryPage.addLikeSearch("country", country);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("country", "%" + country + "%");
				exportSetting.addLikeSearch("country", country);
			}
		}


		// BANK_CODE
		String bankCode = getSearchFields().get("bankCode");
		if (StringUtils.isNotEmpty(bankCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCode", "%" + bankCode + "%");
				queryPage.addLikeSearch("bankCode", bankCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCode", "%" + bankCode + "%");
				exportSetting.addLikeSearch("bankCode", bankCode);
			}
		}


		// VERIFICATION_TYPE
		String verificationType = getSearchFields().get("verificationType");
		if (StringUtils.isNotEmpty(verificationType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("verificationType", "%" + verificationType + "%");
				queryPage.addLikeSearch("verificationType", verificationType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("verificationType", "%" + verificationType + "%");
				exportSetting.addLikeSearch("verificationType", verificationType);
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


		// COMMENTS
		String comments = getSearchFields().get("comments");
		if (StringUtils.isNotEmpty(comments)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("comments", "%" + comments + "%");
				queryPage.addLikeSearch("comments", comments);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("comments", "%" + comments + "%");
				exportSetting.addLikeSearch("comments", comments);
			}
		}


		// DIRECTION
		String direction = getSearchFields().get("direction");
		if (StringUtils.isNotEmpty(direction)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("direction", "%" + direction + "%");
				queryPage.addLikeSearch("direction", direction);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("direction", "%" + direction + "%");
				exportSetting.addLikeSearch("direction", direction);
			}
		}


		// SERVICE_URL
		String serviceUrl = getSearchFields().get("serviceUrl");
		if (StringUtils.isNotEmpty(serviceUrl)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("serviceUrl", "%" + serviceUrl + "%");
				queryPage.addLikeSearch("serviceUrl", serviceUrl);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("serviceUrl", "%" + serviceUrl + "%");
				exportSetting.addLikeSearch("serviceUrl", serviceUrl);
			}
		}


		// SERVICE_URL_FORMAT
		String serviceUrlFormat = getSearchFields().get("serviceUrlFormat");
		if (StringUtils.isNotEmpty(serviceUrlFormat)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("serviceUrlFormat", "%" + serviceUrlFormat + "%");
				queryPage.addLikeSearch("serviceUrlFormat", serviceUrlFormat);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("serviceUrlFormat", "%" + serviceUrlFormat + "%");
				exportSetting.addLikeSearch("serviceUrlFormat", serviceUrlFormat);
			}
		}


		// TECH_SUPPORT_NAME
		String techSupportName = getSearchFields().get("techSupportName");
		if (StringUtils.isNotEmpty(techSupportName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("techSupportName", "%" + techSupportName + "%");
				queryPage.addLikeSearch("techSupportName", techSupportName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("techSupportName", "%" + techSupportName + "%");
				exportSetting.addLikeSearch("techSupportName", techSupportName);
			}
		}


		// TECH_SUPPORT_PHONENUM
		String techSupportPhonenum = getSearchFields().get("techSupportPhonenum");
		if (StringUtils.isNotEmpty(techSupportPhonenum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("techSupportPhonenum", "%" + techSupportPhonenum + "%");
				queryPage.addLikeSearch("techSupportPhonenum", techSupportPhonenum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("techSupportPhonenum", "%" + techSupportPhonenum + "%");
				exportSetting.addLikeSearch("techSupportPhonenum", techSupportPhonenum);
			}
		}


		// TECH_SUPPORT_EMAIL
		String techSupportEmail = getSearchFields().get("techSupportEmail");
		if (StringUtils.isNotEmpty(techSupportEmail)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("techSupportEmail", "%" + techSupportEmail + "%");
				queryPage.addLikeSearch("techSupportEmail", techSupportEmail);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("techSupportEmail", "%" + techSupportEmail + "%");
				exportSetting.addLikeSearch("techSupportEmail", techSupportEmail);
			}
		}


		// BUSINESS_SUPPORT_NAME
		String businessSupportName = getSearchFields().get("businessSupportName");
		if (StringUtils.isNotEmpty(businessSupportName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("businessSupportName", "%" + businessSupportName + "%");
				queryPage.addLikeSearch("businessSupportName", businessSupportName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("businessSupportName", "%" + businessSupportName + "%");
				exportSetting.addLikeSearch("businessSupportName", businessSupportName);
			}
		}


		// BUSINESS_SUPPORT_PHONENUM
		String businessSupportPhonenum = getSearchFields().get("businessSupportPhonenum");
		if (StringUtils.isNotEmpty(businessSupportPhonenum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("businessSupportPhonenum", "%" + businessSupportPhonenum + "%");
				queryPage.addLikeSearch("businessSupportPhonenum", businessSupportPhonenum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("businessSupportPhonenum", "%" + businessSupportPhonenum + "%");
				exportSetting.addLikeSearch("businessSupportPhonenum", businessSupportPhonenum);
			}
		}


		// BUSINESS_SUPPORT_EMAIL
		String businessSupportEmail = getSearchFields().get("businessSupportEmail");
		if (StringUtils.isNotEmpty(businessSupportEmail)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("businessSupportEmail", "%" + businessSupportEmail + "%");
				queryPage.addLikeSearch("businessSupportEmail", businessSupportEmail);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("businessSupportEmail", "%" + businessSupportEmail + "%");
				exportSetting.addLikeSearch("businessSupportEmail", businessSupportEmail);
			}
		}


		// IS_VALID
		String isValid = getSearchFields().get("isValid");
		if (StringUtils.isNotEmpty(isValid)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("isValid", "%" + isValid + "%");
				queryPage.addLikeSearch("isValid", isValid);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("isValid", "%" + isValid + "%");
				exportSetting.addLikeSearch("isValid", isValid);
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

	public ICorBankIntfBiz getCorBankIntfBiz() {
		logger.info("entering action::CorBankIntfManagerAction.getCorBankIntfBiz()...");
		return corBankIntfBiz;
	}

	public void setCorBankIntfBiz(ICorBankIntfBiz corBankIntfBiz) {
		logger.info("entering action::CorBankIntfManagerAction.setCorBankIntfBiz()...");
		this.corBankIntfBiz = corBankIntfBiz;
	}

	public CorBankIntf getCorBankIntf() {
		logger.info("entering action::CorBankIntfManagerAction.getCorBankIntf()...");
		return corBankIntf;
	}

	public void setCorBankIntf(CorBankIntf corBankIntf) {
		logger.info("entering action::CorBankIntfManagerAction.setCorBankIntf()...");
		this.corBankIntf = corBankIntf;
	}

	public String getId() {
		logger.info("entering action::CorBankIntfManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CorBankIntfManagerAction.setId()...");
		this.id = id;
	}

	/**
	* @Title: getQueryCondition
	* @Description: TODO(条件查询)
	* @param @return 拼接后的结果   设定文件
	* @return String    返回类型 
	* @throws
	 */
	@SuppressWarnings("unused")
	public String getQueryCondition() {
		Map<String, String> searchFields2 = getSearchFields();
		StringBuffer condition = new StringBuffer();
		queryPage.clearQueryCondition();
		queryPage.clearSortMap();
		condition.append(" and a.isValid = ?");
		queryPage.addQueryCondition("  and a.isValid = ? ", Constants.IS_VALID_VALID);
		queryPage.addLikeSearch("isValid", Constants.IS_VALID_VALID);
		
		condition.append(" and b.isValid = ?");
		queryPage.addQueryCondition("  and b.isValid = ? ", Constants.IS_VALID_VALID);
		queryPage.addLikeSearch("isValid", Constants.IS_VALID_VALID);
		
		// BANK_INNER_CODE  根据共同的时间进行查询
		String bankInnerCode = getSearchFields().get("bankInnerCode");
	 
		if (StringUtils.isNotEmpty(bankInnerCode)) {
			condition.append(" and a.bankInnerCode = ?");
			if (null != queryPage) {
				queryPage.addQueryCondition("  and a.bankInnerCode = ? ", bankInnerCode );
				queryPage.addLikeSearch("bankInnerCode", bankInnerCode);
			}
		 
		}
		
		// BANK_NAME
		
		
		String bankName = getSearchFields().get("bankName");	
		System.out.println("++++++++++++++++++++++++++++++++++++++)))" +bankName);
		if (StringUtils.isNotEmpty(bankName) && !"bankName".equals(bankName)) {
			if (null != queryPage) {
				condition.append(" and b.bankName = ? ");
				queryPage.addQueryCondition(" and b.bankName = ? ", bankName );
				queryPage.addLikeSearch("bankName", bankName);
			}		
		}
		
		// bankBranchName
		String bankBranchName = getSearchFields().get("bankBranchName");
		if (StringUtils.isNotEmpty(bankBranchName)) {
			
			if (null != queryPage) {
				condition.append(" and b.bankBranchName = ? ");
				queryPage.addQueryCondition(" and b.bankBranchName = ? ", "%" + bankBranchName + "%");
				queryPage.addLikeSearch("bankBranchName", bankBranchName);
			}
		};
	

		//	bankCode银行代码
		// bankNum.
		
		String bankNum = getSearchFields().get("bankNum");
	
	
				if (StringUtils.isNotEmpty(bankNum)) {
					condition.append(" and b.bankNum = ? ");
					if (null != queryPage) {
						queryPage.addQueryCondition("  and b.bankNum = ? ", bankNum );
						queryPage.addLikeSearch("bankNum", bankNum);
					}
					if (null != exportSetting) {
						exportSetting.addQueryCondition("b.bankNum", "%" + bankNum + "%");
						exportSetting.addLikeSearch("bankNum", bankNum);
					}
				}
	
				
				// bankLevel
				String bankLevel = getSearchFields().get("bankLevel");
				if (StringUtils.isNotEmpty(bankLevel)) {
					condition.append(" and b.bankLevel = ? ");
					if (null != queryPage) {
						queryPage.addQueryCondition("  and b.bankLevel = ? ",  bankLevel );
						queryPage.addLikeSearch("bankLevel", bankLevel);
					}
					if (null != exportSetting) {
						exportSetting.addQueryCondition("bankLevel", "%" + bankLevel + "%");
						exportSetting.addLikeSearch("bankLevel", bankLevel);
					}
				}
		
	//CREATE_TIME
		// startTime/ endTime 时间的between查询 需求暂时不要 使用DATE_TIME_FORMAT 转型自己写的不能用  
		String starTime = getSearchFields().get("starTime");
		String endTime = getSearchFields().get("endTime");
		System.out.println(starTime);
		System.out.println(endTime);
		if(StringUtils.isNotEmpty(starTime)){
			if(null != queryPage){
			 	try {				 	
					condition.append(" and b.createTime >= ? ");
					queryPage.addQueryCondition("createTime", 	DateUtils.convert(starTime,
							DateUtils.DATE_TIME_FORMAT));
		
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		if(StringUtils.isNotEmpty(endTime)){
			if(null != queryPage){
			 	try {				 	
			 		condition.append(" and b.createTime <= ? ");
					queryPage.addQueryCondition("createTime", 	DateUtils.convert(endTime,
							DateUtils.DATE_TIME_FORMAT));
		
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
				
		
		return condition.toString();
	}
}

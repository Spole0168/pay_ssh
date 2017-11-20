package com.ibs.core.module.bank.action;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ibs.core.module.bank.biz.ICorBankSettingBiz;
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



@SuppressWarnings("serial")
public class CorReservedFundAcntAction extends CrudBaseAction{

	private String bankInnerCode;
	private ITcorReservedFundAcntBiz corReservedFundAcntBiz;
	private ICorBankSettingBiz corBankSettingBiz;
	private String FindBanknum;
	private CorReservedFundAcnt corReservedFundAcnt;
	private String id;
	private String bankCardNum;
	private List<OptionObjectPair> certTypeList;
	private List<OptionObjectPair> currencyList;
	private List<OptionObjectPair> reservedTypeList;
	
	private String currency;
	private String certType;
	
	private IDataDictBiz dataDictBiz;
	private IDataDictService dataDictService; 
	
	@Override
	public String create() {
		// TODO Auto-generated method stub
		certTypeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CERT_TYPE);
		currencyList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		reservedTypeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__BANK_RESERVED_TYPE);
		HttpServletRequest request=ServletActionContext.getRequest();
		bankInnerCode = (String)request.getSession().getAttribute("bankInnerCode");
		return SUCCESS;
	}

	@Override
	public String modify() {
		// TODO Auto-generated method stub
		corReservedFundAcnt = corReservedFundAcntBiz.getCorReservedFundAcntById(id);
		currencyList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		certTypeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CERT_TYPE);
		reservedTypeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__BANK_RESERVED_TYPE);
		HttpServletRequest request=ServletActionContext.getRequest();
		bankInnerCode = (String)request.getSession().getAttribute("bankInnerCode");
		return SUCCESS;
	}
	
	public void saveOrUp() {
		// TODO Auto-generated method stub
		try{
			if (this.getIsModify() == false) {
				CorReservedFundAcnt crfa = new CorReservedFundAcnt();
				CorBankSetting cbs = corBankSettingBiz.findByValue(bankInnerCode);
				System.out.println("bankInnerCode================"+bankInnerCode);
				crfa.setBankAcntCode(bankInnerCode);
				crfa.setCustName(corReservedFundAcnt.getCustName());
				System.out.println("corReservedFundAcnt.getCustName()================"+corReservedFundAcnt.getCustName());
				crfa.setCertType(corReservedFundAcnt.getCertType());
				System.out.println("corReservedFundAcnt.getCertType()================"+corReservedFundAcnt.getCertType());
				crfa.setCertNum(corReservedFundAcnt.getCertNum());
				System.out.println("corReservedFundAcnt.getCertNum()================"+corReservedFundAcnt.getCertNum());
				crfa.setBankCardNum(corReservedFundAcnt.getBankCardNum());
				System.out.println("corReservedFundAcnt.getBankCardNum()================"+corReservedFundAcnt.getBankCardNum());
				crfa.setCurrency(corReservedFundAcnt.getCurrency());
				System.out.println("corReservedFundAcnt.getCurrency()================"+corReservedFundAcnt.getCurrency());
				crfa.setBankNum(cbs.getBankNum());
				System.out.println("crfa1.getBankNum()================"+cbs.getBankNum());
				crfa.setAcntCategory(corReservedFundAcnt.getAcntCategory());
				System.out.println("corReservedFundAcnt.getAcntCategory()================"+corReservedFundAcnt.getAcntCategory());
				crfa.setIsValid(Constants.IS_VALID_VALID);
				
				CorReservedFundAcnt savecrfa = corReservedFundAcntBiz.saveCorReservedFundAcnt(crfa);
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/plain;charset=UTF-8");
				if(savecrfa != null){
					response.getWriter().write("1");
				}else{
					response.getWriter().write("0");
				}
				
			}else{
				CorReservedFundAcnt crf = new CorReservedFundAcnt();
				crf.setId(id);
				crf.setBankAcntCode(corReservedFundAcnt.getBankAcntCode());
				crf.setCustName(corReservedFundAcnt.getCustName());
				crf.setCertType(corReservedFundAcnt.getCertType());
				crf.setCertNum(corReservedFundAcnt.getCertNum());
				crf.setBankCardNum(corReservedFundAcnt.getBankCardNum());
				crf.setBankNum(corReservedFundAcnt.getBankNum());
				crf.setCurrency(corReservedFundAcnt.getCurrency());
				crf.setAcntCategory(corReservedFundAcnt.getAcntCategory());
				crf.setIsValid(Constants.IS_VALID_VALID);
				
				CorReservedFundAcnt savecrfa = corReservedFundAcntBiz.updateCorReservedFundAcnt(crf);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/plain;charset=UTF-8");
				if(savecrfa != null){
					response.getWriter().write("1");
				}else{
					response.getWriter().write("0");
				}
			}
		}catch(Exception e){
			
		}
	}

	@Override
	public String saveOrUpdate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void BankNumCheck(){
		try{
			boolean falg = corReservedFundAcntBiz.CheckBankNum(bankInnerCode,bankCardNum,id);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=UTF-8");
			if(falg == true){
				response.getWriter().write("0"); 
			}else{
				response.getWriter().write("-1"); 
			}
		}catch(Exception e){
			
		}
	}
	
	public void saveCheckBankNum(){
		try{
			boolean falg = corReservedFundAcntBiz.CheckBankNum(bankInnerCode,bankCardNum);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=UTF-8");
			if(falg == true){
				response.getWriter().write("-1"); 
			}else{
				response.getWriter().write("0"); 
			}
		}catch(Exception e){
			
		}
	}
	@Override
	public String list() {
		// TODO Auto-generated method stub
		return null;
	}
	public String BankNum(){
		HttpServletRequest request=ServletActionContext.getRequest();
		request.getSession().setAttribute("bankInnerCode",bankInnerCode);
		bankInnerCode = (String)request.getSession().getAttribute("bankInnerCode");
		return SUCCESS;
	}
	
	@Override
	public String search() {
		// TODO Auto-generated method stub
		Page<CorReservedFundAcnt> result = (Page<CorReservedFundAcnt>) corReservedFundAcntBiz.findByBankNum(queryPage,bankInnerCode);
		Collection<CorReservedFundAcnt> crfa = result.getRows();
		List<DataDict> certTypelist = dataDictService.list(IDataDictService.DATA_DICT_TYPE__CERT_TYPE);
		List<DataDict> Currencylist = dataDictService.list(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		List<DataDict> reservedList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__BANK_RESERVED_TYPE);
		try{
			CollectionUtils.transformCollection(crfa, "certType","certType", certTypelist, "code", "name");	
			CollectionUtils.transformCollection(crfa, "currency","currency", Currencylist, "code", "name");	
			CollectionUtils.transformCollection(crfa, "acntCategory","acntCategory", reservedList, "code", "name");
		}catch(Exception e){
			throw new BizException(e.getMessage());		
		}
		setResult(result);
		return AJAX_RETURN_TYPE;
	}

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void deleteById() {
		// TODO Auto-generated method stub
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=UTF-8");
			corReservedFundAcntBiz.deleteById(id);
			response.getWriter().write("1");
		}catch(Exception e){
			
		}
	}

	@Override
	public String export() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getBankInnerCode() {
		return bankInnerCode;
	}

	public void setBankInnerCode(String bankInnerCode) {
		this.bankInnerCode = bankInnerCode;
	}

	public ITcorReservedFundAcntBiz getCorReservedFundAcntBiz() {
		return corReservedFundAcntBiz;
	}

	public void setCorReservedFundAcntBiz(ITcorReservedFundAcntBiz corReservedFundAcntBiz) {
		this.corReservedFundAcntBiz = corReservedFundAcntBiz;
	}

	public String getFindBanknum() {
		return FindBanknum;
	}

	public void setFindBanknum(String findBanknum) {
		FindBanknum = findBanknum;
	}

	public CorReservedFundAcnt getCorReservedFundAcnt() {
		return corReservedFundAcnt;
	}

	public void setCorReservedFundAcnt(CorReservedFundAcnt corReservedFundAcnt) {
		this.corReservedFundAcnt = corReservedFundAcnt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBankCardNum() {
		return bankCardNum;
	}

	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}

	public List<OptionObjectPair> getCertTypeList() {
		return certTypeList;
	}

	public void setCertTypeList(List<OptionObjectPair> certTypeList) {
		this.certTypeList = certTypeList;
	}

	public List<OptionObjectPair> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<OptionObjectPair> currencyList) {
		this.currencyList = currencyList;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public IDataDictBiz getDataDictBiz() {
		return dataDictBiz;
	}

	public void setDataDictBiz(IDataDictBiz dataDictBiz) {
		this.dataDictBiz = dataDictBiz;
	}

	public IDataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	public List<OptionObjectPair> getReservedTypeList() {
		return reservedTypeList;
	}

	public void setReservedTypeList(List<OptionObjectPair> reservedTypeList) {
		this.reservedTypeList = reservedTypeList;
	}

	public ICorBankSettingBiz getCorBankSettingBiz() {
		return corBankSettingBiz;
	}

	public void setCorBankSettingBiz(ICorBankSettingBiz corBankSettingBiz) {
		this.corBankSettingBiz = corBankSettingBiz;
	}
	
	
}

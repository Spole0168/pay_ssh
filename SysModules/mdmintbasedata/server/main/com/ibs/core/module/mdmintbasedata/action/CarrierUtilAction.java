package com.ibs.core.module.mdmintbasedata.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmintbasedata.biz.ICarrierBiz;
import com.ibs.core.module.mdmintbasedata.domain.CarrierSimple;
import com.ibs.portal.framework.server.action.CrudBaseAction;

/**
 * 提供给页面autoComplete 或者弹出窗口使用的Action
 * 
 * @author zdp
 * 
 */
public class CarrierUtilAction extends CrudBaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8679291741782881847L;
	private Map<String, Object> jsonResult = new HashMap<String, Object>(); 
    private ICarrierBiz carrierBiz; 
	private String orgCodeOrNameOrPinYin;
	private Integer records;
	private String searchVar;
	

	public String getOrgCodeOrNameOrPinYin() {
		return orgCodeOrNameOrPinYin;
	}

	public void setOrgCodeOrNameOrPinYin(String orgCodeOrNameOrPinYin) {
		this.orgCodeOrNameOrPinYin = orgCodeOrNameOrPinYin;
	}

	public Integer getRecords() {
		return records;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	public String getSearchVar() {
		return searchVar;
	}

	public void setSearchVar(String searchVar) {
		this.searchVar = searchVar;
	}
 
	public Map<String, Object> getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(Map<String, Object> jsonResult) {
		this.jsonResult = jsonResult;
	}

	@Override
	public String create() {
		return null;
	}

	@Override
	public String delete() {
		return null;
	}

	@Override
	public String export() {
		return null;
	}

	@Override
	public String list() {
		return null;
	}

	@Override
	public String modify() {
		return null;
	}

	@Override
	public String saveOrUpdate() {
		return null;
	}

	@Override
	public String search() {
		return null;
	}

	/**
	 * 提供autoComplete的接口
	 * 
	 * @return
	 */
	public String autoCompleteCarriers() {
		logger.trace("enter " + this.getClass().getSimpleName()
				+ " method autoCompleteCarriers"); 
	 
		if (null == records || records.intValue() <= 0) {
			records = new Integer(10); // 默认为10个每次
		} 

		if (null != searchVar && searchVar.length() > 0) {
			orgCodeOrNameOrPinYin = searchVar;
		} else {
			orgCodeOrNameOrPinYin = "";
		}
	 
		List<CarrierSimple> datas = carrierBiz.findUniversal(CarrierSimple.class,
					 orgCodeOrNameOrPinYin, records,Constants.STATUS_VALID);
			jsonResult.put("datas", datas);
		return AJAX_RETURN_TYPE;
		
	}

	public ICarrierBiz getCarrierBiz() {
		return carrierBiz;
	}

	public void setCarrierBiz(ICarrierBiz carrierBiz) {
		this.carrierBiz = carrierBiz;
	}

	 

 

	 

}

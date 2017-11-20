package com.ibs.core.module.mdmintbasedata.domain;

import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.domain.MdmBaseEntity;

/**
 * T_MDM_CARRIERS  快递承运商
 * @author Adair
 *
 */
@SuppressWarnings("serial")
public class Carrier extends MdmBaseEntity {
	/*
	 * 是否启用
	 */
	private char used;
	private String name;
	private String usedName;

	public char getUsed() {
		return used;
	}

	public void setUsed(char used) {
		this.used = used;
	}
	public String getName() {
		if(name!=null)
		{
			name=name.toUpperCase().trim();
			
		}
		return name;
	}

	public void setName(String codeparm) {
		if(codeparm!=null)
		{
			this.name=codeparm.toUpperCase().trim();
		}else
		{
			this.name = codeparm;
		}
		
	}

	public void setUsedName(String usedName) {
		this.usedName = usedName;
	}

	public String getUsedName() {
		if (getUsed()+"" == null) {
			usedName="";
		}
		
		if ((getUsed()+"").equals(Constants.YES_OR_NO_N)) {
			usedName = "禁用";
		}
		
		if ((getUsed()+"").equals(Constants.YES_OR_NO_Y)) {
			usedName = "启用";
		}
		return usedName;
	}
}

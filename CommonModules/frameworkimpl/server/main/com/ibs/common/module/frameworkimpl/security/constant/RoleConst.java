package com.ibs.common.module.frameworkimpl.security.constant; 
import java.util.ArrayList;
 

public class RoleConst {
 /**
 * 默认员工角色
 */
public static final ArrayList<String> defaultEmpRoleCode=new ArrayList<String>();

/**
 * 默认客户角色
 */
public static final ArrayList<String> defaultCustomerRoleCode=new ArrayList<String>();


/**
 * 默认航空货代角色
 */
public static final ArrayList<String> defaultFLTAgentRoleCode=new ArrayList<String>();

/**
 * 默认国际件货代角色
 */
public static final ArrayList<String> defaultIAgentRoleCode=new ArrayList<String>();

  static {
	  defaultEmpRoleCode.add("R_DEFAULT_EMPROLE");	 
	  defaultCustomerRoleCode.add("R_DEFAULT_CUSTOMER");
	  defaultFLTAgentRoleCode.add("R_DEFAULT_FLT_AGENT");
	  defaultIAgentRoleCode.add("R_DEFAULT_INT_AGENT");
	  
	}
	 

	
}

package com.ibs.common.module.frameworkimpl.security.domain;

import java.util.HashSet;
import java.util.Set;

import com.ibs.portal.framework.server.domain.BaseEntity;

/**
 * 角色组定义
 * 
 * @author huolang
 * 
 */
public class RoleGroup extends BaseEntity {

	private static final long serialVersionUID = -1410906498979025627L;
	private String id;
	private String name;
	private String description;
	private Set<Role> includedRoles = new HashSet<Role>(0);

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Role> getIncludedRoles() {
		return includedRoles;
	}

	public void setIncludedRoles(Set<Role> includedRoles) {
		this.includedRoles = includedRoles;
	}

}

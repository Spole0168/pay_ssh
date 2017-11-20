package com.ibs.portal.framework.server.domain;


public abstract class BaseEntity implements IEntity {
	public int compareTo(Object o) {
		if (null == o)
			return 1;
		if (!IEntity.class.isAssignableFrom(o.getClass())) {
			return 1;
		}
		IEntity target = (IEntity) o;
		if (null == this.getId())
			return 1;
		if (null == target.getId())
			return -1;
		return this.getId().toString().compareTo(target.getId().toString());

	}

	/**
	 * 实体ID
	 */
	protected String id;

	public BaseEntity() {

	}

	public BaseEntity(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BaseEntity other = (BaseEntity) obj;
		if (getId() != null && other.getId() != null)
			return getId().equals(other.getId());
		return false;
	}

	@Override
	public String toString() {
		return getClass().getName() + ":" + getId();
	}
}

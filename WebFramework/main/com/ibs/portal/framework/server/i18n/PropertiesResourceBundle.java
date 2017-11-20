package com.ibs.portal.framework.server.i18n;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

public class PropertiesResourceBundle extends ResourceBundle {

	private Properties properties;

    public PropertiesResourceBundle (Properties properties) {
    	this.properties = (properties == null ? new Properties() : properties);
    }

    public Object handleGetObject(String key) {
        if (key == null)
           return null;
        return properties.get(key);
    }

    @SuppressWarnings("unchecked")
	public Enumeration<String> getKeys() {
        ResourceBundle parent = this.parent;
        return new ResourceBundleEnumeration((Set)properties.keySet(), (parent != null) ? parent.getKeys() : null);
    }

	public void setParent(ResourceBundle parent) {
		super.setParent(parent);
	}

}

package com.ibs.portal.framework.server.context;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.config.StrutsXmlConfigurationProvider;

import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.ConfigurationException;
import com.opensymphony.xwork2.config.ConfigurationProvider;
import com.opensymphony.xwork2.inject.ContainerBuilder;
import com.opensymphony.xwork2.util.location.LocatableProperties;

public class ActionConfigProvider implements ConfigurationProvider{
	private List<ConfigurationProvider> providers = new ArrayList<ConfigurationProvider>();

	public ActionConfigProvider() {
		List<String> moduleConfigs = ApplicationContext.getContext().getApplication().getActionConfigs();
		for (String mc : moduleConfigs) {
			providers.add(new StrutsXmlConfigurationProvider(mc, true, ApplicationContext.getContext().getServletContext()));
		}
	}

	public void destroy() {
		for (ConfigurationProvider provider : providers)
			provider.destroy();
	}

	public void init(Configuration arg0) throws ConfigurationException {
		for (ConfigurationProvider provider : providers)
			provider.init(arg0);
	}

	public void loadPackages() throws ConfigurationException {
		for (ConfigurationProvider provider : providers)
			provider.loadPackages();
	}

	public boolean needsReload() {
		for (ConfigurationProvider provider : providers)
			if (provider.needsReload())
				return true;
		return false;
	}

	public void register(ContainerBuilder arg0, LocatableProperties arg1)
			throws ConfigurationException {
		for (ConfigurationProvider provider : providers)
			provider.register(arg0, arg1);
	}
}

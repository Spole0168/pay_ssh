package com.ibs.portal.framework.server.context;

import javax.servlet.ServletContext;

import org.apache.tiles.TilesApplicationContext;
import org.apache.tiles.factory.AbstractTilesContainerFactory;
import org.apache.tiles.servlet.wildcard.WildcardServletTilesApplicationContext;
import org.apache.tiles.startup.AbstractTilesInitializer;

public class TilesInitializer extends AbstractTilesInitializer {

	/**
	 * 很重要，创建匹配模式的ApplicaitonContext
	 */
	@Override
	protected TilesApplicationContext createTilesApplicationContext(
			TilesApplicationContext preliminaryContext) {
		return new WildcardServletTilesApplicationContext(
				(ServletContext) preliminaryContext.getContext());
	}

	@Override
	protected AbstractTilesContainerFactory createContainerFactory(
			TilesApplicationContext context) {
		return new TilesContainerFactory();
	}

}

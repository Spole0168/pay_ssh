package com.ibs.portal.framework.server.context;


import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.tiles.TilesApplicationContext;
import org.apache.tiles.context.TilesRequestContextFactory;
import org.apache.tiles.definition.DefinitionsFactoryException;
import org.apache.tiles.factory.BasicTilesContainerFactory;
import org.apache.tiles.util.URLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TilesContainerFactory extends BasicTilesContainerFactory {

	private static Logger logger = LoggerFactory.getLogger(TilesContainerFactory.class);

	@Override
	protected List<URL> getSourceURLs(
			TilesApplicationContext applicationContext,
			TilesRequestContextFactory contextFactory) {
		try {
			List<String> tilesConfigs = ApplicationContext.getContext()
					.getApplication().getTilesConfigs();

			Set<URL> urlSet = new HashSet<URL>();

			// 把主要的给加进去
			URL mainUrl = applicationContext.getResource("/WEB-INF/tiles*.xml");
			if (mainUrl != null) {
				urlSet.add(mainUrl);
			}

			for (Iterator<String> i = tilesConfigs.iterator(); i.hasNext();) {
				String path = i.next();
				logger.debug("Tiles配置文件的路径是:" + path);
				path.replace('.', '/');
				URL url = applicationContext.getResource("classpath*:" + path);
				if (url != null) {
					urlSet.add(url);
					logger.debug("加入Tiles url:" + url);
				}
			}

			return URLUtil.getBaseTilesDefinitionURLs(urlSet);
		} catch (IOException e) {
			throw new DefinitionsFactoryException(
					"Cannot load definition URLs", e);
		}
	}


}

package com.ibs.portal.framework.server.context;

import org.apache.tiles.web.startup.AbstractTilesListener;

public class TilesListener extends AbstractTilesListener {

	@Override
	protected TilesInitializer createTilesInitializer() {
		return new TilesInitializer();
	}

}

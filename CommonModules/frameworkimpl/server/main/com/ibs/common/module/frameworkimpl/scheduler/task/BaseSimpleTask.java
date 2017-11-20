package com.ibs.common.module.frameworkimpl.scheduler.task;

public abstract class BaseSimpleTask {
	
	private boolean interrupt = false;

	public boolean isInterrupt() {
		return interrupt;
	}

	public void setInterrupt(boolean interrupt) {
		this.interrupt = interrupt;
	}

}

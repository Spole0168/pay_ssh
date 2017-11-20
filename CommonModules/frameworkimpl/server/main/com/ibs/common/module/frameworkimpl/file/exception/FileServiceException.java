package com.ibs.common.module.frameworkimpl.file.exception;


/**
 * @author 
 * $Id: FileServiceException.java 23528 2010-12-27 02:04:36Z  $
 */
public class FileServiceException extends Exception {

	private static final long serialVersionUID = 8652288727147056183L;

	public FileServiceException(FileServiceMessage fileService) {
		super(fileService.toString());
	}

}

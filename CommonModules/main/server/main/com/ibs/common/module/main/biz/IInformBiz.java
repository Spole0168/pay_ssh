package com.ibs.common.module.main.biz;



import java.util.List;

import com.ibs.common.module.main.domain.MainInform;
import com.ibs.common.module.main.domain.MessageInform;


/**
 * 提醒BIZ接口
 * @author 
 * $Id: IInformBiz.java 20049 2010-12-07 08:05:57Z  $
 */
public interface IInformBiz {
	
	public List<MainInform> findValidMainInform();

	public List<MessageInform> findValidMessageInform();
}

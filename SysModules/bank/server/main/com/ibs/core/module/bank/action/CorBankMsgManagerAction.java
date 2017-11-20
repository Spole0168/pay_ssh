/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.bank.action;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.xerces.parsers.XMLDocumentParser;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.ibs.core.module.bank.biz.ICorBankMsgBiz;
import com.ibs.core.module.bank.domain.CorBankMsg;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.DateUtils;
import com.ibs.portal.framework.util.StringUtils;


/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_MSG
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CorBankMsgManagerAction extends CrudBaseAction {

	private ICorBankMsgBiz corBankMsgBiz;

	private String id;
	private CorBankMsg corBankMsg;

	/**
	 * 根据报文编码和 支付渠道编码 查看报文信息
	 * 
	 * @return
	 */
	public String searchByMsgCode()  throws Exception {
		logger.info("entering action::CnlMsgManagerAction.searchByMsgCode()...");
		String msgCode = request.getParameter("msgCode");
		logger.info("entering searchByMsgCode()::银行报文编码msgCode = " + msgCode);

		if (null != msgCode) {
			String text = corBankMsgBiz.findByMsgCode(msgCode);

			if (StringUtils.isNotEmpty(text)) {
				logger.debug("file Content:" + text);
				SAXReader reader = new SAXReader(); // SAX解析器reader
				try {
					Document document = reader.read(new StringReader(text)); // 使用SAX将指定的数据流读入到document中
					
					String responseXML = null;
					XMLWriter writer = null;
					if (document != null) {
						try {
							StringWriter stringWriter = new StringWriter();
							OutputFormat format = new OutputFormat("    ", true); // OutputFormat是用来规范要输出的Xml的格式
							writer = new XMLWriter(stringWriter, format);  // XMLWriter将普通流转换成Xml标准流
							writer.write(document); // 将Document输出到指定的输出流
							writer.flush();
							responseXML = stringWriter.getBuffer().toString();
							logger.debug("xml报文字符串 转换为 格式化的xml字符串 成功...responseXML：："+responseXML);
						} finally {
							if (writer != null) {
								try {
									writer.close();
								} catch (IOException e) {
								}
							}
						}
					}
					text = responseXML;
					text = "<xmp>" + text + "</xmp>";
				} catch (DocumentException e1) {
					logger.debug("xml报文字符串 转换为 格式化的xml字符串 失败...：："+e1);
					e1.printStackTrace();
				}
				try {
					response.setContentType("text/xml;charset=UTF-8");
					response.getWriter().println(text);
					logger.debug("ajax read msg end...");
				} catch (IOException e) {
					logger.debug("ajax read msg failed..." + e);
					e.printStackTrace();
				}
			} else {
				logger.debug("text is empty::" + text);
				try {
					response.setContentType("text/html;charset=UTF-8");
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().println("<font color='red' size='4'>无法查看报文！</font>");
				} catch (IOException e) {
					logger.debug("text is empty::ajax read msg failed..." + e);
					e.printStackTrace();
				}
			}
		}
		return AJAX_RETURN_TYPE;
	}

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CorBankMsgManagerAction.list()...");
		return SUCCESS;
	}

	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CorBankMsgManagerAction.create()...");
		return SUCCESS;
	}

	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CorBankMsgManagerAction.modify()...");
		corBankMsg = corBankMsgBiz.getCorBankMsgById(id);
		return SUCCESS;
	}

	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CorBankMsgManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<CorBankMsg> result = (Page<CorBankMsg>) corBankMsgBiz.findCorBankMsgByPage(queryPage);
		setResult(result);

		/**
		 * 第三步，返回
		 */
		return AJAX_RETURN_TYPE;
	}

	/**
	 * 保存
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdate() {
		logger.info("entering action::CorBankMsgManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			corBankMsgBiz.saveCorBankMsg(corBankMsg);
		}
		// 如果是修改
		else {
			corBankMsgBiz.updateCorBankMsg(corBankMsg);
		}

		return SUCCESS;
	}

	/**
	 * Action方法，批量删除数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() {
		logger.info("entering action::CorBankMsgManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}

	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CorBankMsgManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		corBankMsgBiz.exportCorBankMsg(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CorBankMsgManagerAction.setQueryCondition()...");

		// ID
		String id = getSearchFields().get("id");
		if (StringUtils.isNotEmpty(id)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("id", "%" + id + "%");
				queryPage.addLikeSearch("id", id);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("id", "%" + id + "%");
				exportSetting.addLikeSearch("id", id);
			}
		}

		// MSG_CODE
		String msgCode = getSearchFields().get("msgCode");
		if (StringUtils.isNotEmpty(msgCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("msgCode", "%" + msgCode + "%");
				queryPage.addLikeSearch("msgCode", msgCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("msgCode", "%" + msgCode + "%");
				exportSetting.addLikeSearch("msgCode", msgCode);
			}
		}

		// MSG_LOCATION
		String msgLocation = getSearchFields().get("msgLocation");
		if (StringUtils.isNotEmpty(msgLocation)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("msgLocation", "%" + msgLocation + "%");
				queryPage.addLikeSearch("msgLocation", msgLocation);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("msgLocation", "%" + msgLocation + "%");
				exportSetting.addLikeSearch("msgLocation", msgLocation);
			}
		}

		// RECIEVE_TIME
		String recieveTime = getSearchFields().get("recieveTime");
		if (StringUtils.isNotEmpty(recieveTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("recieveTime", DateUtils.convert(recieveTime, DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("recieveTime", DateUtils.convert(recieveTime, DateUtils.DATE_FORMAT));
			}
		}

		// BANK_CODE
		String bankCode = getSearchFields().get("bankCode");
		if (StringUtils.isNotEmpty(bankCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCode", "%" + bankCode + "%");
				queryPage.addLikeSearch("bankCode", bankCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCode", "%" + bankCode + "%");
				exportSetting.addLikeSearch("bankCode", bankCode);
			}
		}

		// PMT_CNL_CODE
		String pmtCnlCode = getSearchFields().get("pmtCnlCode");
		if (StringUtils.isNotEmpty(pmtCnlCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("pmtCnlCode", "%" + pmtCnlCode + "%");
				queryPage.addLikeSearch("pmtCnlCode", pmtCnlCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("pmtCnlCode", "%" + pmtCnlCode + "%");
				exportSetting.addLikeSearch("pmtCnlCode", pmtCnlCode);
			}
		}

		// IS_VALID
		String isValid = getSearchFields().get("isValid");
		if (StringUtils.isNotEmpty(isValid)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("isValid", "%" + isValid + "%");
				queryPage.addLikeSearch("isValid", isValid);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("isValid", "%" + isValid + "%");
				exportSetting.addLikeSearch("isValid", isValid);
			}
		}

		// CREATOR
		String creator = getSearchFields().get("creator");
		if (StringUtils.isNotEmpty(creator)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("creator", "%" + creator + "%");
				queryPage.addLikeSearch("creator", creator);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("creator", "%" + creator + "%");
				exportSetting.addLikeSearch("creator", creator);
			}
		}

		// UPDATOR
		String updator = getSearchFields().get("updator");
		if (StringUtils.isNotEmpty(updator)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("updator", "%" + updator + "%");
				queryPage.addLikeSearch("updator", updator);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("updator", "%" + updator + "%");
				exportSetting.addLikeSearch("updator", updator);
			}
		}

		// CREATE_TIME
		String createTime = getSearchFields().get("createTime");
		if (StringUtils.isNotEmpty(createTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("createTime", DateUtils.convert(createTime, DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("createTime", DateUtils.convert(createTime, DateUtils.DATE_FORMAT));
			}
		}

		// UPDATE_TIME
		String updateTime = getSearchFields().get("updateTime");
		if (StringUtils.isNotEmpty(updateTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("updateTime", DateUtils.convert(updateTime, DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("updateTime", DateUtils.convert(updateTime, DateUtils.DATE_FORMAT));
			}
		}

		// DIRECTION
		String direction = getSearchFields().get("direction");
		if (StringUtils.isNotEmpty(direction)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("direction", "%" + direction + "%");
				queryPage.addLikeSearch("direction", direction);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("direction", "%" + direction + "%");
				exportSetting.addLikeSearch("direction", direction);
			}
		}

	}

	public ICorBankMsgBiz getCorBankMsgBiz() {
		logger.info("entering action::CorBankMsgManagerAction.getCorBankMsgBiz()...");
		return corBankMsgBiz;
	}

	public void setCorBankMsgBiz(ICorBankMsgBiz corBankMsgBiz) {
		logger.info("entering action::CorBankMsgManagerAction.setCorBankMsgBiz()...");
		this.corBankMsgBiz = corBankMsgBiz;
	}

	public CorBankMsg getCorBankMsg() {
		logger.info("entering action::CorBankMsgManagerAction.getCorBankMsg()...");
		return corBankMsg;
	}

	public void setCorBankMsg(CorBankMsg corBankMsg) {
		logger.info("entering action::CorBankMsgManagerAction.setCorBankMsg()...");
		this.corBankMsg = corBankMsg;
	}

	public String getId() {
		logger.info("entering action::CorBankMsgManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CorBankMsgManagerAction.setId()...");
		this.id = id;
	}

}

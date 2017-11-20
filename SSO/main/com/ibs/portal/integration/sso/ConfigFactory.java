package com.ibs.portal.integration.sso;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 配置工厂
 *
 * @author luoyue
 *
 */
public class ConfigFactory {

	/**
	 * 服务器间验证令牌传递信息头KEY
	 */
	public static final String TOKEN_HEADER_KEY = "TOKEN";

	/**
	 * 用户请求URL附带令牌参数KEY
	 */
	public static final String TOKEN_PARAMETER_KEY = "TOKEN";

	/**
	 * 用户请求URL附带令牌参数KEY
	 */
	public static final String TOKEN_URL_SUFFIX = ".sso";

	private static IUserIdManager userIdManager;

	/**
	 * 获取用户ID管理策略接口
	 *
	 * @return 用户ID管理策略接口
	 */
	public static IUserIdManager getUserIdManager() {
		return userIdManager;
	}

	/**
	 * 获取用户ID管理策略接口
	 *
	 * @return 用户ID管理策略接口
	 */
	public static void setUserId(HttpSession session, Token token) {
		((IUserIdManager)userIdManager).setUserId(session, resolveUserId(token));
	}

	public static String resolveUserId(Token token) {
		String userId = token.getUserId();
		if (userIdManager instanceof TokenUserIdManager) {
			userId = ((TokenUserIdManager)userIdManager).resolveUserId(token);
		}
		return userId;
	}

	private static String currentApplicationId;

	/**
	 * 通过当前应用系统配置
	 *
	 * @return 当前应用系统配置
	 */
	public static String getCurrentApplicationId() {
		return currentApplicationId;
	}

	private static final Map applications = new HashMap();

	/**
	 * 通过应用系统ID获取受信的应用系统配置
	 *
	 * @param applicationId
	 *            应用系统ID
	 * @return 受信的应用系统配置
	 */
	public static Application getApplication(String applicationId) {
		return (Application)applications.get(applicationId);
	}

	/**
	 * 获取所有应用系统定义
	 *
	 * @return 所有应用系统定义
	 */
	public static Map getAllApplications() {
		return applications;
	}

	private static boolean isInited = false;

	public static void init(ServletContext servletContext) throws ConfigException {
		if (isInited)
			return;
		else
			isInited = true;
		try {
			System.out.println("[sso] loading /WEB-INF/sso.xml");
			InputStream config = servletContext.getResourceAsStream("/WEB-INF/sso.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(config);
			Element root = document.getDocumentElement();
			NodeList applicationNodes = root.getElementsByTagName("application");
			if (applicationNodes == null || applicationNodes.getLength() == 0)
				throw new ConfigException("sso.xml config error! application not exists! please config application element, eg: <application id=\"...\" vaildateURL=\"...\" />");
			for (int i = 0, n = applicationNodes.getLength(); i < n; i++) {
				Element applicationNode = (Element) applicationNodes.item(i);
				String id = applicationNode.getAttribute("id");
				if (id == null || id.length() == 0)
					throw new ConfigException("sso.xml config error! application id undefined! please config application id, eg: <application id=\"...\" vaildateURL=\"...\" />");
				System.out.println("[sso] register application: " + id);
				String vaildateURL = applicationNode.getAttribute("vaildateURL");
				if (vaildateURL == null || vaildateURL.length() == 0)
					throw new ConfigException("sso.xml config error! application vaildateURL undefined! please config application vaildateURL, eg: <application id=\"...\" vaildateURL=\"...\" />");
				System.out.println("[sso] vaildate URL: " + vaildateURL);
				ConfigFactory.applications.put(id, new Application(id, vaildateURL));
			}
			currentApplicationId = root.getAttribute("currentApplication");
			if (currentApplicationId == null)
				throw new ConfigException("sso.xml config error! please config attribute \"currentApplication\" of root element, eg: <sso currentApplication=\"xxx\" userIdManager=\"...\"></sso>");
			System.out.println("[sso] current application: " + currentApplicationId);
			String userIdManagerName = root.getAttribute("userIdManager");
			System.out.println("[sso] UserIdManager class name: " + userIdManagerName);
			userIdManager = (IUserIdManager)Class.forName(userIdManagerName).newInstance();
			if (! (userIdManager instanceof IUserIdManager))
				throw new ConfigException("sso.xml config error! " + userIdManagerName+ " unimplements " + TokenUserIdManager.class.getName() + " or " + IUserIdManager.class.getName());
		} catch (ConfigException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConfigException("sso.xml config error!", e);
		}
	}

}
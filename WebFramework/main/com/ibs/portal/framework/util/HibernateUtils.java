package com.ibs.portal.framework.util;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.Hibernate;

import com.ibs.portal.framework.server.metadata.ColumnType;


public class HibernateUtils {
	
	private static final String SQL_SELECT_MESSAGE = "sql.append(\"t.{0} as {1}, \");\n";
//	private static final String SQL_SCALAR_MESSAGE = "queryPage.addScalar(\"{0}\", {1});\n";
	private static final String SQL_SCALAR_MESSAGE = "scallarList.add(new ColumnType(\"{0}\", {1}));\n";
	private static final String SQL_UPDATE_MESSAGE = "sql.append(\"t.{0} = :{1}, \");\n";
	private static final String SQL_ARGUMENTS_MESSAGE = "args.put(\"{0}\", o.get{1}());\n";
	
//	private static final String JDBC_MESSAGE = "\"{0},\" + \n";
	private static final String JDBC_SELECT_MESSAGE = "sql.append(\"{0},\");\n";
	private static final String JDBC_BIND_MESSAGE = "ps.set{0}(++i , o.get{1}());\t// {2}\n";
	private static final String JDBC_BIND_PRIMITIVE_MESSAGE = "if (o.get{1}() == null)\t// {2}\n\tps.setNull(++i , java.sql.Types.{3});\nelse\n\tps.set{0}(++i , o.get{1}());\n";
	private static final String JDBC_BIND_TIMESTAMP_MESSAGE = "if (o.get{1}() == null)\t// {2}\n\tps.setNull(++i , java.sql.Types.{3});\nelse\n\tps.set{0}(++i , new Timestamp(o.get{1}().getTime()));\n";
	private static final String JDBC_ROW_MAPPER = "item.set{0}(rs.get{1}(\"{2}\"));\n";
	
	@SuppressWarnings("unchecked")
	public void printSql(Document document, String tableName) {

		List parent = document.selectNodes("//class[attribute::table='" + tableName + "']");
		String domainName = ((Element)parent.get(0)).attribute("name").getValue();
		
		List nodes = document.selectNodes("//class[attribute::table='" + tableName + "']/*");
		
		Iterator iter = nodes.iterator();
		
		StringBuffer sb11 = new StringBuffer();
		StringBuffer sb12 = new StringBuffer();
		StringBuffer sb21 = new StringBuffer();
		StringBuffer sb22 = new StringBuffer();

		while (iter.hasNext()) {
			Element element = (Element) iter.next();
			
			String propName = element.attributeValue("name");
			String propType = element.attributeValue("type");
			String colName = element.attributeValue("column");
			if (StringUtils.isEmpty(colName)) {
				Element node = (Element) element.selectSingleNode("column");
				if (node == null)
					continue;
				colName = node.attributeValue("name");
			}

//			sb11.append(PREFIX_APPEND).append(PREFIX_TABLE_ALIAS).append(colName).append(" as ")
//				.append(propName).append(", ").append(SUFFIX_APPEND).append("\n");
			
			sb11.append(MessageFormat.format(SQL_SELECT_MESSAGE, colName, propName));
			sb21.append(MessageFormat.format(SQL_UPDATE_MESSAGE, colName, propName));
			sb22.append(MessageFormat.format(SQL_ARGUMENTS_MESSAGE, propName, StringUtils.capitalize(propName)));
			
			if (propType.indexOf("String") >= 0 || propType.equals("string")) {
				sb12.append(MessageFormat.format(SQL_SCALAR_MESSAGE, propName, "Hibernate.STRING"));
			} else if (propType.indexOf("Char") >= 0 || propType.equals("char")) {
				sb12.append(MessageFormat.format(SQL_SCALAR_MESSAGE, propName, "Hibernate.CHARACTER"));
			} else if (propType.indexOf("Integer") >= 0 || propType.equals("int")) {
				sb12.append(MessageFormat.format(SQL_SCALAR_MESSAGE, propName, "Hibernate.INTEGER"));
			} else if (propType.indexOf("Long") >= 0 || propType.equals("long")) {
				sb12.append(MessageFormat.format(SQL_SCALAR_MESSAGE, propName, "Hibernate.LONG"));
			} else if (propType.indexOf("Short") >= 0 || propType.equals("short")) {
				sb12.append(MessageFormat.format(SQL_SCALAR_MESSAGE, propName, "Hibernate.SHORT"));
			} else if (propType.indexOf("Boolean") >= 0 || propType.equals("boolean")) {
				sb12.append(MessageFormat.format(SQL_SCALAR_MESSAGE, propName, "Hibernate.BOOLEAN"));
			} else if (propType.indexOf("Double") >= 0 || propType.equals("double")) {
				sb12.append(MessageFormat.format(SQL_SCALAR_MESSAGE, propName, "Hibernate.DOUBLE"));
			} else if (propType.indexOf("BigDecimal") >= 0 || propType.equals("big_decimal")) {
				sb12.append(MessageFormat.format(SQL_SCALAR_MESSAGE, propName, "Hibernate.BIG_DECIMAL"));
			} else if (propType.indexOf("Date") >= 0 || propType.indexOf("Timestamp") >= 0
					|| propType.equals("timestamp")) {
				sb12.append(MessageFormat.format(SQL_SCALAR_MESSAGE, propName, "Hibernate.TIMESTAMP"));
			} else {
				System.err.println(propName + " ERROR...");
			}
			
			//System.out.println(propName + "\t" + propType + "\t" + colName);
		}
		
		System.out.println("-------------------- HIBERNATE SQL SELECT --------------------\n");
		System.out.println("StringBuffer sql = new StringBuffer();");
		System.out.println("sql.append(\"SELECT \");\n");
		System.out.println(sb11.toString());
		System.out.println("sql.append(\"FROM YTSTL." + tableName + "\");\n");
		System.out.println("List<ColumnType>  scallarList = new  ArrayList<ColumnType>();");
		System.out.println(sb12.toString());
		System.out.println("queryPage.setScalarList(scallarList);");
		System.out.println("queryPage.setSqlString(sql.toString());");
		System.out.println("\nreturn findPageBySql(queryPage, " + domainName + ".class);\n");
		
		System.out.println("-------------------- HIBERNATE SQL UPDATE --------------------\n");
		System.out.println("StringBuffer sql = new StringBuffer();");
		System.out.println("sql.append(\"UPDATE YTSTL." + tableName + " t SET \");\n");
		System.out.println(sb21.toString());
		System.out.println("Map<String,Object> args = new HashMap<String, Object>();");
		System.out.println(sb22.toString());
		System.out.println("super.executeSql(sql.toString(), args, null);");
	}
	
	@SuppressWarnings("unchecked")
	public void printJdbc(Document document, String tableName) {

		List nodes = document.selectNodes("//class[attribute::table='" + tableName + "']/*");
		
		Iterator iter = nodes.iterator();
		
		StringBuffer sb11 = new StringBuffer();
		StringBuffer sb12 = new StringBuffer();
		StringBuffer sb21 = new StringBuffer();
		StringBuffer sb22 = new StringBuffer();

		int i = 1;
		
		while (iter.hasNext()) {
			Element element = (Element) iter.next();
			
			String propName = element.attributeValue("name");
			String propType = element.attributeValue("type");
			String colName = element.attributeValue("column");
			if (StringUtils.isEmpty(colName)) {
				Element node = (Element) element.selectSingleNode("column");
				if (node == null)
					continue;
				colName = node.attributeValue("name");
			}

//			sb11.append(PREFIX_APPEND).append(PREFIX_TABLE_ALIAS).append(colName).append(" as ")
//				.append(propName).append(", ").append(SUFFIX_APPEND).append("\n");
			
//			sb11.append(MessageFormat.format(JDBC_MESSAGE, colName, propName));
			sb12.append(MessageFormat.format(JDBC_SELECT_MESSAGE, colName, propName));
			
			if (propType.indexOf("String") >= 0 || propType.equals("string")
					|| propType.indexOf("Char") >= 0 || propType.equals("char")) {
				sb21.append(MessageFormat.format(JDBC_BIND_MESSAGE, "String", StringUtils.capitalize(propName), colName));
				sb22.append(MessageFormat.format(JDBC_ROW_MAPPER, StringUtils.capitalize(propName), "String", colName));
			} else if (propType.indexOf("Integer") >= 0 || propType.equals("int")) {
				sb21.append(MessageFormat.format(JDBC_BIND_PRIMITIVE_MESSAGE, "Int", StringUtils.capitalize(propName), colName, "DECIMAL"));
				sb22.append(MessageFormat.format(JDBC_ROW_MAPPER, StringUtils.capitalize(propName), "Int", colName));
			} else if (propType.indexOf("Long") >= 0 || propType.equals("long")) {
				sb21.append(MessageFormat.format(JDBC_BIND_PRIMITIVE_MESSAGE, "Long", StringUtils.capitalize(propName), colName, "DECIMAL"));
				sb22.append(MessageFormat.format(JDBC_ROW_MAPPER, StringUtils.capitalize(propName), "Long", colName));
			} else if (propType.indexOf("Short") >= 0 || propType.equals("short")) {
				sb21.append(MessageFormat.format(JDBC_BIND_PRIMITIVE_MESSAGE, "Short", StringUtils.capitalize(propName), colName, "DECIMAL"));
				sb22.append(MessageFormat.format(JDBC_ROW_MAPPER, StringUtils.capitalize(propName), "Short", colName));
			} else if (propType.indexOf("Boolean") >= 0 || propType.equals("boolean")) {
				sb21.append(MessageFormat.format(JDBC_BIND_PRIMITIVE_MESSAGE, "Boolean", StringUtils.capitalize(propName), colName, "DECIMAL"));
				sb22.append(MessageFormat.format(JDBC_ROW_MAPPER, StringUtils.capitalize(propName), "Boolean", colName));
			} else if (propType.indexOf("Double") >= 0 || propType.equals("double")) {
				sb21.append(MessageFormat.format(JDBC_BIND_PRIMITIVE_MESSAGE, "Double", StringUtils.capitalize(propName), colName, "DECIMAL"));
				sb22.append(MessageFormat.format(JDBC_ROW_MAPPER, StringUtils.capitalize(propName), "Double", colName));
			} else if (propType.indexOf("BigDecimal") >= 0 || propType.equals("big_decimal")) {
				sb21.append(MessageFormat.format(JDBC_BIND_MESSAGE, "BigDecimal", StringUtils.capitalize(propName), colName));
				sb22.append(MessageFormat.format(JDBC_ROW_MAPPER, StringUtils.capitalize(propName), "BigDecimal", colName));
			} else if (propType.indexOf("Timestamp") >= 0 || propType.equals("timestamp")
					|| propType.indexOf("Date") >= 0 || propType.equals("date")) {
				sb21.append(MessageFormat.format(JDBC_BIND_TIMESTAMP_MESSAGE, "Timestamp", StringUtils.capitalize(propName), colName, "TIMESTAMP"));
				sb22.append(MessageFormat.format(JDBC_ROW_MAPPER, StringUtils.capitalize(propName), "Timestamp", colName));
			}/* else if (propType.indexOf("Date") >= 0 || propType.equals("date")) {
				sb21.append(MessageFormat.format(JDBC_BIND_MESSAGE, "Date", StringUtils.capitalize(propName), colName));
				sb22.append(MessageFormat.format(JDBC_ROW_MAPPER, StringUtils.capitalize(propName), "Date", colName));
			}*/ else {
				System.err.println(propName + " ERROR...");
			}
			
			++i;
		}
		
		System.out.println("\n-------------------- JDBC SELECT AND UPDATE --------------------\n");
//		System.out.println(sb11.toString());
		System.out.println(sb12.toString());
		System.out.println(sb21.toString());
		System.out.println("-------------------- JDBC ROW MAPPER --------------------");
		System.out.println(sb22.toString());
	}

}

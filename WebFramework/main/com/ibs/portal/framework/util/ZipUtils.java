package com.ibs.portal.framework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ZipUtils {
	
	private static final Log logger = LogFactory.getLog(ZipUtils.class);
	
	// 压缩
	public static String compress(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			GZIPOutputStream gzip;
			gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes());
			gzip.close();
			return out.toString("ISO-8859-1");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return null;
	}

	// 解压缩
	public static String uncompress(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ByteArrayInputStream in = new ByteArrayInputStream(
					str.getBytes("ISO-8859-1"));
			GZIPInputStream gunzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = gunzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
			// toString()使用平台默认编码，也可以显式的指定如toString("GBK")
			return out.toString();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return null;
	}


	// 测试方法
	public static void main(String[] args) throws IOException {
		
		long time1 = System.nanoTime()/1000000;
		System.out.println(ZipUtils.compress("{\"acceptCanvassEmpCode\":\"\",\"acceptCanvassEmpName\":\"\",\"acceptFailureCode\":\"\",\"acceptMode\":0,\"acceptOrgCode\":\"\",\"acceptOrgName\":\"\",\"acceptTime\":null,\"acceptUserCode\":\"\",\"acceptUserName\":\"\",\"assignOrgCode\":\"\",\"assignOrgName\":\"\",\"canvassEmpCode\":\"\",\"canvassEmpName\":\"\",\"canvassFailureCode\":\"\",\"canvassMode\":0,\"canvassOrgCode\":\"\",\"canvassOrgName\":\"\",\"canvassTime\":null,\"canvassUserCode\":\"\",\"canvassUserName\":\"\",\"codFee\":0,\"creatorId\":\"\",\"deliveryEmpCode\":\"\",\"deliveryEmpName\":\"\",\"deliveryOrgCode\":\"\",\"deliveryOrgName\":\"\",\"endCanvassTime\":null,\"goodsTotalFee\":0,\"goodsTotalQty\":0,\"goodsTotalWeight\":0,\"goodsType\":0,\"id\":\"\",\"informType\":0,\"isNewOrder\":false,\"isPackage\":false,\"mergeOrderCode\":\"\",\"offerFee\":0,\"orderCancelTime\":null,\"orderChannelCode\":\"\",\"orderCreateTime\":null,\"orderFlag\":0,\"orderLogisticsCode\":\"SP110701000002139\",\"orderNo\":\"\",\"orderStatus\":0,\"orderTypeCode\":\"\",\"recipientAddress\":\"湖南省长沙市雨花区芙蓉中路二段223号二办公楼203\",\"recipientCityId\":\"\",\"recipientCityName\":\"\",\"recipientCountyId\":\"\",\"recipientCountyName\":\"\",\"recipientEncryptionCode\":\"\",\"recipientId\":\"\",\"recipientMobile\":\"\",\"recipientName\":\"肖威娜\",\"recipientPhone\":\"\",\"recipientPostalCode\":0,\"recipientProvId\":\"\",\"recipientProvName\":\"\",\"remark\":\"【喜多】浴盆大-桔\",\"senderAddress\":\"上海上海市青浦区华新镇北青公路3890弄500号东一库 (民兴工业区)\",\"senderCityId\":\"\",\"senderCityName\":\"\",\"senderCountyId\":\"\",\"senderCountyName\":\"\",\"senderEncryptionCode\":\"\",\"senderId\":\"\",\"senderMobile\":\"4001168100/\",\"senderName\":\"耀点100\",\"senderPhone\":\"\",\"senderPostalCode\":200000,\"senderProvId\":\"\",\"senderProvName\":\"\",\"signoffFailureCode\":\"\",\"signoffTime\":null,\"specialGoodsType\":0,\"startCanvassTime\":null,\"tradeNo\":\"\",\"waybillNo\":\"2385831811\",\"waybillStatus\":0}").length());
		System.out.println(ZipUtils.uncompress(ZipUtils.compress("{\"acceptCanvassEmpCode\":\"\",\"acceptCanvassEmpName\":\"\",\"acceptFailureCode\":\"\",\"acceptMode\":0,\"acceptOrgCode\":\"\",\"acceptOrgName\":\"\",\"acceptTime\":null,\"acceptUserCode\":\"\",\"acceptUserName\":\"\",\"assignOrgCode\":\"\",\"assignOrgName\":\"\",\"canvassEmpCode\":\"\",\"canvassEmpName\":\"\",\"canvassFailureCode\":\"\",\"canvassMode\":0,\"canvassOrgCode\":\"\",\"canvassOrgName\":\"\",\"canvassTime\":null,\"canvassUserCode\":\"\",\"canvassUserName\":\"\",\"codFee\":0,\"creatorId\":\"\",\"deliveryEmpCode\":\"\",\"deliveryEmpName\":\"\",\"deliveryOrgCode\":\"\",\"deliveryOrgName\":\"\",\"endCanvassTime\":null,\"goodsTotalFee\":0,\"goodsTotalQty\":0,\"goodsTotalWeight\":0,\"goodsType\":0,\"id\":\"\",\"informType\":0,\"isNewOrder\":false,\"isPackage\":false,\"mergeOrderCode\":\"\",\"offerFee\":0,\"orderCancelTime\":null,\"orderChannelCode\":\"\",\"orderCreateTime\":null,\"orderFlag\":0,\"orderLogisticsCode\":\"SP110701000002139\",\"orderNo\":\"\",\"orderStatus\":0,\"orderTypeCode\":\"\",\"recipientAddress\":\"湖南省长沙市雨花区芙蓉中路二段223号二办公楼203\",\"recipientCityId\":\"\",\"recipientCityName\":\"\",\"recipientCountyId\":\"\",\"recipientCountyName\":\"\",\"recipientEncryptionCode\":\"\",\"recipientId\":\"\",\"recipientMobile\":\"\",\"recipientName\":\"肖威娜\",\"recipientPhone\":\"\",\"recipientPostalCode\":0,\"recipientProvId\":\"\",\"recipientProvName\":\"\",\"remark\":\"【喜多】浴盆大-桔\",\"senderAddress\":\"上海上海市青浦区华新镇北青公路3890弄500号东一库 (民兴工业区)\",\"senderCityId\":\"\",\"senderCityName\":\"\",\"senderCountyId\":\"\",\"senderCountyName\":\"\",\"senderEncryptionCode\":\"\",\"senderId\":\"\",\"senderMobile\":\"4001168100/\",\"senderName\":\"耀点100\",\"senderPhone\":\"\",\"senderPostalCode\":200000,\"senderProvId\":\"\",\"senderProvName\":\"\",\"signoffFailureCode\":\"\",\"signoffTime\":null,\"specialGoodsType\":0,\"startCanvassTime\":null,\"tradeNo\":\"\",\"waybillNo\":\"2385831811\",\"waybillStatus\":0}")).length());
		long time2 = System.nanoTime()/1000000;
//		System.out.println(ZipUtils.zipString("{\"acceptCanvassEmpCode\":\"\",\"acceptCanvassEmpName\":\"\",\"acceptFailureCode\":\"\",\"acceptMode\":0,\"acceptOrgCode\":\"\",\"acceptOrgName\":\"\",\"acceptTime\":null,\"acceptUserCode\":\"\",\"acceptUserName\":\"\",\"assignOrgCode\":\"\",\"assignOrgName\":\"\",\"canvassEmpCode\":\"\",\"canvassEmpName\":\"\",\"canvassFailureCode\":\"\",\"canvassMode\":0,\"canvassOrgCode\":\"\",\"canvassOrgName\":\"\",\"canvassTime\":null,\"canvassUserCode\":\"\",\"canvassUserName\":\"\",\"codFee\":0,\"creatorId\":\"\",\"deliveryEmpCode\":\"\",\"deliveryEmpName\":\"\",\"deliveryOrgCode\":\"\",\"deliveryOrgName\":\"\",\"endCanvassTime\":null,\"goodsTotalFee\":0,\"goodsTotalQty\":0,\"goodsTotalWeight\":0,\"goodsType\":0,\"id\":\"\",\"informType\":0,\"isNewOrder\":false,\"isPackage\":false,\"mergeOrderCode\":\"\",\"offerFee\":0,\"orderCancelTime\":null,\"orderChannelCode\":\"\",\"orderCreateTime\":null,\"orderFlag\":0,\"orderLogisticsCode\":\"SP110701000002139\",\"orderNo\":\"\",\"orderStatus\":0,\"orderTypeCode\":\"\",\"recipientAddress\":\"湖南省长沙市雨花区芙蓉中路二段223号二办公楼203\",\"recipientCityId\":\"\",\"recipientCityName\":\"\",\"recipientCountyId\":\"\",\"recipientCountyName\":\"\",\"recipientEncryptionCode\":\"\",\"recipientId\":\"\",\"recipientMobile\":\"\",\"recipientName\":\"肖威娜\",\"recipientPhone\":\"\",\"recipientPostalCode\":0,\"recipientProvId\":\"\",\"recipientProvName\":\"\",\"remark\":\"【喜多】浴盆大-桔\",\"senderAddress\":\"上海上海市青浦区华新镇北青公路3890弄500号东一库 (民兴工业区)\",\"senderCityId\":\"\",\"senderCityName\":\"\",\"senderCountyId\":\"\",\"senderCountyName\":\"\",\"senderEncryptionCode\":\"\",\"senderId\":\"\",\"senderMobile\":\"4001168100/\",\"senderName\":\"耀点100\",\"senderPhone\":\"\",\"senderPostalCode\":200000,\"senderProvId\":\"\",\"senderProvName\":\"\",\"signoffFailureCode\":\"\",\"signoffTime\":null,\"specialGoodsType\":0,\"startCanvassTime\":null,\"tradeNo\":\"\",\"waybillNo\":\"2385831811\",\"waybillStatus\":0}").length());
//		System.out.println(ZipUtils.unzipString(ZipUtils.compress("{\"acceptCanvassEmpCode\":\"\",\"acceptCanvassEmpName\":\"\",\"acceptFailureCode\":\"\",\"acceptMode\":0,\"acceptOrgCode\":\"\",\"acceptOrgName\":\"\",\"acceptTime\":null,\"acceptUserCode\":\"\",\"acceptUserName\":\"\",\"assignOrgCode\":\"\",\"assignOrgName\":\"\",\"canvassEmpCode\":\"\",\"canvassEmpName\":\"\",\"canvassFailureCode\":\"\",\"canvassMode\":0,\"canvassOrgCode\":\"\",\"canvassOrgName\":\"\",\"canvassTime\":null,\"canvassUserCode\":\"\",\"canvassUserName\":\"\",\"codFee\":0,\"creatorId\":\"\",\"deliveryEmpCode\":\"\",\"deliveryEmpName\":\"\",\"deliveryOrgCode\":\"\",\"deliveryOrgName\":\"\",\"endCanvassTime\":null,\"goodsTotalFee\":0,\"goodsTotalQty\":0,\"goodsTotalWeight\":0,\"goodsType\":0,\"id\":\"\",\"informType\":0,\"isNewOrder\":false,\"isPackage\":false,\"mergeOrderCode\":\"\",\"offerFee\":0,\"orderCancelTime\":null,\"orderChannelCode\":\"\",\"orderCreateTime\":null,\"orderFlag\":0,\"orderLogisticsCode\":\"SP110701000002139\",\"orderNo\":\"\",\"orderStatus\":0,\"orderTypeCode\":\"\",\"recipientAddress\":\"湖南省长沙市雨花区芙蓉中路二段223号二办公楼203\",\"recipientCityId\":\"\",\"recipientCityName\":\"\",\"recipientCountyId\":\"\",\"recipientCountyName\":\"\",\"recipientEncryptionCode\":\"\",\"recipientId\":\"\",\"recipientMobile\":\"\",\"recipientName\":\"肖威娜\",\"recipientPhone\":\"\",\"recipientPostalCode\":0,\"recipientProvId\":\"\",\"recipientProvName\":\"\",\"remark\":\"【喜多】浴盆大-桔\",\"senderAddress\":\"上海上海市青浦区华新镇北青公路3890弄500号东一库 (民兴工业区)\",\"senderCityId\":\"\",\"senderCityName\":\"\",\"senderCountyId\":\"\",\"senderCountyName\":\"\",\"senderEncryptionCode\":\"\",\"senderId\":\"\",\"senderMobile\":\"4001168100/\",\"senderName\":\"耀点100\",\"senderPhone\":\"\",\"senderPostalCode\":200000,\"senderProvId\":\"\",\"senderProvName\":\"\",\"signoffFailureCode\":\"\",\"signoffTime\":null,\"specialGoodsType\":0,\"startCanvassTime\":null,\"tradeNo\":\"\",\"waybillNo\":\"2385831811\",\"waybillStatus\":0}")).length());
//		long time3 = System.nanoTime()/1000000;
		
		System.out.println("时间1：" + (time2 - time1));
//		System.out.println("时间2：" + (time3 - time2));
	}
}

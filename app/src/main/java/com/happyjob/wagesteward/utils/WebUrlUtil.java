package com.happyjob.wagesteward.utils;


import com.happyjob.wagesteward.base.AppConfig;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;


/**
 * Filename: WebUrlUtil.java <br>
 * 
 * Description: 网络请求路径工具类 <br>
 * 
 * @author: HLJ <br>
 * @version: 1.0 <br>
 * @Createtime: 2015-5-19 <br>
 * 
 * @Copyright: Copyright (c)2015 by HLJ <br>
 * 
 */

public class WebUrlUtil {

	/**
	 * @param url 第一个参数代表是那个php，文件名。例如login.php
	 * @param methodName 第二个参数代表访问的那个方法  login
	 * @param params 第三个参数代表所有传的参数放在map里面。
	 * @return
	 * 	LinkedHashMap<String, Object> paramsMap = new LinkedHashMap<String, Object>();
		paramsMap.put("apiusername", "parking");
		paramsMap.put("apipassword", Md5Util.getMD5To32("parking"));
		paramsMap.put("username", username);
		paramsMap.put("sms", sms);
	 */

	/**
	 * @param fileUrl
	 *            第一个参数代表是那个php。例如login.php
	 * @param methodName
	 *            第二个参数代表访问的那个方法 login
	 * @param params
	 *            第三个参数代表所有传的参数放在map里面。
	 * @return LinkedHashMap<String, Object> paramsMap = new
	 *         LinkedHashMap<String, Object>(); paramsMap.put("apiusername",
	 *         "parking"); paramsMap.put("apipassword",
	 *         Md5Util.getMD5To32("parking")); paramsMap.put("username",
	 *         username); paramsMap.put("sms", sms);
	 */
	public static String urlString(String fileUrl, String methodName,
								   HashMap<String, Object> params) {// 第一个fileurl表示是那个php文件。例如login.php//访问的是那个php文件
		StringBuffer absUrl = new StringBuffer();
		absUrl.append(AppConfig.BaseUrl + "/" + fileUrl + "?act=" + methodName);
		if (params != null && params.size() > 0) {
			for (Entry<String, Object> item : params.entrySet()) {
				// 如果有参数的话，设置调用参数
				try {
					if (item.getKey() != null
							&& item.getValue()!=null&&item.getValue().toString() != null) {
						absUrl.append("&"
								+ item.getKey()
								+ "="
								+ URLEncoder.encode(item.getValue().toString(),
								"UTF-8"));
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
//		System.out.println("-----------" + absUrl.toString());
		return absUrl.toString();
	}


}

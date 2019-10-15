package com.secusoft.ssw.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 字符串工具
 * @author yjc
 * 2017年4月14日
 */
public class StringUtil {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 风向角度换算
	 */
	private static final String[] directArr = new String[] { "北风", "东北偏北风", "东北风", "东北偏东风", "东风", "东南偏东风", "东南风", "东南偏南风", "南风","西南偏南风", "西南风", "西南偏西风", "西风", "西北偏西风", "西北风", "西北偏北风" };

	public static String windDirectionSwitch(float fx) {
		int index = 0;
		if (348.75 <= fx && fx <= 360) {
			index = 0;
		} else if (0 <= fx && fx <= 11.25) {
			index = 0;
		} else if (11.25 < fx && fx <= 33.75) {
			index = 1;
		} else if (33.75 < fx && fx <= 56.25) {
			index = 2;
		} else if (56.25 < fx && fx <= 78.75) {
			index = 3;
		} else if (78.75 < fx && fx <= 101.25) {
			index = 4;
		} else if (101.25 < fx && fx <= 123.75) {
			index = 5;
		} else if (123.75 < fx && fx <= 146.25) {
			index = 6;
		} else if (146.25 < fx && fx <= 168.75) {
			index = 7;
		} else if (168.75 < fx && fx <= 191.25) {
			index = 8;
		} else if (191.25 < fx && fx <= 213.75) {
			index = 9;
		} else if (213.75 < fx && fx <= 236.25) {
			index = 10;
		} else if (236.25 < fx && fx <= 258.75) {
			index = 11;
		} else if (258.75 < fx && fx <= 281.25) {
			index = 12;
		} else if (281.25 < fx && fx <= 303.75) {
			index = 13;
		} else if (303.75 < fx && fx <= 326.25) {
			index = 14;
		} else if (326.25 < fx && fx < 348.75) {
			index = 15;
		} else {
			System.out.println("degrees[{}] 大于 360.0了 角度为："+fx);
		}
		return directArr[index];
	}


	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		if(str==null || str.trim()=="" || str.isEmpty()){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断多个字符串中是否含有空字符串
	 * @param strs
	 * @return
	 */
	public static boolean isAnyBlank(String... strs){
		for(String str_ : strs){
			if(isBlank(str_))
				return true;
		}
		return false;
	}

	public static String[] tranferArr(String strs) {
		String replace = strs.replaceAll(",", " , ");
		String[] split = replace.split(",");
		String[] aa = new String[split.length];
		for (int i=0 ;i<split.length;i++) {
			aa[i] = split[i].trim();
		}
		return aa;
	}

	/**
	 * 字符串脱敏
	 * @param str 需脱敏的字符串
	 * @param type 字符串类型：1.姓名 2.手机号 3.身份证号 4.银行卡号
	 * @return
	 */
	public static String getDesensitization(String str,int type){
		if(isBlank(str)){
			return null;
		}else{
			try{
				switch(type){
				case 1:
					StringBuffer name = new StringBuffer(str.substring(0, 1));
					for(int i=1;i<str.length();i++){
						name.append("*");
					}
					return name.toString();
				case 2:
					return str.substring(0,3)+"****"+str.substring(7,11);
				case 3:
					return "**************"+str.substring(str.length()-4, str.length());
				case 4:
					return str.substring(0,6)+"**********"+str.substring(str.length()-4, str.length());
				default:return null;
				}
			}catch (Exception e) {
				return null;
			}
		}
	}
	
	/**
	 * 获取业务流水凭证
	 * @return
	 */
	public static String generateTransactionId(){
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + UUIDString();
	}
	
	/**
	 * 获取UUID
	 * @return
	 */
	public static String UUIDString(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * urlencode转map
	 * @param str
	 * @return
	 */
	public static Map<String, String> analysisPars(String str){
		Map<String, String> result = new HashMap<String, String>();
		String[] strs = str.split("&");
		for(String par : strs){
			result.put(par.split("=")[0], par.split("=")[1]);
		}
		return result;
	}

}

package com.bole.common;

public class Constants {

	public static String URL_ENCODE = "UTF-8";
	
	/**
	 * 默认的起始页
	 */
	public static final int DEFAULT_PAGE_NO = 1;

	/**
	 * 默认的分页显示数量
	 */
	public static final int DEFAULT_PAGE_SIZE = 10;

	public static final String PAGE_NO_NAME = "pageNo";

	public static final String PAGE_SIZE_NAME = "pageSize";
	
	/**
	 * 验证码最大值
	 */
	public static int CHECK_CODE_MAX_LENGTH = 999999;

	public static int SUCCESS_0 = 0;
	public static int ERROR_999 = 999;
	public static int ERROR_100 = 100;
	
	//用户类型
	public static Short USER_TYPE_0 = 0;	//代理
	public static Short USER_TYPE_1 = 1;   //客服
	public static Short USER_TYPE_2 = 2;   //管理员

}

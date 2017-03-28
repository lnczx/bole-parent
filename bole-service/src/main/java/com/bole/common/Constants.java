package com.bole.common;

import java.math.BigDecimal;

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
	public static Short USER_TYPE_0 = 0;	//会员
	public static Short USER_TYPE_1 = 1;   //客服
	public static Short USER_TYPE_2 = 2;   //管理员
	
	//用户级别
	public static final Short USER_LEVEL_0 = 0;  	//0级, 数值默认
	public static final Short USER_LEVEL_1 = 1;  	//1级
	public static final Short USER_LEVEL_2 = 2;  	//2级
	public static final Short USER_LEVEL_3 = 3;  	//3级
	public static final Short USER_LEVEL_4 = 4;  	//4级
	public static final Short USER_LEVEL_5 = 5;  	//5级
	public static final Short USER_LEVEL_6 = 6;  	//6级
	
	//用户激活状态
	public static Short USER_ACTIVE_0 = 0;   //未激活;
	public static Short USER_ACTIVE_1 = 1;   //已激活;
	
	//充值
	public static Short SCORE_TYPE_0 = 0;    //赠送
	public static Short SCORE_TYPE_1 = 1;    //付款
	public static Short SCORE_TYPE_2 = 2;    //返利
	public static Short SCORE_TYPE_3 = 3;    //领取
	//最低可领取数
	public static BigDecimal MIN_SCORE_MONEY_CASH = new BigDecimal(100); //最低可领取数
	
	//最低可领取天数间隔
	public static int MIN_CASH_DATE = 7;

}

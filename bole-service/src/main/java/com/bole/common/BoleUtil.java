package com.bole.common;

import java.math.BigDecimal;

public class BoleUtil {

	public static String getScoreTypeName(Short scoreType) {
		String scoreTypeName = "";
		switch (scoreType) {
		case 0:
			scoreTypeName = "赠送";
			break;
		case 1:
			scoreTypeName = "付款";
			break;
		case 2:
			scoreTypeName = "返利";
			break;
		default:
			scoreTypeName = "";
		}
		return scoreTypeName;
	}

	// 获得返利比例
	public static BigDecimal getLevelRatio(short level) {
		BigDecimal payBackRatio = new BigDecimal(0);
		
		switch (level) {
		case 1:
			payBackRatio = new BigDecimal(0.05);
			break;
		case 2:
			payBackRatio = new BigDecimal(0.04);
			break;
		case 3:
			payBackRatio = new BigDecimal(0.03);
			break;
		case 4:
			payBackRatio = new BigDecimal(0.02);
			break;
		case 5:
			payBackRatio = new BigDecimal(0.01);
			break;
		case 6:
			payBackRatio = new BigDecimal(0.005);
			break;
		default:
			payBackRatio = new BigDecimal(0);
		}
		
		return payBackRatio;
	}

	public static String getScoreCashStatusName(Short status) {
		String cashStatusName = "";
		switch (status) {
		case 0:
			cashStatusName = "审核中";
			break;
		case 1:
			cashStatusName = "已通过";
			break;
		case 2:
			cashStatusName = "已关闭";
			break;
		default:
			cashStatusName = "";
		}
		return cashStatusName;
	}

}

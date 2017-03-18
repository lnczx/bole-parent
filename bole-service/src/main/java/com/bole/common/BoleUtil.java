package com.bole.common;

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
	public static double getPayBackRatio(short level) {
		double payBackRatio = 0;
		
		switch (level) {
		case 1:
			payBackRatio = (double)0.05;
			break;
		case 2:
			payBackRatio = (double)0.04;
			break;
		case 3:
			payBackRatio = (double)0.03;
			break;
		case 4:
			payBackRatio = (double)0.02;
			break;
		case 5:
			payBackRatio = (double)0.01;
			break;
		case 6:
			payBackRatio = (double)0.005;
			break;
		default:
			payBackRatio = 0;
		}
		
		return payBackRatio;
	}

}

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

}

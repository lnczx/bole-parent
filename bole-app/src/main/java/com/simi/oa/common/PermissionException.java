package com.simi.oa.common;

/**
 * 权限异常类
 * @author misswhen
 *
 */
public class PermissionException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -6008004025445496953L;

	public PermissionException(String msg)
    {
        super(msg);
    }

}

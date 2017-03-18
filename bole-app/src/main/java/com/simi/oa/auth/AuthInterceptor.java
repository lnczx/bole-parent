package com.simi.oa.auth;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bole.common.Constants;
import com.bole.po.model.user.User;
import com.simi.oa.common.PermissionException;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);

			// 没有声明需要权限,或者声明不验证权限
			if (authPassport == null || authPassport.validate() == false)
				return true;
			else {

				AccountAuth accountAuth = AuthHelper.getSessionAccountAuth(request);
				if (accountAuth != null) {
					boolean hasPermission = false;
					String requestServletPath = request.getServletPath();

					User u = accountAuth.getU();

					Short userType = u.getUserType();
					
					if (userType.equals(Constants.USER_TYPE_2)) {
						hasPermission = true;
					}

					if (userType.equals(Constants.USER_TYPE_0) && 
							(
								requestServletPath.indexOf("home") >= 0 ||
								requestServletPath.indexOf("user") >= 0
							)
						) {
						hasPermission = true;
					}

					if (userType.equals(Constants.USER_TYPE_1) && 
						requestServletPath.indexOf("admin") < 0) {
						hasPermission = true;
					}

					if (hasPermission)
						return true;
					else
						throw new PermissionException("没有权限！");
					
				} else {
					StringBuilder urlBuilder = new StringBuilder(request.getContextPath());
					urlBuilder.append("/home/login");
					if (request.getServletPath() != null && !request.getServletPath().isEmpty()) {
						urlBuilder.append("?returnUrl=");

						StringBuilder pathAndQuery = new StringBuilder(request.getServletPath());
						if (request.getQueryString() != null && !request.getQueryString().isEmpty()) {
							pathAndQuery.append("?");
							pathAndQuery.append(request.getQueryString());
						}

						urlBuilder.append(URLEncoder.encode(pathAndQuery.toString(), "UTF-8"));
					}

					response.sendRedirect(urlBuilder.toString());
					return false;
				}

			}
		} else
			return true;
	}

	public static void main(String[] args) {
		String perm = "^/staff";
		String reu = "/staff/partners";
		Pattern pattern = Pattern.compile(perm, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(reu);
		System.out.println(matcher.find());
	}
}
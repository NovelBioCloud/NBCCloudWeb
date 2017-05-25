package com.novelbio.portal.web.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.novelbio.portal.base.framework.ResultJson;
import com.novelbio.portal.biz.entity.UserEntity;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		if (userEntity != null && userEntity.isLogin()) {
			return true;
		} else {
			if (isAjaxRequest(request)) {
				response.setHeader("ContentType", "application/json");
				response.getWriter().write(JSON.toJSONString(new ResultJson(false, "没有权限")));
			} else {
				response.sendRedirect("");
			}
			return false;
		}
	}

	private boolean isAjaxRequest(HttpServletRequest request) {
		return request != null && request.getHeader("x-requested-with") != null
				&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}
}
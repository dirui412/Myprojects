package lj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lj.exception.user.AuthorizationException;
import lj.global.AppConst;
import lj.util.PagerUtils;

public class GlobalInterceptor implements HandlerInterceptor {
	private List<String> excludedDirs = null;

	public void setExcludedDirs(List<String> excludedDirs) {
		this.excludedDirs = excludedDirs;
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object handler) throws Exception {
		 System.out.println("preHandle request servlet path:" +req.getServletPath());
		// 1-忽略不要处理的请求
		// 1.1用户请求主页
		if (req.getServletPath().equals("/") == true)
			return true;
		// 1.2用户登录
		if (req.getServletPath().indexOf(AppConst.URL_LOGIN) >= 0)
			return true;
		// 1.3用户下载
		if (req.getServletPath().indexOf(AppConst.URL_DOWNLOAD) >= 0)
			return true;
		// 1.4其他排除的URL
		for (String temp : excludedDirs)
			if (req.getServletPath().indexOf(temp) >= 0) {
				// System.out.println("excludedDirs:"+temp);
				return true;
			}

		// 2-判断用户Session是否正常
		HttpSession session = req.getSession();
		if (session.getAttribute(AppConst.SESSION_USERINFO) == null) {
			throw new AuthorizationException();
		}

		// 3-拦截分页请求，保持到QueryContext
		if (req.getParameter(PagerUtils.REQ_PAGE_SIZE) != null 
				&& req.getParameter(PagerUtils.REQ_START_ROW) != null) {
			PagerUtils.parseRequest(req);
		}

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("afterCompletion");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("postHandle");
	}

}
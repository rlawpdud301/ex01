package co.yi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import co.yi.controller.UserController;
import co.yi.domain.LoginDTO;
import co.yi.domain.MemberVO;

public class LoginInterceptor implements HandlerInterceptor {

	private static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("login preHandle------------");
		
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		logger.info("login postHandle------------");
		
		LoginDTO memberVO = (LoginDTO)modelAndView.getModel().get("memberVO");
		
		if (memberVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute(LOGIN, memberVO);
			Object dest = session.getAttribute("dest");
			String path = dest != null ? (String)dest : request.getContextPath()+"/sboard/list";
			
			response.sendRedirect(path);
		}
		

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("login preHandle------------");

	}

}

package org.board.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	private static final String LOGIN = "login";
	private static final Logger logger = 
			LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		
		Object userVO = modelMap.get("userVO");
		
		// userVO가 잇을경우
		if(userVO != null){
			
			session.setAttribute(LOGIN, userVO);
			
			// 사용자가 remember me를 클릭햇으면
			if(request.getParameter("useCookie") != null){

				logger.info("remember me....................");
				// 쿠키 생성 loginCookie = 키 값:사용자 아이디
				Cookie loginCookie = new Cookie("loginCookie", session.getId());
				loginCookie.setPath("/");
				loginCookie.setMaxAge(60 * 60 * 24 * 7);
				// 쿠키 심는 메서드
				response.addCookie(loginCookie);
			}
			
			Object dest = session.getAttribute("dest");
			
			response.sendRedirect(dest != null ? (String) dest : "/");
		}
		
	}

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		// 세션 검사 세션이 잇으면 세션삭제
		if(session.getAttribute(LOGIN) != null){
			
			System.out.println("pre 세션잇음");
			session.removeAttribute(LOGIN);
		}
		System.out.println("pre");
		return true;
	}

}

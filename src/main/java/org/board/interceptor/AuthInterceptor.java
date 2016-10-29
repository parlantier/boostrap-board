package org.board.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.board.domain.UserVO;
import org.board.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

// 글쓰기페이지 요청이들어오면 실행됨
public class AuthInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger =
			LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Inject
	private UserService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		// 메서드가 실행하기전에 세션객체를 얻어옴
		HttpSession session = request.getSession();
		
		// 세션이 없으면
		if(session.getAttribute("login") == null){
			
			logger.info("current user is not logined");
			
			// dest란 이름으로 사용자 요청uri를 저장
			saveDest(request);
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			if(loginCookie != null){
				
				UserVO userVO = service.checkLoginBefore(loginCookie.getValue());
				
				logger.info("USERVO: " + userVO);
				
				if(userVO != null){
					session.setAttribute("login", userVO);
					return true;
				}
				
			}
			
			response.sendRedirect("/user/login");
			return false;
			
		}
			return true;
	}

	private void saveDest(HttpServletRequest req){
		
		String uri = req.getRequestURI();
		String query = req.getQueryString();
		
		if(query == null || query.equals("null")){
			query = "";
		}else{
			query = "?" + query;
		}
		
		if(req.getMethod().equals("GET")){
			logger.info("dest: " + (uri + query));
			req.getSession().setAttribute("dest", uri + query);
		}
		
	}
	
	
}

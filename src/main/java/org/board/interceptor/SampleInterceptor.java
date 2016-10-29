package org.board.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor extends HandlerInterceptorAdapter{

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("post handle..............");
		
		Object result = modelAndView.getModel().get("result");
		
		if(result != null){
			request.getSession().setAttribute("result", result);
			response.sendRedirect("/doA");
		}
		
		
	}

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub

		// Object타입의 handler가 인자로 들어옴
		// Object타입을 원래 타입으로 다운캐스팅 HandlerMethod method
		// HandlerMethod method.getBean()은 빈이름을 리턴
		// method.getMethod는 Method타입을 리턴
		System.out.println("pre handle.......................");
		
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		
		System.out.println("Bean: "+method.getBean());
		System.out.println("Method: " + method.getMethod());
		
		
		return true;
	}

	
	
}

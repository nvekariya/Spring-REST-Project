package edu.npu.library.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.npu.library.domain.User;

/**
 * Interceptor used to filter requests sent by user who has not logged in
 * @author Neeta Vekairya
 *
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		
		String context = request.getContextPath();
		String uri = request.getRequestURL().toString();
		
		logger.info("uri:" +uri);
		if(!uri.endsWith("login") && !uri.endsWith("logout")){
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			if(user == null){
				session.invalidate();
				response.sendRedirect(context+"/");
				return false;
			}
		}
		return true;
	}
}

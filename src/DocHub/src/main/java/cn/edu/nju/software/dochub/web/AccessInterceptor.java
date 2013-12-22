package cn.edu.nju.software.dochub.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AccessInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}	

	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		UserAccessContext userAccessContext = (UserAccessContext) request.getSession().getAttribute("userAccessContext");
		String uri = request.getRequestURI();
		boolean loginaj =uri.contains("login.aj");
		boolean loginhtml=uri.contains("login.html");
		System.out.println(uri+" "+loginaj);
		if(userAccessContext == null && !loginaj && !loginhtml){
			if(uri.endsWith(".aj")){
				//forbidden
				response.sendError(403);
				return false;
			}
			response.sendRedirect(request.getContextPath()+"/login.html");
//			request.getRequestDispatcher("/").forward(request, response);
			return false;
		}
		return true;
	}

}

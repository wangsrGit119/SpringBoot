package cn.itcast.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//拦截器  拦截登录检查 防止访问未登录界面
//拦截器写碗一定要配置   在webMvcConfigureAdapter----因为是扩展的mvc功能
public class LoginHandlerInterceptor implements HandlerInterceptor {

	//目标方法执行之前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object user=request.getSession().getAttribute("loginuser");
		if (user==null) {
			//未登录  则返回登录界面
			request.setAttribute("msg", "请先登录");
			request.getRequestDispatcher("/login.html").forward(request, response);
			return false;
		}else {
			//登录则放行
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	

}

package cn.singularity.appsys.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 处理请求跳转到对应目录的页面
 * 
 * 页面请求包含/dev/则跳转到 developer目录
 * 		包含/backend/则跳转到 backend目录
 * 在控制器中
 * handle方法返回jsp页面对应的逻辑视图名即可,无需添加目录
 * @author zh
 */
public class PageInterceptor extends HandlerInterceptorAdapter{
	private final String bkPrefix = "developer/";
	private final String devPrefix = "developer/";
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			
		//如果是静态文件则不拦截
		if (request.getRequestURI().indexOf("/statics/") >= 0) {
			return;
		}
		
		if (modelAndView != null) {
			String prefix = "";
			if (modelAndView.getViewName().indexOf("redirect:") == 0
				|| modelAndView.getViewName().indexOf("forward:") == 0) {
				return;
			}
			if (request.getRequestURI().indexOf("/dev/") >= 0) {
				prefix = "backend/";
			} else if (request.getRequestURI().indexOf("/back/") >= 0) {
				prefix = "backend/";
			} else {
				return;
			}
			modelAndView.setViewName(prefix + modelAndView.getViewName());
			
		}
		System.out.print("后拦截 :url = ");
		System.out.print(request.getRequestURI());
		System.out.println(":viewName = " + modelAndView.getViewName());
	}


}

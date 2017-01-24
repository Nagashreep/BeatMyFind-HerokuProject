package com.shrishti.siri.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class LoggingInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

	@Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response,
                              Object handler) throws Exception {
//        RequestMapping rm = ((HandlerMethod) handler).getMethodAnnotation(
//                            RequestMapping.class);
//
//        User loggedinUser = (User) request.getSession().getAttribute("user");
		
		System.out.println("Inside prehandle: "+handler+" >>>: "+handler.getClass());
        
        log.debug("Inside prehandle: "+handler+" >>>: "+handler.getClass());

        return true;
	}
	
}

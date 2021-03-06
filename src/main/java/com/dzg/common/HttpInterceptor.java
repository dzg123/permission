package com.dzg.common;

import com.dzg.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {
    private static final String START_TIME = "requestStartTime";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = String.valueOf(request.getRequestURL());
        Map map = request.getParameterMap();
        log.info("request start:url:{},param:{}",url, JsonMapper.obj2String(map));
        long start = System.currentTimeMillis();
        request.setAttribute(START_TIME,start);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        String url = String.valueOf(request.getRequestURL());
//        Map map = request.getParameterMap();
//
//        log.info("request finished:url:{},param:{}",url, JsonMapper.obj2String(map));
        removeThreadLocalInfo();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = String.valueOf(request.getRequestURL());

        long start = (long) request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();
        log.info("request complete:url:{},costTime:{}",url,end-start );
        removeThreadLocalInfo();
    }
    public void removeThreadLocalInfo(){
        RequestHolder.remove();
    }
}

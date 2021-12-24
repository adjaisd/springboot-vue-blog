package com.adjaisd.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class CsrfFilter implements Filter {

 @Value("${CORS_ALLOWED_ORIGINS}")
 private String[] allowedOrigins;

 @Value("${CORS_ALLOWED_METHOD}")
 private String allowedMethods;

 @Override
 public void destroy() {}

 @Override
 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
   HttpServletResponse httpServletResponse = (HttpServletResponse) response;
   HttpServletRequest httpServletRequest = (HttpServletRequest) request;
   String originHeader = httpServletRequest.getHeader("Origin") != null ? httpServletRequest.getHeader("Origin").replace(" ", "")
       : httpServletRequest.getHeader("Origin");
   // 判断数组中是否包含请求中的origin，如果包含就做跨域设置，否则就是不被允许跨域的地址，不做跨域设置
   if (Arrays.asList(allowedOrigins).contains(originHeader)) {
     httpServletResponse.setHeader("Access-Control-Allow-Origin", originHeader);
     httpServletResponse.setHeader("Access-Control-Allow-Headers", "accept,content-type");
     httpServletResponse.setHeader("Access-Control-Allow-Methods", allowedMethods);
     httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
   }
   // 这里还可以添加一些想对response header添加的信息
   chain.doFilter(request, httpServletResponse);
 }

 @Override
 public void init(FilterConfig arg0) throws ServletException {}
}

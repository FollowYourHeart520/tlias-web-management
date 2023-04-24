package com.ceshiren.filter;


import com.alibaba.fastjson.JSONObject;
import com.ceshiren.pojo.Result;
import com.ceshiren.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest hsqt = (HttpServletRequest)servletRequest;
        HttpServletResponse hsqo = (HttpServletResponse)servletResponse;

        //1。获取请求url
        String url = hsqt.getRequestURL().toString();

        //2。判断是否是登录接口
        if(url.contains("login")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //3。获取请求头中的token令牌
        String jwt = hsqt.getHeader("token");

        //4。判断令牌是否存在
        if(!StringUtils.hasLength(jwt)){
            Result not_login = Result.error("NOT_LOGIN");
            String s = JSONObject.toJSONString(not_login);
            servletResponse.getWriter().write(s);
            return;
        }

        //5。解析token
        try{
            JwtUtils.parseJWT(jwt);


        }catch (Exception e){
            //7。解析失败
            e.printStackTrace();
            Result not_login = Result.error("NOT_LOGIN");
            String s = JSONObject.toJSONString(not_login);
            servletResponse.getWriter().write(s);
            return;
        }

        //6。解析成功
        filterChain.doFilter(servletRequest,servletResponse);
    }
}

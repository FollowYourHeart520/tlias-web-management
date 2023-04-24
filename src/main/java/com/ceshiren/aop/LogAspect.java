package com.ceshiren.aop;


import com.alibaba.fastjson.JSONObject;
import com.ceshiren.mapper.OperateLogMapper;
import com.ceshiren.pojo.OperateLog;
import com.ceshiren.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Aspect
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Around("@annotation(com.ceshiren.anno.Log)")
    public Object runMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {


        //操作人
        String token = httpServletRequest.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);
        Integer operateUser = (Integer) claims.get("id");

        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //全类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();

        //方法名
        String methodName = proceedingJoinPoint.getSignature().getName();

        //参数
        Object[] args = proceedingJoinPoint.getArgs();

        long begin = System.currentTimeMillis();
        //执行方法
        Object resultValue = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        //执行时常
        long time = end - begin;
        OperateLog operateLog= new OperateLog(null,operateUser,operateTime,className,methodName,Arrays.toString(args),
                JSONObject.toJSONString(resultValue),time);
        return null;
    }

}

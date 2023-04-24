package com.ceshiren.controller;

import com.ceshiren.pojo.Emp;
import com.ceshiren.pojo.Result;
import com.ceshiren.service.EmpService;
import com.ceshiren.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("登录",emp);
        emp = empService.login(emp);
        if(emp!=null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",emp.getId());
            claims.put("name",emp.getName());
            claims.put("username",emp.getUsername());

            String s = JwtUtils.generateJwt(claims);
            return Result.success(s);
        }
        return Result.error("登录失败");
    }
}

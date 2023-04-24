package com.ceshiren.service.impl;

import com.ceshiren.mapper.EmpMapper;
import com.ceshiren.pojo.Emp;
import com.ceshiren.pojo.PageBean;
import com.ceshiren.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean queryEmps(Integer page, Integer pageSize, String name,
                              Short gender, LocalDate begin, LocalDate end) {
        //使用pageHelper实现
        //设置分页参数
        PageHelper.startPage(page,pageSize);

        List<Emp> emps = empMapper.queryEmps(name,gender,begin,end);
        Page<Emp> p= (Page<Emp>)emps;

        //封装对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;


        /*//获取总记录数
        Long total = empMapper.queryTotal();
        log.info("total==============================:{}",total);

        //获取列表
        int start = (page-1)*pageSize;
        List<Emp> empList =  empMapper.queryPageEmps(start,pageSize);
        log.info("emplist==============================:{}",empList);

        //封装对象
        PageBean pageBean = new PageBean(total,empList);

        return pageBean;
         */
    }

    @Override
    public void deleteEmps(int[] ids) {
        empMapper.deleteEmps(ids);
    }

    @Override
    public void insertEmp(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        emp.setCreateTime(LocalDateTime.now());
        empMapper.insertEmp(emp);
    }

    @Override
    public Emp queryEmp(Integer id) {
        return  empMapper.queryEmp(id);
    }

    @Override
    public void modifyEmp(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.modifyEmp(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}

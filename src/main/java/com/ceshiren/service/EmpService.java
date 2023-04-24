package com.ceshiren.service;


import com.ceshiren.pojo.Emp;
import com.ceshiren.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 员工管理
 */
public interface EmpService {
    PageBean queryEmps(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void deleteEmps(int[] ids);

    void insertEmp(Emp emp);

    Emp queryEmp(Integer id);

    void modifyEmp(Emp emp);

    Emp login(Emp emp);
}

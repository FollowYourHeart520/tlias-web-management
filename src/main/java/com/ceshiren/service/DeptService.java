package com.ceshiren.service;

import com.ceshiren.pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    public List<Dept> queryDepts();

    void deleteDeptByID(Integer id);

    void  insertDept(Dept dept);

    Dept queryDept(Integer id);

    void modifyDept(Dept dept);
}

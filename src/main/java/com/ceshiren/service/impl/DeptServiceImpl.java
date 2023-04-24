package com.ceshiren.service.impl;

import com.ceshiren.mapper.DeptMapper;
import com.ceshiren.pojo.Dept;
import com.ceshiren.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    public List<Dept> queryDepts(){
        return deptMapper.queryDepts();
    }

    @Override
    public void deleteDeptByID(Integer id) {
        deptMapper.deleteDeptByID(id);
    }

    @Override
    public void insertDept(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insertDept(dept);
    }

    @Override
    public Dept queryDept(Integer id) {
        return deptMapper.queryDept(id);
    }

    @Override
    public void modifyDept(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.modifyDept(dept);
    }
}

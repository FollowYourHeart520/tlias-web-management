package com.ceshiren.controller;

import com.ceshiren.anno.Log;
import com.ceshiren.pojo.Dept;
import com.ceshiren.pojo.Result;
import com.ceshiren.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result queryDepts(){
        log.info("查询全部部门");
        List<Dept> depts = deptService.queryDepts();
        return Result.success(depts);
    }

    @Log
    @DeleteMapping("/{id}")
    public Result deleteDept(@PathVariable Integer id){
        log.info("根据id删除部门：{}",id);
        deptService.deleteDeptByID(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result insertDept(@RequestBody Dept dept){
        deptService.insertDept(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result queryDept(@PathVariable Integer id){
        log.info("查询部门");
        Dept dept = deptService.queryDept(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping
    public Result modifyDept(@RequestBody Dept dept){
        deptService.modifyDept(dept);
        return Result.success();
    }

}

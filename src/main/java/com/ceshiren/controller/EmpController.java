package com.ceshiren.controller;

import com.ceshiren.pojo.Emp;
import com.ceshiren.pojo.PageBean;
import com.ceshiren.pojo.Result;
import com.ceshiren.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理Controller
 */
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;
    /**
     * 分页查询
     */
    @GetMapping
    public Result queryEmps(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10")  Integer pageSize,
                            String name,
                            Short gender,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        PageBean pageBean= empService.queryEmps(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }

    @DeleteMapping("/{ids}")
    public Result deleteEmps(@PathVariable int[] ids){
        empService.deleteEmps(ids);
        return Result.success();
    }

    @PostMapping
    public Result insertEmp(@RequestBody Emp emp){
        empService.insertEmp(emp);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    public Result queryEmp(@PathVariable Integer id){
        Emp emp = empService.queryEmp(id);
        return Result.success(emp);
    }

    /**
     * 修改员工信息
     */
    @PutMapping
    public Result modifyEmp(@RequestBody Emp emp){
        empService.modifyEmp(emp);
        return Result.success();
    }
}

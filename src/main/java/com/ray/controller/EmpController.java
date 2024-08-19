package com.ray.controller;

import com.ray.pojo.Emp;
import com.ray.pojo.PageBean;
import com.ray.pojo.Result;
import com.ray.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理Controller
 */

@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    // 新增员工
    @PostMapping("/emps")
    public Result save(@RequestBody Emp emp){
        log.info("新增员工,emp:{}",emp);
        empService.save(emp);
        return Result.success();
    }

    //批量删除员工
    @DeleteMapping("/emps/{ids}")
    private Result delete(@PathVariable List<Integer> ids){
        empService.delete(ids);
        return Result.success();
    }

    // 查询回显
    @GetMapping("/emps/{id}")
    private Result getById(@PathVariable Integer id){
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    // 编辑信息保存至数据库并返回信息
    @PutMapping("/emps")
    private Result update(@RequestBody Emp emp){
        empService.update(emp);
        return Result.success();
    }

    // 条件分页查询
    @GetMapping("/emps")
    private Result page(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        String name, Short gender,
                        @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        // 记录日志
        log.info("分页查询,参数为{},{},{},{},{},{}",page, pageSize,name,gender,begin,end);

        //调用业务层分页查询功能
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);

        return Result.success(pageBean);

    }

}

package com.ray.controller;


import com.ray.annotation.Log;
import com.ray.pojo.Dept;
import com.ray.pojo.Result;
import com.ray.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/depts")
    private Result list(){
        log.info("查询全部部门数据");

        // 调用service查询部门数据
        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }


    @DeleteMapping("/depts/{id}")
    private Result delete(@PathVariable Integer id) throws Exception {
        log.info("成功删除第{}行",id);

        // 调用service删除数据
        deptService.delete(id);

        return Result.success();    // 删除操作无需返回任何信息
    }


    @PostMapping("/depts")
    private Result add(@RequestBody Dept dept){
        log.info("新增部门" + dept);
        deptService.add(dept);

        return Result.success();
    }

    @GetMapping("/depts/{id}")
    private Result inquire(@PathVariable Integer id){
        log.info("成功查询第{}行数据",id);

        Dept dept = deptService.inquire(id);

        return Result.success(dept);
    }


    @PutMapping("/depts")
    private Result Update(@RequestBody Dept dept){
        log.info("成功修改第{}行数据",dept.getId());

        deptService.update(dept);

        return Result.success();
    }




}

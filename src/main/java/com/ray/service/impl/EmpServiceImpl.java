package com.ray.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ray.annotation.Log;
import com.ray.mapper.EmpMapper;
import com.ray.pojo.Emp;
import com.ray.pojo.PageBean;
import com.ray.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Log
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
//        // 1. 获取总记录数
//        Long count = empMapper.count();
//
//        // 2. 获取分页查询结果列表
//        Integer start = (page - 1) * pageSize;
//        List<Emp> empList = empMapper.list(start, pageSize);

        // 使用PageHelper
        // 1. 设置分页参数
        PageHelper.startPage(page, pageSize);

        // 2. 执行分页查询
        List<Emp> empList = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) empList;

        // 3. 封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        //补全数据
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        //调用添加方法
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        Emp emp = empMapper.getById(id);
        return emp;
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        Emp e = empMapper.getByUsernameAndPassword(emp);
        return e;
    }
}

package com.ray.service;

import com.ray.pojo.Emp;
import com.ray.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {
    /**
     *  条件分页查询
     * @param page  起始页码
     * @param pageSize  每页展示记录数
     * @return
     */
    PageBean page(Integer page, Integer pageSize,String name, Short gender,LocalDate begin, LocalDate end);

    /**
     * 根据部门删除信息
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 保存员工
     * @param emp
     */
    void save(Emp emp);

    /**
     * 查询员工
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 更新员工
     * @param emp
     */
    void update(Emp emp);

    Emp login(Emp emp);
}

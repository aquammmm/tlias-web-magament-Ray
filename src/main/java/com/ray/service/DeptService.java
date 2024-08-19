package com.ray.service;

import com.ray.pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    /**
     * 查询全部部门数据
     * @return
     */
    List<Dept> list();

    /**
     * 根据id删除部门信息
     * @param id
     */
    void delete(Integer id);

    void add(Dept dept);

    Dept inquire(Integer id);

    void update(Dept dept);
}

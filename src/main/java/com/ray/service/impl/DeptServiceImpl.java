package com.ray.service.impl;

import com.ray.annotation.Log;
import com.ray.aop.MyLog;
import com.ray.mapper.DeptLogMapper;
import com.ray.mapper.DeptMapper;
import com.ray.mapper.EmpMapper;
import com.ray.pojo.Dept;
import com.ray.pojo.DeptLog;
import com.ray.service.DeptLogService;
import com.ray.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    @MyLog
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    // 根据部门id删除部门信息，以及部门之下的所有员工
    @Override
    @MyLog
    @Transactional(rollbackFor = Exception.class)      // 给当前方法添加事务管理
    public void delete(Integer id) throws Exception {

        try {
            // 根据部门id删除部门数据
            deptMapper.deleteById(id);

            // 模拟报错发生
//            if(true){
//                throw new Exception("出现异常了~~~");
//            }

            // 根据id删除部门员工数据
            empMapper.deleteByDeptId(id);
        } finally {
            System.out.println("1111111111111111111111111111111111111");
//             无论是否异常都要执行的代码：记录日志
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此时解散的 是"+id+"号部门");
            deptLogService.insert(deptLog);
        }

    }

    @Override
    public void add(Dept dept) {
        //补全部门数据
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }

    @Override
    public Dept inquire(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.update(dept);
    }
}

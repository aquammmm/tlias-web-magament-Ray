package com.ray.mapper;

import com.ray.annotation.Log;
import com.ray.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {
    // 获取总记录数
//    @Select("select count(*) from emp")
//    public Long count();

    // 获取当前页的结果列表
//    @Select("select * from emp limit #{arg0}, #{arg1}")
//    public List<Emp> list(Integer start, Integer pageSize);

    /**
     * 员工信息查询（使用pagehelper）
     * @return
     */
    public List<Emp> list(@Param("name") String name, @Param("gender") Short gender, @Param("begin") LocalDate begin, @Param("end") LocalDate end);

    void delete(@Param("idd") List<Integer> ids);

    //新增员工
    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime});")
    void insert(Emp emp);

    @Select("select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time from emp where id = #{id}")
    Emp getById(Integer id);

    // 修改员工信息
    void update(Emp emp);

    // 登录
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);

    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}

package com.ceshiren.mapper;

import com.ceshiren.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {

    /**
     * 员工信息查询,使用pageHelper
     */
//    @Select("select * from emp")
    public List<Emp> queryEmps(String name, Short gender, LocalDate begin, LocalDate end);

    void deleteEmps(int[] ids);

    @Insert("insert into emp(username,name,gender,image,job,entrydate,dept_id,create_time,update_time) " +
            "values( #{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    void insertEmp(Emp emp);

    @Select("select * from emp where id= #{id}")
    Emp queryEmp(Integer id);

    void modifyEmp(Emp emp);

    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp getByUsernameAndPassword(Emp emp);
    /**
     * 查询总记录数
     * @return
     *//*
    @Select("select count(*) from emp")
    public Long queryTotal();

    @Select("select * from emp limit #{start},#{pageSize}")
    public List<Emp> queryPageEmps(Integer start,Integer pageSize);*/
}

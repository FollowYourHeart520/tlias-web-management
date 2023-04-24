package com.ceshiren.mapper;

import com.ceshiren.pojo.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {

    @Select("select * from dept")
    public List<Dept> queryDepts();

    @Delete("delete from dept where id= #{id}")
    public void deleteDeptByID(Integer id);

    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void insertDept(Dept dept);

    @Select("select * from dept where id = #{id}")
    public Dept queryDept(Integer id);

    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void modifyDept(Dept dept);
}

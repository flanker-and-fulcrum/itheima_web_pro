package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    //根据ID删除数据
    @Delete("delete from db04.emp where id=#{id}")
    //返回值表示操作影响的记录的条数
    public int deleteEmp(Integer id);

    //新增员工
    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("insert " +
            "into db04.emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")

    public void AddEmp(Emp emp);


    //修改员工的信息
    @Update("update db04.emp\n" +
            "set username=#{username}, name=#{name}, gender=#{gender}, image=#{image}, job=#{job}, entrydate=#{entrydate}, dept_id=#{deptId}, update_time=#{updateTime}\n" +
            "where id=#{id}")
    public void updateEmp(Emp emp);

    //根据id查询员工
    /**
     * 由于数据库中的字段和Java类中的成员变量命名不同，字段名不一致mybaits不会自动封装到类中
     * 解决方法：
     * 1、给数据库中的字段起别名
     * 2、结果映射使用注解@Results({@Result(column="数据库字段名", property="java类成员变量")，....})
     * 3、在pom.xml配置文件中开启驼峰命名选项并设置为true
     * **/
    @Select("select * from db04.emp where id=#{id}")
    public Emp testSelectEmp(Integer id);

    @Select("select id, username, password, name, gender, image, job, entrydate," +
                        "dept_id deptId, create_time createTime, update_time updateTime from db04.emp where id=#{id}")
    public Emp testSelectEmp2(Integer id);

    /***
     * private Integer deptId;
     *     private LocalDateTime createTime;
     *     private LocalDateTime updateTime;
     *
     * **/

    @Results({
            @Result(column = "dept_id",property = "deptId"),
            @Result(column="create_time", property = "createTime"),
            @Result(column = "update_time",property = "updateTime")
    })
    @Select("select * from db04.emp where id=#{id}")
    public Emp testSelectEmp3(Integer id);


    /**
     *方法1
     * @Select("select id, username, password, name, gender, image, job, entrydate," +
     *             "dept_id deptId, create_time createTime, update_time updateTime,  from db04.emp where id=#{id}")
     *     public Emp testSelectEmp(Integer id);
     * ***/

    /***
     * 方法2
     * @Results({@Result(column = "dept_id",property = "deptId"),
     *                        @Result(column="create_time", property = "createTime"),
     *                         @Result(column = "update_time",property = "updateTime")})
     *     @Select("select * from db04.emp where id=#{id}")
     *     public Emp testSelectEmp2(Integer id);
     * ***/

    //like条件查询
    @Results({
            @Result(column = "dept_id",property = "deptId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("select * from db04.emp where name like '%${name}%' and entrydate >= #{begin} and entrydate <=#{end}")
    public Emp[] SelectLike(String name, LocalDate begin, LocalDate end);

    public Emp[] SelectLike2(String name, LocalDate begin, LocalDate end);

    //动态SQL语句(条件查询)
    public Emp[] SelectDynamicSql(String name, short genders, LocalDate begins, LocalDate end);

    public Emp[] SelectDynamicSql2(String name, short genders, LocalDate begins, LocalDate end);

    //动态SQL语句(更新用户)
    public int UpdateDynamicSql(Emp emp);

    //动态SQL语句(删除用户列表的用户)
    public int deleteDynamicSqlByIds(List<Integer> Ids);
}

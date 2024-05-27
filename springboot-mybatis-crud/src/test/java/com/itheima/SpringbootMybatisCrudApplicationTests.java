package com.itheima;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringbootMybatisCrudApplicationTests {
    @Autowired
    private EmpMapper empMapper;

    @Test
    public void testDelete(){
        int index=empMapper.deleteEmp(17);
        System.out.println(index);
    }

    @Test
    public void testAddEmp(){
        Emp  emp=new Emp();
        emp.setUsername("Tom3");
        emp.setName("汤姆3");
        emp.setImage("103.jpg");
        emp.setGender((short)1);
        emp.setJob((short)1);
        emp.setEntrydate(LocalDate.of(2021,1,5));
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setDeptId(2);

        empMapper.AddEmp(emp);
        System.out.println(emp.getId());
    }

    @Test
    public void testUpDateEmp(){
        Emp  emp=new Emp();
        emp.setUsername("Tom2");
        emp.setName("老汤姆");
        emp.setImage("1.jpg");
        emp.setGender((short)1);
        emp.setJob((short)1);
        emp.setEntrydate(LocalDate.of(2000,1,2));
        //emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setDeptId(1);
        emp.setId(18);
        empMapper.updateEmp(emp);
    }

    @Test
    public void testSelectEmp(){
        Emp e=empMapper.testSelectEmp(12);
        System.out.println(e);
    }

    @Test
    public void testSelectEmp2(){
        Emp e=empMapper.testSelectEmp2(13);
        System.out.println(e);
    }

    @Test
    public void testSelectEmp3(){
        Emp e=empMapper.testSelectEmp3(13);
        System.out.println(e);
    }

    @Test
    public void testSelectLike(){
        Emp[] emps=empMapper.SelectLike("张",LocalDate.of(2002,8,1),
        LocalDate.of(2015,2,1));

        Arrays.stream(emps).forEach(emp -> System.out.println(emp+"-->"+"entrydate:"+
                emp.getEntrydate()+"\n"));
    }

    @Test
    public void testSelectLike2(){
        Emp[] emps=empMapper.SelectLike2("张",LocalDate.of(2002,8,1),
                LocalDate.of(2015,2,1));

        Arrays.stream(emps).forEach(emp -> System.out.println(emp+"-->"+"entrydate:"+
                emp.getEntrydate()+"\n"));
    }


    @Test
    public void testDynamicSql1(){
        Emp[] emps=empMapper.SelectDynamicSql("张",(short) 1,LocalDate.of(2002,8,1),
                null);

        Arrays.stream(emps).forEach(emp -> System.out.println(emp+"-->"+"entrydate:"+
                emp.getEntrydate()+"\n"));
    }

    @Test
    public void testDynamicSql2(){
        Emp[] emps=empMapper.SelectDynamicSql2(null,(short) 1,null,null);

        Arrays.stream(emps).forEach(emp -> System.out.println(emp+"-->"+"entrydate:"+
                emp.getEntrydate()+"\n"));
    }

    @Test
    public void testUpdateDynamicSql(){
        Emp  emp=new Emp();
        emp.setId(19);
        emp.setImage("106.jpg");
        emp.setEntrydate(LocalDate.of(2021,1,1));
        emp.setUpdateTime(LocalDateTime.now());
        emp.setDeptId(2);
        empMapper.UpdateDynamicSql(emp);
    }

    @Test
    public void testdeleteDynamicSqlByIds(){
        List<Integer> ids= Arrays.asList(17,18,19);
        int result=empMapper.deleteDynamicSqlByIds(ids);
        System.out.println(result);

    }
}

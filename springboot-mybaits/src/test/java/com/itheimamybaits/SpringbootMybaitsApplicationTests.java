package com.itheimamybaits;

import com.itheimamybaits.mapper.UserMapper;
import com.itheimamybaits.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
//springboot整合单元测试的注解
class SpringbootMybaitsApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testList(){
        List<User> userList=userMapper.userToList();
        userList.forEach(user -> System.out.println(user));
    }

    @Test
    void contextLoads() {
    }

}

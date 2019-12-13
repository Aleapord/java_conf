package com.example.demo.Dao;

import com.example.demo.Pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDao {
    @Select("select * from users where name = #{name}")
    User selectUser(@Param("name") String name);

    @Insert("insert into users(name,password) value (#{user.name},#{user.password})")
    @Options(useGeneratedKeys = true,keyProperty = "user.id")
    int insertUser(@Param("user") User user);
}

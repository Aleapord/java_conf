package com.example.demo.Dao;

import com.example.demo.Pojo.Conf_user;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface Conf_UserDao {

    @Insert("INSERT INTO `conf_user` VALUES (#{cu.userid}, #{cu.confid}, #{cu.name},#{cu.until} , #{cu.id_card}, #{cu.tel}, #{cu.time}, #{cu.sex}, #{cu.room});")
    void insertConf_user(@Param("cu") Conf_user conf_user);

    @Select("select * from conf_user where userid=#{userid} and confid=#{confid}")
    Conf_user selectConf_user(@Param("userid") int userid,@Param("confid") int confid);

    @Delete("delete from conf_user where userid=#{cu.userid} and confid=#{cu.confid}")
    void tuixua(@Param("cu") Conf_user conf_user);

    @Select("select * from conf_user where confid=#{id}")
    List<Conf_user> regDetail(@Param("id") int confid);
}

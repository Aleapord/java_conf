package com.example.demo.Dao;

import com.example.demo.Pojo.Conf_user;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface Conf_UserDao {

    @Insert("INSERT INTO `conf_user` VALUES (#{cu.userid}, #{cu.confid}, #{cu.name},#{cu.until} , #{cu.id_card}, #{cu.tel}, #{cu.time}, #{cu.sex}, #{cu.room});")
    void insertConf_user(@Param("cu") Conf_user conf_user);

}

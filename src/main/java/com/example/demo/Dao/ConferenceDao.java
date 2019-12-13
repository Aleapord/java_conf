package com.example.demo.Dao;

import com.example.demo.Pojo.Conference;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ConferenceDao {

    @Select("select * from conference  where confid = #{confid}")
    Conference selectConfById(@Param("confid") int confid);

    @Select("select * from conference where confname = #{confname}")
    Conference selectConfByName(@Param("confname") String confname);

    @Select("select * from conference ")
    List<Conference> selectAllConference();

//    @Insert("insert into conference value('#{confname}','#{confinf)")
//    @Options(useGeneratedKeys = true,keyProperty = "conference.confid")
//    void addConf(@Param("confname") String name,@Param("confinf") String inf ,@Param("other") String other,
//                 @Param("confplace") String place,@Param("confhotle") String hotle,@Param("confpeople") String people,@Param("confnum") int num);

    @Insert("INSERT INTO `conference` ( `confname`, `conftime`, `confinf`, `confplace`, `confhotle`, `confnum`,`confneed`) VALUES (#{conf.confname}, #{conf.conftime}, #{conf.confinf}, #{conf.confplace}, #{conf.confhotle}, #{conf.confnum},#{conf.confneed})")
    @Options(useGeneratedKeys = true,keyProperty = "conf.confid")
    void insertUser(@Param("conf") Conference conf);

}

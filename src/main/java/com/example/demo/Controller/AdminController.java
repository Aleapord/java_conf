package com.example.demo.Controller;

import com.example.demo.Pojo.Conference;
import com.example.demo.Services.ConferenceServices;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("admin/")
public class AdminController {

    @Autowired
    private ConferenceServices conferenceServices;

    @RequestMapping("/")
    public String admin(){
        return "admin";
    }

    @RequestMapping("getAllConf/")
    @ResponseBody
    public List<Conference> getAllConf(){
        System.out.println(conferenceServices.selectAllConference());
        return conferenceServices.selectAllConference();

    }

//    public HashMap<String ,Object> addConf(@RequestBody Conference conference ){
//        Conference conf=conferenceServices.selectConference(conference.getConfname());
//        HashMap map=new HashMap();
//        String s="";
//        if(conf!=null){
//            s="会议已存在！";
//
//        }else
//        {
//            conferenceServices.addConf(conference);
//            s="添加成功！";
//        }
//        map.put("msg",s);
//        return map;
//    }
    @RequestMapping("addConf/")
    @ResponseBody
    public HashMap<String,Object> addConf(@RequestBody Conference conference) {

        Conference conf = conferenceServices.selectConference(conference.getConfname());
        HashMap map = new HashMap();
        String s = "";
        if (conf != null) {
            s = "会议已存在！";

        } else{
            conferenceServices.addConf(conference);
        s = "添加成功！";

        }
        map.put("msg",s);
        return map;

    }
}

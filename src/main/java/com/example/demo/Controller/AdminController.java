package com.example.demo.Controller;

import com.example.demo.Pojo.Conf_user;
import com.example.demo.Pojo.Conference;
import com.example.demo.Services.Conf_UserService;
import com.example.demo.Services.ConferenceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

//管理员界面的控制器
@Controller
@RequestMapping("admin/")
public class AdminController {

    @Autowired
    private ConferenceServices conferenceServices;

    @Autowired
    private Conf_UserService conf_userService;

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
    @RequestMapping("delete/")
    @ResponseBody
    public HashMap<String,Object> delete(int id){
        HashMap<String,Object> hashMap=new HashMap<>();
        conferenceServices.deleteConf(id);
        hashMap.put("msg","删除成功！");
        return hashMap;
    }
    @RequestMapping("regdetail/")
    @ResponseBody
    public List<Conf_user> regDetail(int id){
        return conf_userService.regDetail(id);
    }

}

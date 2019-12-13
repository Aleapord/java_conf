package com.example.demo.Controller;

import com.example.demo.Pojo.User;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("login/")
    @ResponseBody
    public Map<String,Object> login(@RequestBody User user){
        Map<String,Object> rs = new HashMap<>();
        User user1 = userService.getUser(user.getName());
        String msg="";
        if(!userService.isExistUser(user.getName())){
            msg = "用户名不存在！";
        }
        else if(userService.queryPassword(user.getName()).equals(user.getPassword())){
            if(user.getName().equals("Admin"))
            {
                msg="Admin";
            }
            else {
                msg = "success";
            }

        }
        else {
            msg = "密码错误！";
        }
        rs.put("msg",msg);
        rs.put("id",user1.getId());
        return rs;
    }
    @RequestMapping("reg/")
    public Map<String, Object> reg(String name, String password){
        Map<String,Object> rs=new HashMap<>();
        if(userService.isExistUser(name)){
            rs.put("st","0");
        }
        else {
            userService.insertUser(name, password);
            rs.put("st","1");
        }
        return rs;
    }

    @RequestMapping("getuser/")
    @ResponseBody
    public User getUser(String name){
        return userService.getUser(name);
    }

    @RequestMapping("loggedin/")
    public String loggedIn(){
        return "loggedin";
    }
}

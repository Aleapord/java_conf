package com.example.demo;

import com.example.demo.Dao.UserDao;
import com.example.demo.Pojo.Conference;
import com.example.demo.Services.Conf_UserService;
import com.example.demo.Services.ConferenceServices;
import com.example.demo.Services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserService userDao;
    @Autowired
    private ConferenceServices conferenceServices;
    @Autowired
    private Conf_UserService conf_userService;

    @Test
    public void contextLoads() throws FileNotFoundException {
       System.out.println(ResourceUtils.getURL("").getPath()+"/sec/main/resources/static/erweima.png");
    }

}

package com.example.demo.Services;

import com.example.demo.Dao.Conf_UserDao;
import com.example.demo.Pojo.Conf_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Conf_UserService {
    @Autowired
    private Conf_UserDao conf_userDao;

    public void baoming(Conf_user conf_user){

        conf_userDao.insertConf_user(conf_user);
    }

}

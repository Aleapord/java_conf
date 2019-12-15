package com.example.demo.Services;

import com.example.demo.Dao.Conf_UserDao;
import com.example.demo.Pojo.Conf_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Conf_UserService {


    @Autowired
    private Conf_UserDao conf_userDao;

    public void baoming(Conf_user conf_user){

        conf_userDao.insertConf_user(conf_user);
    }

    public Conf_user selectConf_user(int userid,int confid){

       return conf_userDao.selectConf_user(userid,confid);
    }
    public void tuixuan(Conf_user conf_user){
        conf_userDao.tuixua(conf_user);

    }
    public List<Conf_user> regDetail(int confid){
        return conf_userDao.regDetail(confid);
    }
}

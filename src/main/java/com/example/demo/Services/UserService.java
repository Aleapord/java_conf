package com.example.demo.Services;

import com.example.demo.Dao.UserDao;
import com.example.demo.Pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUser(String name){
        return userDao.selectUser(name);
    }

    public String queryPassword(String userName){
        return userDao.selectUser(userName).getPassword();
    }
    public boolean isExistUser(String name){
        User user=null;
        user=userDao.selectUser(name);
        if (user==null){
            return false;
        }
        else return true;
    }
    public  void insertUser(String name,String password){
        User user=new User(0,name,password);
        userDao.insertUser(user);
    }

}

package com.example.demo.Services;

import com.example.demo.Dao.ConferenceDao;
import com.example.demo.Pojo.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConferenceServices {

    @Autowired
    private ConferenceDao conferenceDao;

    public Conference selectConference(int confId){
        return conferenceDao.selectConfById(confId);
    }

    public Conference selectConference(String confname){ return conferenceDao.selectConfByName(confname);}

    public List<Conference> selectAllConference(){
        return conferenceDao.selectAllConference();
    }

    public void addConf(Conference conf){
        Date date=new Date();
        int num= (int) (date.getTime()%10000);
        conf.setConftime(date);
        conf.setConfnum(num);
        System.out.println(conf);
        conferenceDao.insertUser(conf);

    }
    public List<Conference> confsForUser(int id){

        return conferenceDao.getConfForSomeOne(id);
    }
    public void deleteConf(int id){

        conferenceDao.deleteConf(id);
    }
}

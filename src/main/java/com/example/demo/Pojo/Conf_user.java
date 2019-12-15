package com.example.demo.Pojo;

import java.util.Date;

public class Conf_user {
    private int userid;
    private int confid;
    private String name;

    public Conf_user(int userid, int confid, String name, String until, String id_card, String tel, Date time, String sex, String room) {
        this.userid = userid;
        this.confid = confid;
        this.name = name;
        this.until = until;
        this.id_card = id_card;
        this.tel = tel;
        this.time = time;
        this.sex = sex;
        this.room = room;
    }

    public Conf_user(int userid, int confid) {
        this.userid = userid;
        this.confid = confid;
    }

    private String until;
    private String id_card;
    private String tel;
    private Date time;
    private String sex;
    private String room;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUntil() {
        return until;
    }

    public void setUntil(String until) {
        this.until = until;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Conf_user() {
    }

    public Conf_user(int confid, String name) {
        this.confid = confid;
        this.name = name;
    }

    public int getConfid() {
        return confid;
    }

    public void setConfid(int confid) {
        this.confid = confid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Conf_user{" +
                "userid=" + userid +
                ", confid=" + confid +
                ", name='" + name + '\'' +
                ", until='" + until + '\'' +
                ", id_card='" + id_card + '\'' +
                ", tel='" + tel + '\'' +
                ", time=" + time +
                ", sex='" + sex + '\'' +
                ", room='" + room + '\'' +
                '}';
    }
}

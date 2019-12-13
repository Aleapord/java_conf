package com.example.demo.Pojo;

public class User {
    private int id;
    private String name;
    private String password;
    private String unit;
    private String idnumber;
    private String tel;
    private String sex;
    private String userinf;
    private String userimg;

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getUnit() {
        return unit;
    }

    public User(String name, String password, String sex) {
        this.name = name;
        this.password = password;
        this.sex = sex;
    }

    public String getUserinf() {
        return userinf;
    }

    public void setUserinf(String userinf) {
        this.userinf = userinf;
    }

    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

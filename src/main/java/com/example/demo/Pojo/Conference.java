package com.example.demo.Pojo;

import java.util.Date;

public class Conference {
    private int confid;
    private String confname;
    private Date conftime;
    private String confinf;
    private String confplace;
    private String confhotle;
    private String confneed;

    public String getConfneed() {
        return confneed;
    }

    public void setConfneed(String confneed) {
        this.confneed = confneed;
    }

    public Conference(String confname, String confinf, String confplace, String confhotle, String confimg) {
        this.confname = confname;
        this.confinf = confinf;
        this.confplace = confplace;
        this.confhotle = confhotle;
        this.confimg = confimg;
    }

    private int confnum;
    private String confimg;

    public int getConfnum() {
        return confnum;
    }

    public void setConfnum(int confnum) {
        this.confnum = confnum;
    }

    public Conference(String confname,  Date conftime, String confinf) {
        this.confname = confname;
        this.conftime = conftime;
        this.confinf = confinf;
    }

    public Conference(String confname, int confnum) {
        this.confname = confname;

        this.confnum = confnum;
    }

    public String getConfimg() {
        return confimg;
    }

    public void setConfimg(String confimg) {
        this.confimg = confimg;
    }

    public String getConfplace() {
        return confplace;
    }

    public void setConfplace(String confplace) {
        this.confplace = confplace;
    }

    public String getConfhotle() {
        return confhotle;
    }

    public void setConfhotle(String confhotle) {
        this.confhotle = confhotle;
    }

    public int getConfid() {
        return confid;
    }

    public void setConfid(int confid) {
        this.confid = confid;
    }

    public String getConfname() {
        return confname;
    }

    public void setConfname(String confname) {
        this.confname = confname;
    }



    public Date getConftime() {
        return conftime;
    }

    public void setConftime(Date conftime) {
        this.conftime = conftime;
    }

    public String getConfinf() {
        return confinf;
    }

    public void setConfinf(String confinf) {
        this.confinf = confinf;
    }

    public Conference() {
    }

    public Conference(int confid, String confname, int id, Date conftime, String confinf) {
        this.confid = confid;
        this.confname = confname;
        this.conftime = conftime;
        this.confinf = confinf;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "confid=" + confid +
                ", confname='" + confname + '\'' +
                ", conftime='" + conftime + '\'' +
                ", confinf='" + confinf + '\'' +
                '}';
    }
}

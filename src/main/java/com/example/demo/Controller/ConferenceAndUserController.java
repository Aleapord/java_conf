package com.example.demo.Controller;

import com.example.demo.Pojo.Conf_user;
import com.example.demo.Pojo.Conference;
import com.example.demo.Services.Conf_UserService;
import com.example.demo.Services.ConferenceServices;
import com.example.demo.Until.QrCodeUntil;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.HsqlMaxValueIncrementer;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

//登录后界面的控制器
@Controller
@RequestMapping("loggedin/")
public class ConferenceAndUserController {

    @Autowired
    private ConferenceServices conferenceServices;
    @Autowired
    private Conf_UserService conf_userService;

   //获取会议
    @RequestMapping("getConf/")
    @ResponseBody
    public Conference getConf(int confid){
        return conferenceServices.selectConference(confid);
    }
    //获取所有会议列表
    @RequestMapping("getAllConf/")
    @ResponseBody
    public List<Conference> getAllConf(){
        return conferenceServices.selectAllConference();
    }

    //将会议信息输出为excel
    @RequestMapping("exportExcel")
    public void exportExcel(int confid, HttpServletResponse response) throws IOException {
        Conference conference = conferenceServices.selectConference(confid);
        String[] header={"会议名称","会议介绍","会议地点","会议宾馆","会议时间","会议人物"};
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet=hssfWorkbook.createSheet(conference.getConfname());
        HSSFRow headRow =hssfSheet.createRow(0);
        for (int i=0;i<header.length;i++) {
            HSSFCell cell = headRow.createCell(i);
            HSSFRichTextString textString = new HSSFRichTextString(header[i]);
            cell.setCellValue(textString);
        }
        HSSFRow row1=hssfSheet.createRow(1);
        HSSFCell cell = row1.createCell(0);
        HSSFRichTextString textString = new HSSFRichTextString(conference.getConfname());
        cell.setCellValue(textString);
        cell = row1.createCell(1);
        textString = new HSSFRichTextString(conference.getConfinf());
        cell.setCellValue(textString);
        cell = row1.createCell(2);
        textString = new HSSFRichTextString(conference.getConfplace());
        cell.setCellValue(textString);
        cell = row1.createCell(3);
        textString = new HSSFRichTextString(conference.getConfhotle());
        cell.setCellValue(textString);
        cell = row1.createCell(4);
        textString = new HSSFRichTextString(conference.getConftime().toString());
        cell.setCellValue(textString);
        cell = row1.createCell(5);
        textString = new HSSFRichTextString(conference.getConfimg());
        cell.setCellValue(textString);

        response.setContentType("application/octet-stream");

        response.setHeader("Content-disposition", "attachment;filename=download.xls");


        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        hssfWorkbook.write(response.getOutputStream());
    }
    @RequestMapping(value = "baoming/",method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,Object> baoming(@RequestBody Conf_user confUser){
        System.out.println(confUser);
        String s="";
        Conf_user testConf_user = conf_userService.selectConf_user(confUser.getUserid(),confUser.getConfid());
        if(testConf_user!=null){
            s="您已报名！";
        }else {
            conf_userService.baoming(confUser);
            s="报名成功!";
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("msg",s);
        return map;
    }
    //获取用户当前报名所有会议的接口
    @RequestMapping(value = "getMyConfs",method = RequestMethod.POST)
    @ResponseBody
    public List<Conference> getMyConfs(int userid){
        return conferenceServices.confsForUser(userid);
    }
    //退选会议的接口
    @RequestMapping("tuixuan/")
    @ResponseBody
    public HashMap<String,Object> tuixuan(@RequestBody Conf_user conf_user){
        HashMap<String,Object> hashMap=new HashMap<>();
        String s="";
        if (conf_userService.selectConf_user(conf_user.getUserid(),conf_user.getConfid())!=null){
            conf_userService.tuixuan(conf_user);
            s="退选成功！";
        }else {
            s="您并没有报名此会议。";
        }
        hashMap.put("msg",s);
        return hashMap;

    }
    @RequestMapping("qrcode/")
    @ResponseBody
     public void qrcode(int id, HttpServletResponse response) throws IOException {
        Conference conference = conferenceServices.selectConference(id);
        ServletOutputStream stream =null;
        String inf = "会议名称："+conference.getConfname() +
                "会议介绍："+conference.getConfinf()+"\n"+
                "会议时间："+conference.getConftime()+"\n"+
                "会议地点："+conference.getConfplace()+"\n"+
                "会议宾馆："+conference.getConfhotle()+"\n"+
                "会议人物："+conference.getConfimg()+"\n"+
                "会议唯一编号："+conference.getConfnum();

        try{
            stream=response.getOutputStream();
            QrCodeUntil.encode(inf,stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(stream!=null){
                stream.flush();
                stream.close();
            }
        }

    }
}
